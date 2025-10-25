package dev.iestyn129.femtoapi.response

import dev.iestyn129.femtoapi.Headers

class BinaryResponse(val code: StatusCode, val data: ByteArray, val headers: Headers = Headers()) : IResponse {
	init {
		headers["Content-Type"] = "application/octet-stream"
	}

	override fun toHTTPResponse(): HTTPResponse = HTTPResponse(
		code,
		headers,
		data
	)
}
