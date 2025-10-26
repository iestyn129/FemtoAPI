# FemtoAPI
A super tiny and super simple HTTP server and API framework designed for only basic needs of me (iestyn129).

## Building
TynLog is written in Kotlin 2.2.20 on Java 8, using Gradle 8.14.
I'm still new to Gradle, so don't expect perfection.

## Example
Here's an example that shows you the basic setup, and a little bit of what you can do with it.
```kotlin
class ExampleAPI : FemtoAPI("127.0.0.1") {
	@GET("/")
	@GET("/index") // you can handle multiple endpoints with one function
	fun root(session: HTTPSession): IResponse = TextResponse(
		StatusCode.OK,
		"Hi from '${session.uri.path}'!\n" +
		"The method you're using is ${session.method}."
	)
	
	@POST("/post")
	fun post(session: HTTPSession): IResponse = TextResponse(
		StatusCode.OK,
		"Your query sir: ${session.query}"
	)

	@GET("/json")
	@POST("/json") // or support multiple methods
	fun json(session: HTTPSession): IResponse = JSONResponse(
		StatusCode.OK,
		"""{"example": true, "any_actual_json_validation": false, "only_booleans": "false"}"""
	)

	@GET("/html")
	fun html(session: HTTPSession): IResponse = HTMLResponse(
		StatusCode.OK,
		"<html>\n" +
		"<body>\n" +
		"	<h1>I'm a header</h1>\n" +
		"</body>\n"
	)

	@GET("/empty")
	fun empty(session: HTTPSession): IResponse = EmptyResponse(
		StatusCode.OK // empty
	)
	
	// And a lot more!
	// ...
	// ...
	// ok maybe only a little more
}

fun main() {
	val api: ExampleAPI = ExampleAPI()
	api.start()
	Runtime.getRuntime().addShutdownHook(Thread {
		api.stop()
	} )
}
```

## Known Problems
- I see this in the log sometimes, but only for 404s for favicon.ico. It's not high priority.
`Unknown error (java.net.SocketException: Connection reset) occurred when communicating with Socket[addr=/127.0.0.1,port=58655,localport=8080]`

## Issues and Contribution
If you want to create issues or pull requests, be my guest but expect nothing from them.
Yet again, this is only for me.

## Dependencies
- [TynLog](https://github.com/iestyn129/TynLog) - Licenced under GPLv3+.

## Licence
This project is licenced under the GNU General Public Licence version 3 or later (GPLv3+).
See the [LICENCE](./LICENCE) file for more details.
