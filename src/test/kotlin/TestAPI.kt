package dev.iestyn129.femtoapi

import dev.iestyn129.femtoapi.api.GET
import dev.iestyn129.femtoapi.api.POST
import dev.iestyn129.femtoapi.api.response.HTMLResponse
import dev.iestyn129.femtoapi.api.response.IResponse
import dev.iestyn129.femtoapi.api.response.JSONResponse
import dev.iestyn129.femtoapi.api.response.TextResponse
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

	@GET("/html")
	fun html(session: HTTPSession): IResponse = HTMLResponse(
		StatusCode.OK,
		"<html>" +
		"<body>" +
		"	<h1>HEADER!!!</h1>" +
		"</body>"
	)

	@GET("/json")
	@POST("/json")
	@GET("/iestyn129")
	fun json(session: HTTPSession): IResponse = JSONResponse(
		StatusCode.OK,
		"{\"iestyn129\": true}"
	)

	@GET("/?")
	fun questionMark(session: HTTPSession): IResponse = TextResponse(
		StatusCode.OK,
		"?"
	)

	@GET("/()")
	fun `()`(session: HTTPSession): IResponse = TextResponse(
		StatusCode.OK,
		"They're called parentheses, MOM!"
	)
}

fun main() {
	TynLog.init(TynConfig(
		debug = true,
		logFolder = LogFolderConfig(
			folder = File("src/test/log")
		)
	))

	val api: TestAPI = TestAPI()
	api.start()
	Runtime.getRuntime().addShutdownHook(Thread {
		api.stop()
	})
}
