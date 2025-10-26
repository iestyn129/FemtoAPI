package dev.iestyn129.femtoapi.api.response

import dev.iestyn129.femtoapi.Headers
import dev.iestyn129.femtoapi.StatusCode

class HTMLResponse(
	override val code: StatusCode,
	html: String,
	override val headers: Headers = Headers()
) : IResponse {
	override val content: ByteArray = html.encodeToByteArray()

	init {
		headers["Content-Type"] = "text/html"
	}
}
