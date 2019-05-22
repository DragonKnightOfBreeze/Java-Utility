@file:Suppress("unused", "UNUSED_PARAMETER", "MemberVisibilityCanBePrivate")

package com.windea.kotlin.generator

/**
 * 文本生成器接口。
 */
interface TextGen<T> {
	val text: String

	val length: Int
		get() = text.length

	val isEmpty: Boolean
		get() = text.isEmpty()

	val isBlank: Boolean
		get() = text.isBlank()
}
