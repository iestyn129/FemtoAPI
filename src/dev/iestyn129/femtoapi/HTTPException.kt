package dev.iestyn129.femtoapi


class HTTPException(
	override val message: String,
	val code: StatusCode = StatusCode.InternalServerError
) : Exception(message) {
	override fun toString(): String = "$code: $message"
}
