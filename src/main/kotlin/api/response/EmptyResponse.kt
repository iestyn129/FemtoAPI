package dev.iestyn129.femtoapi.api.response

import dev.iestyn129.femtoapi.Headers
import dev.iestyn129.femtoapi.StatusCode

class EmptyResponse(
	override val code: StatusCode,
	override val headers: Headers = Headers()
) : IResponse {
	override val content: ByteArray = ByteArray(0)

	init {
		headers["Content-Type"] = "text/plain"
	}
}
