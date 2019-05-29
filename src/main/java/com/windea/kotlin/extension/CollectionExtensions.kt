@file:Suppress("UNCHECKED_CAST")

package com.windea.kotlin.extension

import com.windea.kotlin.annotation.NotTested

//路径查找方法

/**
 * 得到指定路径 [path] 对应的值列表。根路径应该表示一个对象/映射（的键）。
 * 允许的子路径格式：
 * * [] 表示一个数组。
 * * [1..10] 表示一个所应在指定范围内的数组。包含上下限。
 * * [1] 表示一个数组的索引。
 * * {} 表示一个对象/映射。
 * * {Category} 表示一个注为指定占位符的对象/映射。
 * * re:Name.* 表示一个属性/键匹配指定正则的对象/映射。
 * * Name 表示一个对象/映射的属性/键。
 */
@NotTested
fun <K, V> Map<K, V>.fromPath(path: String): List<Any?> = fromPath(this, path)

/**
 * 得到指定路径 [path] 对应的值列表。根路径应该表示一个数组（的索引）。
 * 允许的子路径格式：
 * * [] 表示一个数组。
 * * [1..10] 表示一个所应在指定范围内的数组。包含上下限。
 * * [1] 表示一个数组的索引。
 * * {} 表示一个对象/映射。
 * * {Category} 表示一个注为指定占位符的对象/映射。
 * * re:Name.* 表示一个属性/键匹配指定正则的对象/映射。
 * * Name 表示一个对象/映射的属性/键。
 */
@NotTested
fun <E> List<E>.fromPath(path: String): List<Any?> = fromPath(this, path)

private fun fromPath(input: Any?, path: String): List<Any?> {
	val fixedPath = path.trim().removePrefix("/")
	val subPaths = fixedPath.split("/")
	var valueList = listOf(input)
	
	for(subPath in subPaths) {
		valueList = when {
			//如果子路径表示一个数组，例如："[]"
			subPath == "[]" -> {
				valueList.flatMap { it as List<Any?> }
			}
			//如果子路径表示一个范围，例如："[1..10]"
			subPath matches Regex("\\d+\\.\\.\\d+") -> {
				val (fromIndex, toIndex) = subPath.removeSurrounding("[", "]").split("..").map { it.toInt() }
				valueList.flatMap { (it as List<Any?>) }.subList(fromIndex, toIndex + 1)
			}
			//如果子路径表示一个数组索引，例如："[1]"
			subPath matches Regex("\\d+") -> {
				val index = subPath.removeSurrounding("[", "]").toInt()
				valueList.map { (it as List<Any?>)[index] }
			}
			//如果子路径表示一个对象，例如："{}"
			subPath == "{}" -> {
				valueList.flatMap { (it as Map<String, Any>).values }
			}
			//如果子路径表示一个占位符，例如："{Category}"
			subPath matches Regex("\\{.+}") -> {
				valueList.flatMap { (it as Map<String, Any?>).values }
			}
			//如果子路径表示一个正则表达式，例如："re:Name.*"
			subPath matches Regex("re:.+") -> {
				val pattern = subPath.removePrefix("re:")
				valueList.flatMap { (it as Map<String, Any?>).filterKeys { k -> k matches Regex(pattern) }.values }
			}
			//如果是其他情况，例如："Name"
			else -> {
				valueList.map { (it as Map<String, Any?>)[subPath] }
			}
		}
	}
	return valueList
}
