package io.tnboot.util.homedir

fun homedir() = System.getProperty("user.home") ?: System.getenv("HOME") ?: System.getenv("USERPROFILE")
	?: throw IllegalStateException("Cannot determine home directory")
