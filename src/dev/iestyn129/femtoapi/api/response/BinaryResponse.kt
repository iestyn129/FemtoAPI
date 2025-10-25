package dev.iestyn129.femtoapi.api.response

import dev.iestyn129.femtoapi.Headers
import dev.iestyn129.femtoapi.StatusCode

class BinaryResponse(
	override val code: StatusCode,
	override val content: ByteArray,
	override val headers: Headers = Headers(),
) : IResponse {
	init {
		headers["Content-Type"] = "application/octet-stream"
	}
}
