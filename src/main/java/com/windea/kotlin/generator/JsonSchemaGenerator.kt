@file:Suppress("UNCHECKED_CAST")

package com.windea.kotlin.generator

import com.windea.java.annotation.NotTested
import com.windea.kotlin.utils.JsonUtils
import com.windea.kotlin.utils.YamlUtils
import org.apache.commons.logging.LogFactory

/**
 * Json Schema的生成器类。
 */
@NotTested
class JsonSchemaGenerator private constructor() : ITextGenerator {
	private var schemaMap: MutableMap<String, Any?> = HashMap()
	private var ruleMap: MutableMap<String, JsonSchemaRule> = HashMap()


	fun addRule(ruleName: String, rule: JsonSchemaRule): JsonSchemaGenerator {
		ruleMap[ruleName] = rule
		return this
	}

	override fun execute() {
		//递归遍历整个约束映射的深复制，处理原本的约束映射
		//如果找到了自定义规则，则替换成规则集合中指定的官方规则
		doRulesRec(schemaMap)
	}

	private fun doRulesRec(map: MutableMap<String, Any?>) {
		map.keys.forEach { key ->
			//如果值为映射，则继续向下递归遍历，否则检查是否匹配规则名
			val value = map[key]
			if (value is MutableMap<*, *>) {
				doRulesRec(value as MutableMap<String, Any?>)
			} else {
				//如果找到了对应规则名的规则，则执行规则并替换
				ruleMap[key]?.let {
					val newRule = it.invoke(Pair(key, value))
					map.remove(key)
					map.putAll(newRule)
				}
			}
		}
	}

	override fun generate(outputPath: String) {
		JsonUtils.toFile(schemaMap, outputPath)
	}


	companion object {
		private val log = LogFactory.getLog(JsonSchemaGenerator::class.java)
		
		
		/**
		 * 从指定路径 [jsonPath] 的拓展json schema文件读取数据映射。
		 */
		fun fromJson(jsonPath: String): JsonSchemaGenerator {
			val generator = JsonSchemaGenerator()
			generator.schemaMap = JsonUtils.fromFile(jsonPath).toMutableMap()
			generator.ruleMap = getDefaultRules().toMutableMap()
			return generator
		}
		
		/**
		 * 从指定路径 [yamlPath] 的拓展yaml schema文件读取数据映射。
		 */
		fun fromYaml(yamlPath: String): JsonSchemaGenerator {
			val generator = JsonSchemaGenerator()
			generator.schemaMap = YamlUtils.fromFile(yamlPath).toMutableMap()
			generator.ruleMap = getDefaultRules().toMutableMap()
			return generator
		}

		private fun getDefaultRules(): Map<String, JsonSchemaRule> {
			return mutableMapOf(
				"language" to { (_, value) ->
					mapOf("x-intellij-language-injection" to value)
				},
				"deprecated" to { (_, value) ->
					when (value) {
						is String -> mapOf("deprecationMessage" to value)
						true -> mapOf("deprecationMessage" to "")
						else -> mapOf()
					}
				},
				"enumSchema" to { (_, value) ->
					mapOf("enum" to (value as List<Map<String, Any?>>).map { it["value"] })
				},
				"generatedFrom" to { (_, value) ->
					TODO()
				},
				"allowedAnnotation" to { (_, value) ->
					TODO()
				}
			)
		}
	}
}
