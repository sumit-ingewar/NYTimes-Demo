package com.sumit.core

val String.Companion.empty : String get() = String()

fun String?.safeGet(): String = this ?: String.empty