package io.tnboot.logging.util.colorful

/** Internal, don't touch, or you will be fired. */
class Code private constructor(
	private val open: String,
	private val close: String,
	private val regexp: Regex,
) : Stylus {
	@OptIn(ExperimentalUnsignedTypes::class)
	internal constructor(close: UByte, vararg open: UByte) : this(
		"\u001b[${open.joinToString(";")}m",
		"\u001b[${close}m",
		Regex("\\u001b\\[${close}m"),
	)

	override fun style(str: String): String {
		return if (isEnabled) {
			"$open${str.replace(regexp, open)}$close"
		} else {
			str
		}
	}
}
