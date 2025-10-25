package dev.iestyn129.femtoapi.api.response

import dev.iestyn129.femtoapi.Headers
import dev.iestyn129.femtoapi.StatusCode

class TextResponse(
	override val code: StatusCode,
	text: String,
	override val headers: Headers = Headers()
) : IResponse {
	override val content: ByteArray = text.encodeToByteArray()

	init {
		headers["Content-Type"] = "text/plain"
	}
}
