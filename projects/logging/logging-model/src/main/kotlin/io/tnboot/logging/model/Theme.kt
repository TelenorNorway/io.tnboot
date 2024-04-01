package io.tnboot.logging.model

/**
 * [Theme] is an interface that represents a theme for Telenor Boot
 * Logging. A theme is responsible for rendering a log event into a
 * string that can be written to a log file or console.
 */
interface Theme {
	/**
	 * The name of the theme.
	 *
	 * When implementing your theme, you should return a unique name,
	 * as this is used to identify the theme in the configuration. This
	 * name should be a simple string, and should not contain any
	 * special characters. For example:
	 *
	 * ```
	 * my-super-duper-cool-theme
	 * ```
	 *
	 * While no validation is done on the name, except a uniqueness
	 * check, it is recommended to follow the above guidelines.
	 */
	fun name(): String

	/** Render a log event into a string. */
	fun render(payload: LogPayload): String
}
