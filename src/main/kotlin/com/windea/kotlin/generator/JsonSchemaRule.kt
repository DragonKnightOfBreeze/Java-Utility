package com.windea.kotlin.generator

interface JsonSchemaRule {
	fun doRule(originRule: Map<*, *>): Map<*, *>
}
