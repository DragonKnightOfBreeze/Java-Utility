package com.windea.kotlin.generator

import com.windea.java.exception.NotImplementedException
import com.windea.kotlin.utils.YamlUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import java.util.*

class JsonSchemaGen private constructor(private val schemaMap: Map<*, *>) : TextGen<JsonSchemaGen> {

	private val ruleSet: MutableSet<JsonSchemaRule>? = null


	//配置生成器

	fun addRules(vararg rules: JsonSchemaRule): JsonSchemaGen {
		ruleSet!!.addAll(Arrays.asList(*rules))
		return this
	}

	//解析约束映射


	//输出

	fun generate(jsonSchemaPath: String) {
		throw NotImplementedException()
	}

	override fun text(): String {
		throw NotImplementedException()
	}

	@Deprecated("", ReplaceWith("text()"))
	override fun toString(): String {
		return text()
	}

	companion object {
		private val log = LogFactory.getLog(JsonSchemaGen::class.java)

		@Throws(Exception::class)
		private fun fromYaml(yamlPath: String): JsonSchemaGen {
			val schemaMap = YamlUtils.fromFile(yamlPath)
			return JsonSchemaGen(schemaMap)
		}
	}
}
