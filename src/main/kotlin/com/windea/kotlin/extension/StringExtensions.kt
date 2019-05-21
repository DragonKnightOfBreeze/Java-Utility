package com.windea.kotlin.extension

import java.util.function.Predicate

//判断方法

/**
 * 判断字符串是否以指定的任意前缀[prefixes]开始。
 */
fun String.startsWith(vararg prefixes: String, ignoreCase: Boolean = false): Boolean {
	for (prefix in prefixes)
		if (this.startsWith(prefix, ignoreCase))
			return true
	return false
}

/**
 * 判断字符串是否以指定的任意后缀[suffixes]开始。
 */
fun String.endsWith(vararg suffixes: String, ignoreCase: Boolean = false): Boolean {
	for (suffix in suffixes)
		if (this.endsWith(suffix, ignoreCase))
			return true
	return false
}

//转换方法

/**
 * 将字符串转换为camelCase。
 */
fun String.toCamelCase(): String {
	throw NotImplementedError()
}

/**
 * 转换字符串的显示格式。
 */
fun String.switchCase(fromCase: StringCase, toCase: StringCase): String {
	throw NotImplementedError()
}

enum class StringCase {
	/**camelCase。*/
	CamelCase,
	/**PascalCase。*/
	PascalCase,
	/**SCREAMING_SNAKE_CASE。*/
	ScreamingSnakeCase,
	/**snake_case。*/
	SnakeCase,
	/**kebab-case。*/
	KebabCase,
	/**dot.case。*/
	DotCase,
	/**whiteSpace case。*/
	WhiteSpaceCase,
	/**lSep\\case。*/
	LSepCase,
	/**rSep/case。*/
	RSepCase
}

//控制方法

/**
 * 如果满足条件[condition]，则保留这段文本。
 */
fun String.where(condition: Boolean): String {
	return if (condition) "" else this
}

/**
 * 如果满足条件预测[predicate]，则保留这段文本。
 */
fun <T : Any> String.where(pointer: T, predicate: Predicate<T>): String {
	val condition = predicate.test(pointer)
	return where(condition)
}

//修改方法

/**
 * 根据指定的前缀[prefix]和后缀[suffix]，包围字符串。
 */
fun String.surround(prefix: String, suffix: String, ignoreEmpty: Boolean = true): String {
	val condition = ignoreEmpty && this.isEmpty()
	val result = prefix + this + suffix
	return if (condition) "" else result
}

/**
 * 根据指定的前后缀[fix]，包围字符串。
 */
fun String.surround(fix: String, ignoreEmpty: Boolean): String {
	return surround(fix, fix, ignoreEmpty)
}
