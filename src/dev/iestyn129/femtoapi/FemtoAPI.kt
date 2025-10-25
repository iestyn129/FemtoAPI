package dev.iestyn129.femtoapi

import dev.iestyn129.femtoapi.api.URIMap
import dev.iestyn129.femtoapi.response.HTTPResponse
import dev.iestyn129.femtoapi.response.IResponse
import dev.iestyn129.femtoapi.response.StatusCode
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

open class FemtoAPI(private val hostname: InetSocketAddress) : Closeable {
	constructor(host: String, port: Int) : this(InetSocketAddress(host, port))

	constructor(host: String) : this(InetSocketAddress(host, DEFAULT_PORT))

	constructor(port: Int) : this(InetSocketAddress(port))

	constructor() : this(InetSocketAddress(DEFAULT_PORT))

	private val serverSocket: ServerSocket = ServerSocket()
	private val uriMap: URIMap = URIMap(this)

	fun start() {
		TynLog.info("Running FemtoAPI at http://${hostname.hostName}:${hostname.port}")
		serverSocket.bind(hostname)

		thread { while (!serverSocket.isClosed) { try {
			val client: Socket = serverSocket.accept()
			val input: InputStream = client.getInputStream()
			val output: OutputStream = client.getOutputStream()

			try {
				val response: HTTPResponse = try {
					val statusLine: String = input.readLine()
						?: throw HTTPException("Input stream is empty")
					val status: List<String> = statusLine.split(" ", limit = 3)
					if (status.size != 3) throw HTTPException("Unknown HTTP request: \"$statusLine\"")

					val method: Method? = Method.fromString(status[0].uppercase())
					val uri: URI = URI(status[1].ifBlank { "/" })
					val protocol: String = status[2]

					if (method == null) throw HTTPException(
						"Unknown HTTP protocol: \"$protocol\"",
						StatusCode.MethodNotAllowed
					)

					if (protocol != HTTP_1_1)
						throw HTTPException("Unsupported HTTP protocol: \"$protocol\"")

					TynLog.debug("$method at $uri over $protocol")

					val session: HTTPSession = HTTPSession(uri, method, protocol, input)
					val response: Any = uriMap.get(method, session.baseURI)?.call(this, session)
						?: throw HTTPException("URI \"$uri\" not found", StatusCode.NotFound)

					when (response) {
						is HTTPResponse -> response
						is IResponse -> response.toHTTPResponse()
						else -> throw HTTPException(
							"Endpoint handler returned an invalid type: $response",
							StatusCode.InternalServerError
						)
					}
				} catch (e: HTTPException) {
					TynLog.error(e)
					HTTPResponse(e.code)
				}

				response.send(output)
			} catch (e: Exception) {
				TynLog.error("Unknown error ($e) occurred when communicating with $client")
			} finally {
				input.safeClose()
				output.safeClose()
				client.safeClose()
			}
		} catch (e: SocketException) {
			if (!serverSocket.isClosed) throw e
			TynLog.info("FemtoAPI socket has closed")
		} } }
	}

	override fun close() = serverSocket.safeClose()
}
