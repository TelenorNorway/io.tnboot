package io.tnboot.logging.model

/**
 * Represents an error.
 */
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
