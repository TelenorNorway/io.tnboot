package io.tnboot.logging.util.colorful

/**
 * Container for a ANSI color codes.
 */
interface Stylus {
	/**
	 * Apply the style to the string.
	 *
	 * @param str The string to apply the style to.
	 * @return The string with the style applied.
	 */
	fun style(str: String): String
}
