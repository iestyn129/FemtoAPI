package dev.iestyn129.femtoapi.api.response

import dev.iestyn129.femtoapi.Headers
import dev.iestyn129.femtoapi.StatusCode

class CustomResponse(
	override val code: StatusCode,
	contentType: String,
	override val content: ByteArray,
	override val headers: Headers = Headers()
) : IResponse {
	init {
		headers["Content-Type"] = contentType
	}
}
