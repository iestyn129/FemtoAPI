package dev.iestyn129.femtoapi.response

import dev.iestyn129.femtoapi.Headers

data class TextResponse(val code: StatusCode, val text: String, val headers: Headers = Headers()) : IResponse {
	init {
		headers["Content-Type"] = "text/plain"
	}

	override fun toHTTPResponse(): HTTPResponse = HTTPResponse(
		code,
		headers,
		text.encodeToByteArray()
	)
}
