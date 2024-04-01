package io.tnboot.logging.util.colorful

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ModTests {
	@BeforeTest
	fun setup() = setEnabledState(true)

	@AfterTest
	fun teardown() = setEnabledState(false)

	@Test
	fun `reset()`() {
		assertEquals("\u001b[0mfoo bar\u001b[0m", reset("foo bar"))
	}

	@Test
	fun `red() single color`() {
		assertEquals("\u001b[31mfoo bar\u001b[39m", red("foo bar"))
	}

	@Test
	fun `bgBlue() red() double color`() {
		assertEquals("\u001b[44m\u001b[31mfoo bar\u001b[39m\u001b[49m", bgBlue(red("foo bar")))
	}

	@Test
	fun `red() replaces close characters`() {
		assertEquals("\u001b[31mHel\u001b[31mlo\u001b[39m", red("Hel\u001b[39mlo"))
	}

	@Test
	fun `isEnabled handles enabled colors`() {
		assertTrue(isEnabled)
		setEnabledState(false)
		assertFalse(isEnabled)
		assertEquals("foo bar", red("foo bar"))
		setEnabledState(true)
		assertTrue(isEnabled)
		assertEquals("\u001b[31mfoo bar\u001b[39m", red("foo bar"))
		disable()
		assertFalse(isEnabled)
		assertEquals("foo bar", red("foo bar"))
		enable()
		assertTrue(isEnabled)
		assertEquals("\u001b[31mfoo bar\u001b[39m", red("foo bar"))
		toggleEnabledState()
		assertFalse(isEnabled)
		assertEquals("foo bar", red("foo bar"))
		toggleEnabledState()
		assertTrue(isEnabled)
		assertEquals("\u001b[31mfoo bar\u001b[39m", red("foo bar"))
	}

	@Test
	fun `bold()`() {
		assertEquals("\u001b[1mfoo bar\u001b[22m", bold("foo bar"))
	}

	@Test
	fun `dim()`() {
		assertEquals("\u001b[2mfoo bar\u001b[22m", dim("foo bar"))
	}

	@Test
	fun `italic()`() {
		assertEquals("\u001b[3mfoo bar\u001b[23m", italic("foo bar"))
	}

	@Test
	fun `underline()`() {
		assertEquals("\u001b[4mfoo bar\u001b[24m", underline("foo bar"))
	}

	@Test
	fun `inverse()`() {
		assertEquals("\u001b[7mfoo bar\u001b[27m", inverse("foo bar"))
	}

	@Test
	fun `hidden()`() {
		assertEquals("\u001b[8mfoo bar\u001b[28m", hidden("foo bar"))
	}

	@Test
	fun `strikeThrough()`() {
		assertEquals("\u001b[9mfoo bar\u001b[29m", strikeThrough("foo bar"))
	}

	@Test
	fun `black()`() {
		assertEquals("\u001b[30mfoo bar\u001b[39m", black("foo bar"))
	}

	@Test
	fun `red()`() {
		assertEquals("\u001b[31mfoo bar\u001b[39m", red("foo bar"))
	}

	@Test
	fun `green()`() {
		assertEquals("\u001b[32mfoo bar\u001b[39m", green("foo bar"))
	}

	@Test
	fun `yellow()`() {
		assertEquals("\u001b[33mfoo bar\u001b[39m", yellow("foo bar"))
	}

	@Test
	fun `blue()`() {
		assertEquals("\u001b[34mfoo bar\u001b[39m", blue("foo bar"))
	}

	@Test
	fun `magenta()`() {
		assertEquals("\u001b[35mfoo bar\u001b[39m", magenta("foo bar"))
	}

	@Test
	fun `cyan()`() {
		assertEquals("\u001b[36mfoo bar\u001b[39m", cyan("foo bar"))
	}

	@Test
	fun `white()`() {
		assertEquals("\u001b[37mfoo bar\u001b[39m", white("foo bar"))
	}

	@Test
	fun `brightBlack()`() {
		assertEquals("\u001b[90mfoo bar\u001b[39m", brightBlack("foo bar"))
	}

	@Test
	fun `brightRed()`() {
		assertEquals("\u001b[91mfoo bar\u001b[39m", brightRed("foo bar"))
	}

	@Test
	fun `brightGreen()`() {
		assertEquals("\u001b[92mfoo bar\u001b[39m", brightGreen("foo bar"))
	}

	@Test
	fun `brightYellow()`() {
		assertEquals("\u001b[93mfoo bar\u001b[39m", brightYellow("foo bar"))
	}

	@Test
	fun `brightBlue()`() {
		assertEquals("\u001b[94mfoo bar\u001b[39m", brightBlue("foo bar"))
	}

	@Test
	fun `brightMagenta()`() {
		assertEquals("\u001b[95mfoo bar\u001b[39m", brightMagenta("foo bar"))
	}

	@Test
	fun `brightCyan()`() {
		assertEquals("\u001b[96mfoo bar\u001b[39m", brightCyan("foo bar"))
	}

	@Test
	fun `brightWhite()`() {
		assertEquals("\u001b[97mfoo bar\u001b[39m", brightWhite("foo bar"))
	}

	@Test
	fun `bgBlack()`() {
		assertEquals("\u001b[40mfoo bar\u001b[49m", bgBlack("foo bar"))
	}

	@Test
	fun `bgRed()`() {
		assertEquals("\u001b[41mfoo bar\u001b[49m", bgRed("foo bar"))
	}

	@Test
	fun `bgGreen()`() {
		assertEquals("\u001b[42mfoo bar\u001b[49m", bgGreen("foo bar"))
	}

	@Test
	fun `bgYellow()`() {
		assertEquals("\u001b[43mfoo bar\u001b[49m", bgYellow("foo bar"))
	}

	@Test
	fun `bgBlue()`() {
		assertEquals("\u001b[44mfoo bar\u001b[49m", bgBlue("foo bar"))
	}

	@Test
	fun `bgMagenta()`() {
		assertEquals("\u001b[45mfoo bar\u001b[49m", bgMagenta("foo bar"))
	}

	@Test
	fun `bgCyan()`() {
		assertEquals("\u001b[46mfoo bar\u001b[49m", bgCyan("foo bar"))
	}

	@Test
	fun `bgWhite()`() {
		assertEquals("\u001b[47mfoo bar\u001b[49m", bgWhite("foo bar"))
	}

	@Test
	fun `bgBrightBlack()`() {
		assertEquals("\u001b[100mfoo bar\u001b[49m", bgBrightBlack("foo bar"))
	}

	@Test
	fun `bgBrightRed()`() {
		assertEquals("\u001b[101mfoo bar\u001b[49m", bgBrightRed("foo bar"))
	}

	@Test
	fun `bgBrightGreen()`() {
		assertEquals("\u001b[102mfoo bar\u001b[49m", bgBrightGreen("foo bar"))
	}

	@Test
	fun `bgBrightYellow()`() {
		assertEquals("\u001b[103mfoo bar\u001b[49m", bgBrightYellow("foo bar"))
	}

	@Test
	fun `bgBrightBlue()`() {
		assertEquals("\u001b[104mfoo bar\u001b[49m", bgBrightBlue("foo bar"))
	}

	@Test
	fun `bgBrightMagenta()`() {
		assertEquals("\u001b[105mfoo bar\u001b[49m", bgBrightMagenta("foo bar"))
	}

	@Test
	fun `bgBrightCyan()`() {
		assertEquals("\u001b[106mfoo bar\u001b[49m", bgBrightCyan("foo bar"))
	}

	@Test
	fun `bgBrightWhite()`() {
		assertEquals("\u001b[107mfoo bar\u001b[49m", bgBrightWhite("foo bar"))
	}

	@Test
	fun `bgBrightWhite() with red()`() {
		assertEquals("\u001b[107m\u001b[31mfoo bar\u001b[39m\u001b[49m", bgBrightWhite(red("foo bar")))
	}

	@Test
	fun `bgBrightWhite() with red() and bold()`() {
		assertEquals(
			"\u001b[107m\u001b[1m\u001b[31mfoo bar\u001b[39m\u001b[22m\u001b[49m",
			bgBrightWhite(bold(red("foo bar"))),
		)
	}

	@Test
	fun `rgb8()`() {
		assertEquals("\u001b[38;5;42mfoo bar\u001b[39m", rgb8("foo bar", 42U))
	}

	@Test
	fun `bgRgb8()`() {
		assertEquals("\u001b[48;5;42mfoo bar\u001b[49m", bgRgb8("foo bar", 42U))
	}

	@Test
	fun `rgb24()`() {
		assertEquals(
			"\u001b[38;2;41;42;43mfoo bar\u001b[39m",
			rgb24("foo bar", 41U, 42U, 43U),
		)
	}

	@Test
	fun `bgRgb24()`() {
		assertEquals(
			"\u001b[48;2;41;42;43mfoo bar\u001b[49m",
			bgRgb24("foo bar", 41U, 42U, 43U),
		)
	}

	@Test
	fun `rgb24() hex`() {
		assertEquals(
			"\u001b[38;2;41;42;43mfoo bar\u001b[39m",
			rgb24("foo bar", 0x292A2BU),
		)
	}

	@Test
	fun `bgRgb24() hex`() {
		assertEquals(
			"\u001b[48;2;41;42;43mfoo bar\u001b[49m",
			bgRgb24("foo bar", 0x292A2BU),
		)
	}
}
