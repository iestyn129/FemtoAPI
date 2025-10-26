package dev.iestyn129.femtoapi.api

import dev.iestyn129.femtoapi.FemtoAPI
import dev.iestyn129.femtoapi.HTTPSession
import dev.iestyn129.femtoapi.Method
import dev.iestyn129.femtoapi.api.response.IResponse
import dev.iestyn129.tynlog.TynLog
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberFunctions

fun KFunction<*>.getMethods(): List<Pair<String, Method>> = annotations.mapNotNull { when (it) {
	is CONNECT -> it.uri to Method.CONNECT
	is DELETE -> it.uri to Method.DELETE
	is GET -> it.uri to Method.GET
	is HEAD -> it.uri to Method.HEAD
	is OPTIONS -> it.uri to Method.OPTIONS
	is PATCH -> it.uri to Method.PATCH
	is POST -> it.uri to Method.POST
	is TRACE -> it.uri to Method.TRACE
	else -> null
} }

class EndpointHandler(femtoAPI: FemtoAPI) {
	private val methodURIMap: Map<Method, Map<String, KFunction<*>>>

	init {
		val methodURIMap: MutableMap<Method, MutableMap<String, KFunction<*>>> = mutableMapOf()

		val femtoClass: KClass<out FemtoAPI> = femtoAPI::class
		if (femtoClass == FemtoAPI::class)
			TynLog.warn(
				"You are running the FemtoAPI class directly, " +
				"to create any endpoints you must inherit from FemtoAPI"
			)

		femtoClass.memberFunctions.forEach { it.getMethods().forEach { (uri: String, method: Method) ->
			if (it.parameters.size == 2 &&
				it.parameters[0].type.classifier == femtoClass &&
				it.parameters[1].type.classifier == HTTPSession::class &&
				it.returnType.classifier == IResponse::class
			) {
				if (!methodURIMap.containsKey(method))
					methodURIMap[method] = mutableMapOf()

				methodURIMap[method]?.put(uri, it)
			} else throw IllegalStateException(
				"Function `${femtoClass.simpleName}.${it.name}` for endpoint \"$uri\" " +
				"must match signature: `(HTTPSession) -> IResponse`"
			)
		} }

		this.methodURIMap = methodURIMap
	}

	fun get(method: Method, uri: String): KFunction<*>? = methodURIMap[method]?.get(uri)
}
