package io.tnboot.logging.util.colorful

fun reset(text: String) = RESET.style(text)
fun bold(text: String) = BOLD.style(text)
fun dim(text: String) = DIM.style(text)
fun italic(text: String) = ITALIC.style(text)
fun underline(text: String) = UNDERLINE.style(text)
fun inverse(text: String) = INVERSE.style(text)
fun hidden(text: String) = HIDDEN.style(text)
fun strikeThrough(text: String) = STRIKETHROUGH.style(text)
fun black(text: String) = BLACK.style(text)
fun red(text: String) = RED.style(text)
fun green(text: String) = GREEN.style(text)
fun yellow(text: String) = YELLOW.style(text)
fun blue(text: String) = BLUE.style(text)
fun magenta(text: String) = MAGENTA.style(text)
fun cyan(text: String) = CYAN.style(text)
fun white(text: String) = WHITE.style(text)
fun brightBlack(text: String) = BRIGHT_BLACK.style(text)
fun brightRed(text: String) = BRIGHT_RED.style(text)
fun brightGreen(text: String) = BRIGHT_GREEN.style(text)
fun brightYellow(text: String) = BRIGHT_YELLOW.style(text)
fun brightBlue(text: String) = BRIGHT_BLUE.style(text)
fun brightMagenta(text: String) = BRIGHT_MAGENTA.style(text)
fun brightCyan(text: String) = BRIGHT_CYAN.style(text)
fun brightWhite(text: String) = BRIGHT_WHITE.style(text)
fun bgBlack(text: String) = BG_BLACK.style(text)
fun bgRed(text: String) = BG_RED.style(text)
fun bgGreen(text: String) = BG_GREEN.style(text)
fun bgYellow(text: String) = BG_YELLOW.style(text)
fun bgBlue(text: String) = BG_BLUE.style(text)
fun bgMagenta(text: String) = BG_MAGENTA.style(text)
fun bgCyan(text: String) = BG_CYAN.style(text)
fun bgWhite(text: String) = BG_WHITE.style(text)
fun bgBrightBlack(text: String) = BG_BRIGHT_BLACK.style(text)
fun bgBrightRed(text: String) = BG_BRIGHT_RED.style(text)
fun bgBrightGreen(text: String) = BG_BRIGHT_GREEN.style(text)
fun bgBrightYellow(text: String) = BG_BRIGHT_YELLOW.style(text)
fun bgBrightBlue(text: String) = BG_BRIGHT_BLUE.style(text)
fun bgBrightMagenta(text: String) = BG_BRIGHT_MAGENTA.style(text)
fun bgBrightCyan(text: String) = BG_BRIGHT_CYAN.style(text)
fun bgBrightWhite(text: String) = BG_BRIGHT_WHITE.style(text)

@OptIn(ExperimentalUnsignedTypes::class)
fun rgb8(color: UByte) = Code(39U, 38U, 5U, color)

fun rgb8(str: String, color: UByte) = rgb8(color).style(str)

@OptIn(ExperimentalUnsignedTypes::class)
fun bgRgb8(color: UByte) = Code(49U, 48U, 5U, color)

fun bgRgb8(str: String, color: UByte) = bgRgb8(color).style(str)

@OptIn(ExperimentalUnsignedTypes::class)
fun rgb24(r: UByte, g: UByte, b: UByte) = Code(39U, 38U, 2U, r, g, b)

fun rgb24(str: String, r: UByte, g: UByte, b: UByte) = rgb24(r, g, b).style(str)

fun rgb24(color: UInt) = rgb24(
	((color shr 16) and 0xFFU).toUByte(),
	((color shr 8) and 0xFFU).toUByte(),
	(color and 0xFFU).toUByte(),
)

fun rgb24(str: String, color: UInt) = rgb24(color).style(str)

@OptIn(ExperimentalUnsignedTypes::class)
fun bgRgb24(r: UByte, g: UByte, b: UByte) = Code(49U, 48U, 2U, r, g, b)

fun bgRgb24(str: String, r: UByte, g: UByte, b: UByte) = bgRgb24(r, g, b).style(str)

fun bgRgb24(color: UInt) = bgRgb24(
	((color shr 16) and 0xFFU).toUByte(),
	((color shr 8) and 0xFFU).toUByte(),
	(color and 0xFFU).toUByte(),
)

fun bgRgb24(str: String, color: UInt) = bgRgb24(color).style(str)
