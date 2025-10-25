package dev.iestyn129.femtoapi.response

import dev.iestyn129.femtoapi.Headers

data class JSONResponse(val code: StatusCode, val json: String, val headers: Headers = Headers()) : IResponse {
	init {
		headers["Content-Type"] = "application/json"
	}

	override fun toHTTPResponse(): HTTPResponse = HTTPResponse(
		code,
		headers,
		json.encodeToByteArray()
	)
}
