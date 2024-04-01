package io.tnboot.logging.model

/**
 * Represents an error.
 */
// Properties are marked as unused by IntelliJ because they are not
// used in the project. However, they are used by consumers of the
// module.
@Suppress("unused")
interface Error {
	val className: String
	val message: String?
	val stack: Array<StackTraceElement>
	val commonFrames: Int
	val cause: Error?
	val cyclic: Boolean

	/**
	 * Returns a string representation of this error.
	 */
	val repr: String
}
