package dev.iestyn129.femtoapi

import dev.iestyn129.femtoapi.response.StatusCode

class HTTPException(
	override val message: String,
	val code: StatusCode = StatusCode.InternalServerError
) : Exception(message)
