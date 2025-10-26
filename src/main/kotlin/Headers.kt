package dev.iestyn129.femtoapi

import java.util.TreeMap

class Headers() : TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER) {
	constructor(vararg pairs: Pair<String, String>) : this() {
		putAll(pairs)
	}
}
