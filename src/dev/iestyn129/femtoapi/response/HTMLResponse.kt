package dev.iestyn129.femtoapi.response

import dev.iestyn129.femtoapi.Headers

data class HTMLResponse(val code: StatusCode, val html: String, val headers: Headers = Headers()) : IResponse {
	init {
		headers["Content-Type"] = "text/html"
	}

	override fun toHTTPResponse(): HTTPResponse = HTTPResponse(
		code,
		headers,
		html.encodeToByteArray()
	)
}
