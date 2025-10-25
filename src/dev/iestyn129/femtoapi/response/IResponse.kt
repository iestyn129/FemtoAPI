package dev.iestyn129.femtoapi.response

interface IResponse {
	fun toHTTPResponse(): HTTPResponse
}
