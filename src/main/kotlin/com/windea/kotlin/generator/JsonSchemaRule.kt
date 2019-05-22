@file:Suppress("unused", "UNUSED_PARAMETER", "MemberVisibilityCanBePrivate")

package com.windea.kotlin.generator

/**
 * 可自定义的Json Schema规则接口/lambda。
 */
interface JsonSchemaRule {
	fun doRule(originRule: Map<String, Any?>): Map<String, Any?>
}
