package com.windea.kotlin.generator

interface TextGen<T> {
	val isEmpty: Boolean
		get() = text().isEmpty()

	val isBlank: Boolean
		get() = text().isBlank()

	fun text(): String

	fun length(): Int {
		return text().length
	}
}
