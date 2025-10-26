package dev.iestyn129.femtoapi

enum class Method {
	CONNECT,
	DELETE,
	GET,
	HEAD,
	OPTIONS,
	PATCH,
	POST,
	TRACE;

	companion object {
		fun fromString(method: String): Method? = entries.find { method.equals(it.name, ignoreCase = true) }
	}
}
