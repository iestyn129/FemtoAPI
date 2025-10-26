package dev.iestyn129.femtoapi

fun main() {
	val headers: Headers = Headers()
	headers["Foo"] = "bar"
	headers["baR"] = "baz"
	headers["bAz"] = "fOo"
	headers["baz"] = "foo"
	println(headers["fOO"])
	println(headers["BAr"])
	println(headers["BaZ"])

	println(headers.keys)
}
