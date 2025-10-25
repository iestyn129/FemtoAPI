package dev.iestyn129.femtoapi

import dev.iestyn129.tynlog.TynLog
import java.io.Closeable
import java.io.InputStream
import java.io.OutputStream

const val HTTP_1_1: String = "HTTP/1.1"

fun Closeable.safeClose() = try {
	this.close()
} catch (e: Exception) {
	TynLog.error("Could not close $this because of $e")
}

fun InputStream.readLine(): String? {
	val builder: StringBuilder = StringBuilder()

	while (true) {
		val read: Int = read()
		if (read == -1)
			return if (builder.isNotEmpty()) builder.toString() else null

		val char: Char = read.toChar()

		if (char == '\n')
			break
		if (char != '\r')
			builder.append(char)
	}

	return builder.toString()
}

fun OutputStream.writeLine(message: String = "") =
	write((if (message.endsWith('\n')) message else "$message\n").encodeToByteArray())
