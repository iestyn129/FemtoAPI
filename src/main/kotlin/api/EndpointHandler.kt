package dev.iestyn129.femtoapi.api

import dev.iestyn129.femtoapi.FemtoAPI
import dev.iestyn129.femtoapi.HTTPSession
import dev.iestyn129.femtoapi.Method
import dev.iestyn129.femtoapi.api.response.IResponse
import dev.iestyn129.tynlog.TynLog
import java.lang.reflect.Method as JMethod

fun JMethod.getMethods(): List<Pair<String, Method>> = buildList {
	addAll(getAnnotationsByType(CONNECT::class.java).map { it.uri to Method.CONNECT })
	addAll(getAnnotationsByType(DELETE::class.java).map { it.uri to Method.DELETE })
	addAll(getAnnotationsByType(GET::class.java).map { it.uri to Method.GET })
	addAll(getAnnotationsByType(HEAD::class.java).map { it.uri to Method.HEAD })
	addAll(getAnnotationsByType(OPTIONS::class.java).map { it.uri to Method.OPTIONS })
	addAll(getAnnotationsByType(PATCH::class.java).map { it.uri to Method.PATCH })
	addAll(getAnnotationsByType(POST::class.java).map { it.uri to Method.POST })
	addAll(getAnnotationsByType(TRACE::class.java).map { it.uri to Method.TRACE })
}

class EndpointHandler(femtoAPI: FemtoAPI) {
	private val methodURIMap: Map<Method, Map<String, JMethod>>

	init {
		val methodURIMap: MutableMap<Method, MutableMap<String, JMethod>> = mutableMapOf()

		val femtoClass: Class<FemtoAPI> = femtoAPI.javaClass
		if (femtoClass == FemtoAPI::class.java)
			TynLog.warn(
				"You are running the FemtoAPI class directly, " +
				"to create any endpoints you must inherit from FemtoAPI"
			)

		femtoClass.methods.forEach { method ->
			method.getMethods().forEach { (uri, httpMethod) ->
				val parameters = method.parameterTypes

				if (parameters.size == 1 &&
					parameters[0] == HTTPSession::class.java &&
					method.returnType == IResponse::class.java
				) {
					val uriMap = methodURIMap.getOrPut(httpMethod) {
						mutableMapOf()
					}

					if (uriMap.containsKey(uri)) throw IllegalStateException(
						"Function `${femtoClass.simpleName}.${method.name}` for endpoint \"$uri\" " +
						"is already used by `${femtoClass.simpleName}.${uriMap[uri]!!.name}`"
					)

					uriMap[uri] = method
				} else throw IllegalStateException(
					"Function `${femtoClass.simpleName}.${method.name}` for endpoint \"$uri\" " +
					"must match signature: `(HTTPSession) -> IResponse`"
				)
			}
		}

		this.methodURIMap = methodURIMap
	}

	fun get(method: Method, uri: String): JMethod? = methodURIMap[method]?.get(uri)
}
