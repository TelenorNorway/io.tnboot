package io.tnboot.logging.util.colorful

/**
 * Internal object to store the colors enabled state. Used for thread
 * safety.
 */
internal object Enabled {
	/** Whether colors wil be rendered. */
	internal var enabled = System.getenv("NO_COLOR")?.let { false } ?: true
}

/** Whether colors wil be rendered. */
val isEnabled get() = synchronized(Enabled) { Enabled.enabled }

/** Enable color rendering. */
fun enable() = synchronized(Enabled) {
	Enabled.enabled = true
}

/** Disable color rendering. */
fun disable() = synchronized(Enabled) {
	Enabled.enabled = false
}

/** Toggle the enabled state. */
fun toggleEnabledState() = synchronized(Enabled) {
	Enabled.enabled = !Enabled.enabled
}

/** Set the enabled state. */
fun setEnabledState(enabled: Boolean) = synchronized(Enabled) {
	Enabled.enabled = enabled
}
