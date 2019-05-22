package com.windea.kotlin.generator

import com.windea.kotlin.utils.JsonUtils
import com.windea.kotlin.utils.YamlUtils
import org.apache.commons.logging.LogFactory
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class JsonSchemaGen private constructor() : TextGen<JsonSchemaGen> {
	private var schemaMap: MutableMap<String, Any?> = HashMap()
	private var ruleSet: MutableSet<JsonSchemaRule> = HashSet()


	//属性

	override val text: String
		get() = JsonUtils.toString(schemaMap)

	//配置生成器

	fun addRules(vararg rules: JsonSchemaRule): JsonSchemaGen {
		ruleSet.addAll(Arrays.asList(*rules))
		return this
	}

	//解析约束映射


	//输出

	fun generate(jsonSchemaPath: String) {
		JsonUtils.toFile(schemaMap, jsonSchemaPath)
	}

	override fun toString(): String {
		return text
	}

	//伴生生成方法

	companion object {
		private val log = LogFactory.getLog(JsonSchemaGen::class.java)

		@Throws(Exception::class)
		private fun fromYaml(yamlPath: String): JsonSchemaGen {
			val gen = JsonSchemaGen()
			gen.schemaMap = YamlUtils.fromFile(yamlPath).toMutableMap()
			return gen
		}
	}
}
