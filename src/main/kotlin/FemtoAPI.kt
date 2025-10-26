package dev.iestyn129.femtoapi

import dev.iestyn129.femtoapi.api.EndpointHandler
import dev.iestyn129.femtoapi.api.response.EmptyResponse
import dev.iestyn129.femtoapi.api.response.IResponse
import dev.iestyn129.tynlog.TynLog
import java.io.Closeable
import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException
import java.net.URI
import kotlin.concurrent.thread

private const val DEFAULT_PORT: Int = 8080
const val FEMTO_API_VERSION: String = "0.1.0"

open class FemtoAPI(private val hostname: InetSocketAddress) : Closeable {
	constructor(host: String, port: Int) : this(InetSocketAddress(host, port))

	constructor(host: String) : this(InetSocketAddress(host, DEFAULT_PORT))

	constructor(port: Int) : this(InetSocketAddress(port))

	constructor() : this(InetSocketAddress(DEFAULT_PORT))

	private val serverSocket: ServerSocket = ServerSocket()
	private val endpointHandler: EndpointHandler = EndpointHandler(this)

	fun start() {
		TynLog.info("Running FemtoAPI at http://${hostname.hostName}:${hostname.port}")
		serverSocket.bind(hostname)

		thread { while (!serverSocket.isClosed) { try {
			val client: Socket = serverSocket.accept()
			val input: InputStream = client.getInputStream()
			val output: OutputStream = client.getOutputStream()
			TynLog.debug()

			val startTime: Long = System.currentTimeMillis()

			try {
				var method: Method? = null
				var uri: URI? = null
				var protocol: String? = null

				val response: IResponse = try {
					val statusLine: String = input.readLine() ?: throw HTTPException(
						"Input stream is empty",
						StatusCode.BadRequest
					)

					val status: List<String> = statusLine.split(" ", limit = 3)
					if (status.size != 3) throw HTTPException(
						"Invalid HTTP status line: '$statusLine'",
						StatusCode.BadRequest
					)

					method = Method.fromString(status[0].uppercase())
					uri = URI(status[1].ifBlank { "/" })
					protocol = status[2]

					if (method == null) throw HTTPException(
						"Unknown HTTP method: '$protocol'",
						StatusCode.MethodNotAllowed
					)

					if (protocol != HTTP_1_1) throw HTTPException(
						"Unsupported HTTP protocol: '$protocol'",
						StatusCode.HTTPVersionNotSupported
					)

					val response: Any = endpointHandler.get(method, uri.path)?.call(
						this,
						HTTPSession(uri, method, protocol, input)
					) ?: throw HTTPException(
						"URI '${uri.path}' not found",
						StatusCode.NotFound
					)

					response as? IResponse ?: throw HTTPException(
						"Endpoint handler returned an invalid type: $response",
						StatusCode.InternalServerError
					)

					response
				} catch (e: HTTPException) {
					TynLog.error(e)
					EmptyResponse(e.code)
				}

				val httpResponse: HTTPResponse = HTTPResponse(protocol ?: HTTP_1_1, response)

				TynLog.info(
					"${client.inetAddress.hostAddress}:${client.port} - " +
					"${httpResponse.protocol} - " +
					"$method '${uri?.path}' ${httpResponse.code} - " +
					"${System.currentTimeMillis() - startTime}ms"
				)

				httpResponse.send(output)
			} catch (e: Exception) {
				TynLog.error("Unknown error ($e) occurred when communicating with $client")
			} finally {
				input.safeClose()
				output.safeClose()
				client.safeClose()
			}
		} catch (e: SocketException) {
			if (!serverSocket.isClosed) throw e
		} } }
	}

	fun stop() = close()

	override fun close() {
		serverSocket.safeClose()
		TynLog.info("FemtoAPI has stopped.")
	}
}
