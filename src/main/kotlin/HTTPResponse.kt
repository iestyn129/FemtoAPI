package dev.iestyn129.femtoapi

import dev.iestyn129.femtoapi.api.response.IResponse
import java.io.OutputStream
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class HTTPResponse(
	val protocol: String = HTTP_1_1,
	val code: StatusCode = StatusCode.OK,
	val headers: Headers = Headers(),
	val content: ByteArray = ByteArray(0)
) {
	constructor(protocol: String, response: IResponse) : this(
		protocol,
		response.code,
		response.headers,
		response.content
	)

	constructor(response: IResponse) : this(HTTP_1_1, response)

	init {
		headers["Content-Length"] = content.size.toString()
		if (!headers.containsKey("Content-Type"))
			headers["Content-Type"] = "application/octet-stream"

		headers["Server"] = "FemtoAPI/$FEMTO_API_VERSION"
		headers["Date"] = ZonedDateTime.now(ZoneOffset.UTC).format(
			DateTimeFormatter.ofPattern(
			"EEE, dd MMM yyyy HH:mm:ss 'GMT'",
			Locale.US
		))
	}

	fun send(output: OutputStream) {
		output.writeLine("$protocol $code")
		headers.forEach { (key: String, value: String) ->
			output.writeLine("${key.lowercase()}: $value")
		}

		output.writeLine()
		output.write(content)

		output.flush()
	}
}
