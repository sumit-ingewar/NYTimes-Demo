package com.sumit.core.extensions


val String.Companion.empty: String get() = ""

fun String?.safeGet(): String = this ?: String.empty

fun String.nonAlphabetCharPresent(): Boolean = this.matches("^[a-zA-Z]*$".toRegex()).not()

fun String.getNumbers(): Int = Regex("[^0-9]").replace(this, "").toInt()

fun String.removeWhiteSpaces(): String = this.replace("\\s".toRegex(), "")

fun String.Companion.randomString(length: Int): String {
    val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}