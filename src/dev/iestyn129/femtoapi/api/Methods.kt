package dev.iestyn129.femtoapi.api

@Repeatable
annotation class CONNECT(val uri: String) {
	companion object
}

@Repeatable
annotation class DELETE(val uri: String) {
	companion object
}

@Repeatable
annotation class GET(val uri: String) {
	companion object
}

@Repeatable
annotation class HEAD(val uri: String) {
	companion object
}

@Repeatable
annotation class OPTIONS(val uri: String) {
	companion object
}

@Repeatable
annotation class PATCH(val uri: String) {
	companion object
}

@Repeatable
annotation class POST(val uri: String) {
	companion object
}

@Repeatable
annotation class TRACE(val uri: String) {
	companion object
}
