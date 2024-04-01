package io.tnboot.logging.model

/**
 * Log level enum, from the most verbose to the least verbose.
 *
 * This class exists purely as a translation layer between
 * configuration and Slf4J and Logback.
 */
enum class LogLevel {
	Trace,
	Debug,
	Info,
	Warn,
	Error,
	;

	override fun toString(): String = when (this) {
		Trace -> "TRACE"
		Debug -> "DEBUG"
		Info -> "INFO"
		Warn -> "WARN"
		Error -> "ERROR"
	}
}
