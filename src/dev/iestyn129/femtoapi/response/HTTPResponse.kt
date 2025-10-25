package dev.iestyn129.femtoapi.response

import dev.iestyn129.femtoapi.HTTP_1_1
import dev.iestyn129.femtoapi.Headers
import dev.iestyn129.femtoapi.writeLine
import java.io.OutputStream
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class HTTPResponse(
	val code: StatusCode = StatusCode.OK,
	val headers: Headers = Headers(),
	val body: ByteArray = ByteArray(0)
) {
	init {
		if (body.isEmpty()) {
			headers.remove("Content-Length")
			headers.remove("Content-Type")
		} else
			headers["Content-Length"] = body.size.toString()

		headers["Server"] = "FemtoAPI"
		headers["Date"] = ZonedDateTime.now(ZoneOffset.UTC).format(
			DateTimeFormatter.ofPattern(
			"EEE, dd MMM yyyy HH:mm:ss 'GMT'",
			Locale.US
		))
	}

	fun send(output: OutputStream) {
		output.writeLine("$HTTP_1_1 ${code.code} $code")
		headers.forEach { (key: String, value: String) ->
			output.writeLine("$key: $value")
		}
		output.writeLine()
		output.write(body)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as HTTPResponse

		if (code != other.code) return false
		if (headers != other.headers) return false
		if (!body.contentEquals(other.body)) return false

		return true
	}

	override fun hashCode(): Int {
		var result: Int = code.hashCode()
		result = 31 * result + headers.hashCode()
		result = 31 * result + body.contentHashCode()
		return result
	}
}
