package io.tnboot.logging.model

import java.time.Instant

/**
 * [LogPayload] is a data class that represents the payload of a log
 * event. This is the data that is passed to Telenor Boot Logging
 * themes when a log event is created.
 */
// Properties are marked as unused by IntelliJ because they are not
// used in the project. However, they are used by consumers of the
// module.
@Suppress("unused")
interface LogPayload {
	val at: Instant
	val level: LogLevel
	val sequence: Long
	val loggerName: String
	val caller: Array<StackTraceElement>?
	val thread: String?
	val mdc: Map<String, String?>
	val formattedMessage: String
	val message: String
	val arguments: Array<Any?>
	val error: Error?
}
