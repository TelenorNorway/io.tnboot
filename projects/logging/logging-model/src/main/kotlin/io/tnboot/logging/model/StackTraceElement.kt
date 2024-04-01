package io.tnboot.logging.model

/**
 * Represents a single element in a stack trace.
 */
// Properties are marked as unused by IntelliJ because they are not
// used in the project. However, they are used by consumers of the
// module.
@Suppress("unused")
interface StackTraceElement {
	val classLoaderName: String?
	val clazz: String
	val method: String
	val native: Boolean
	val module: String?
	val version: String?
	val file: String?
	val line: Int?
	val omitted: Boolean
	val alreadyShown: Boolean

	/**
	 * Returns a string representation of this stack trace element.
	 */
	val repr: String
}
