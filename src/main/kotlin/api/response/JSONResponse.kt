package dev.iestyn129.femtoapi.api.response

import dev.iestyn129.femtoapi.Headers
import dev.iestyn129.femtoapi.StatusCode

class JSONResponse(
	override val code: StatusCode,
	json: String,
	override val headers: Headers = Headers()
) : IResponse {
	override val content: ByteArray = json.encodeToByteArray()

	init {
		headers["Content-Type"] = "application/json"
	}
}
