package dev.iestyn129.femtoapi

import dev.iestyn129.femtoapi.api.methods.GET
import dev.iestyn129.femtoapi.response.HTMLResponse
import dev.iestyn129.femtoapi.response.IResponse
import dev.iestyn129.femtoapi.response.JSONResponse
import dev.iestyn129.femtoapi.response.StatusCode
import dev.iestyn129.femtoapi.response.TextResponse
import dev.iestyn129.tynlog.LogFolderConfig
import dev.iestyn129.tynlog.TynConfig
import dev.iestyn129.tynlog.TynLog
import java.io.File

class TestAPI() : FemtoAPI("127.0.0.1") {
	@GET("/")
	fun root(session: HTTPSession): IResponse = TextResponse(
		StatusCode.OK,
		"Hi from '${session.uri}'!\n" +
		"The method you're using is ${session.method}."
	)

	@GET("/penis")
	fun penis(session: HTTPSession): IResponse = HTMLResponse(
		StatusCode.OK,
		"<html>" +
		"<body>" +
		"	<h1>penis</h1>" +
		"</body>"
	)

	@GET("/json")
	fun json(session: HTTPSession): IResponse = JSONResponse(
		StatusCode.OK,
		"{\"iestyn129\": true}"
	)
}

fun main() {
	TynLog.init(TynConfig(
		debug = true,
		logFolder = LogFolderConfig(
			folder = File("test/log")
		)
	))

	TestAPI().start()
}
