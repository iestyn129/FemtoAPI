package dev.iestyn129.femtoapi

import java.io.InputStream
import java.net.URI

data class HTTPSession(
	val uri: URI,
	val method: Method,
	val protocol: String,
	private val input: InputStream
) {
	private val headers: Headers = Headers()
	private val content: ByteArray
	val baseURI: String = uri.path
	val query: Map<String, String> = uri.query?.split("&")?.mapNotNull {
		val parts: List<String> = it.split('=', limit = 2)
		if (parts.size < 2) null else parts[0] to parts[1]
	}?.toMap() ?: emptyMap()

	init {
		while (true) {
			val headerLine: String? = input.readLine()
			if (headerLine == null || headerLine.isEmpty())
				break

			if (!headerLine.contains(": "))
				throw HTTPException("Invalid header: \"$headerLine\"")

			val (key: String, value: String) = headerLine.split(": ", limit = 2)
			headers[key] = value
		}

		headers["Content-Length"].let {
			val contentLength: Int = (it ?: 0.toString()).toIntOrNull()
				?: throw HTTPException("Invalid Content-Length: \"$it\"")
			content = ByteArray(contentLength)
			input.read(content)
		}
	}
}
