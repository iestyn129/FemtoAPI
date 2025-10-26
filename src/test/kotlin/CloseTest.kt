package dev.iestyn129.femtoapi

import dev.iestyn129.tynlog.TynLog
import java.io.Closeable
import java.io.File

fun main() {
	TynLog.init()
	val closable: Closeable = File("LICENCE").inputStream()
	closable.safeClose()
}
