package com.windea.kotlin.extension

import java.util.function.Predicate

//判断方法

/**
 * 判断字符串是否以指定的任意前缀 [prefixes] 开始。
 */
fun String.startsWith(vararg prefixes: String, ignoreCase: Boolean = false): Boolean {
	for (prefix in prefixes) {
		if (this.startsWith(prefix, ignoreCase)) {
			return true
		}
	}
	return false
}

/**
 * 判断字符串是否以指定的任意后缀 [suffixes] 开始。
 */
fun String.endsWith(vararg suffixes: String, ignoreCase: Boolean = false): Boolean {
	for (suffix in suffixes) {
		if (this.endsWith(suffix, ignoreCase)) {
			return true
		}
	}
	return false
}

//转换方法

/**
 * 转换字符串的显示格式。
 */
fun String.switchCase(fromCase: StringCase, toCase: StringCase): String {
	TODO()
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
 * 如果满足条件 [condition]，则保留这段文本。
 */
fun String.where(condition: Boolean): String {
	return if (condition) "" else this
}

/**
 * 如果满足条件预测 [predicate]，则保留这段文本。
 */
fun <T : Any> String.where(pointer: T, predicate: Predicate<T>): String {
	val condition = predicate.test(pointer)
	return where(condition)
}

//修改方法

/**
 * 根据指定的前缀 [prefix] 和后缀 [suffix]，包围字符串。
 */
fun String.surround(prefix: String, suffix: String, ignoreEmpty: Boolean = true): String {
	val condition = ignoreEmpty && this.isEmpty()
	val result = prefix + this + suffix
	return if (condition) "" else result
}

/**
 * 根据指定的前后缀 [fix]，包围字符串。
 */
fun String.surround(fix: String, ignoreEmpty: Boolean): String {
	return surround(fix, fix, ignoreEmpty)
}

//路径相关

/**
 * 根据当前路径片段以及附加的路径片段 [paths]，组成完整路径。允许混合两种分隔符。
 */
fun String.pathJoin(vararg paths: String): String {
	return listOf(this, *paths).joinToString("\\") { it.replace("/", "\\") }
}

/**
 * 根据当前文件路径，得到包含文件名、文件拓展名等信息的路径信息对象。
 */
fun String.pathSplit(): PathInfo {
	val dirIndex = this.lastIndexOf("\\")
	val fileDir = if (dirIndex == -1) "" else this.substring(0, dirIndex)
	val fileName = if (dirIndex == -1) this else this.substring(dirIndex + 1)
	
	val extIndex = fileName.lastIndexOf(".")
	val fileShotName = if (extIndex == -1) fileName else fileName.substring(0, extIndex)
	val fileExt = if (extIndex == -1) "" else fileName.substring(extIndex)
	
	return PathInfo(fileDir, fileName, fileShotName, fileExt)
}

/**
 * 路径信息对象。
 */
class PathInfo(
	/**文件所在文件夹。*/
	val fileDir: String,
	/**文件名。*/
	val fileName: String,
	/**不包含扩展名在内的文件名。*/
	val fileShotName: String,
	/**包含"."的文件扩展名。*/
	val fileExt: String
) {
	/**是否存在上一级文件夹。*/
	val hasDir = fileDir == ""
	/**是否存在文件扩展名。*/
	val hasExt = fileExt == ""
	
	
	/**更改文件所在文件夹为新的文件夹 [newFileDir]。*/
	fun changeFileDir(newFileDir: String): String {
		return newFileDir + "\\" + fileName
	}
	
	/**更改文件名为新的文件名 [newFileName]。*/
	fun changeFileName(newFileName: String): String {
		return fileDir + "\\" + newFileName
	}
	
	/**更改不包含扩展名在内的文件名为新的文件名 [newFileShotName]，可指定是否返回全路径 [forFullPath]。*/
	fun changeFileShotName(newFileShotName: String, forFullPath: Boolean = false): String {
		val newFileName = newFileShotName + fileExt
		return if (forFullPath) fileDir + "\\" + newFileName else newFileName
	}
	
	/**更改文件扩展名为新的扩展名 [newFileExt]，可指定是否返回全路径 [forFullPath]。*/
	fun changeFileExt(newFileExt: String, forFullPath: Boolean = false): String {
		val newFileName = fileShotName + newFileExt
		return if (forFullPath) fileDir + "\\" + newFileName else newFileName
	}
}
