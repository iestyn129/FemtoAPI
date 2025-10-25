package dev.iestyn129.femtoapi.api.response

import dev.iestyn129.femtoapi.Headers
import dev.iestyn129.femtoapi.StatusCode

interface IResponse {
	val code: StatusCode
	val headers: Headers
	val content: ByteArray
}
