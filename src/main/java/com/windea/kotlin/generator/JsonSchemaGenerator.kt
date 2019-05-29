@file:Suppress("UNCHECKED_CAST")

package com.windea.kotlin.generator

import com.windea.kotlin.annotation.NotTested
import com.windea.kotlin.extension.fromPath
import com.windea.kotlin.utils.JsonUtils
import com.windea.kotlin.utils.YamlUtils
import org.apache.commons.logging.LogFactory

/**
 * Json Schema的生成器。
 */
@NotTested
class JsonSchemaGenerator private constructor() : ITextGenerator {
	private var schemaMap: MutableMap<String, Any?> = HashMap()
	private var dataMap: MutableMap<String, Any?> = HashMap()
	private var annotationMap: MutableMap<String, Any?> = HashMap()
	private var ruleMap: MutableMap<String, JsonSchemaRule> = HashMap()
	
	
	fun addRules(rules: Map<String, JsonSchemaRule>): JsonSchemaGenerator {
		//在这里使用+=作为替代或出现方法调用冲突错误，不知道为什么？
		ruleMap.putAll(rules)
		return this
	}
	
	override fun execute(): JsonSchemaGenerator {
		//递归遍历整个约束映射的深复制，处理原本的约束映射
		//如果找到了自定义规则，则替换成规则集合中指定的官方规则
		addDefaultRules()
		doRulesRec(schemaMap)
		return this
	}
	
	private fun addDefaultRules(): Map<String, JsonSchemaRule> {
		return mutableMapOf(
			"language" to { (_, value) ->
				//更改为Idea扩展规则
				mapOf("x-intellij-language-injection" to value)
			},
			"deprecated" to { (_, value) ->
				//更改为Idea扩展规则
				when(value) {
					is String -> mapOf("deprecationMessage" to value)
					true -> mapOf("deprecationMessage" to "")
					else -> mapOf()
				}
			},
			"enumSchema" to { (_, value) ->
				//提取路径`enumSchema/value`对应的值列表
				val enumConsts = (value as List<Map<String, Any?>>).map { it["value"] }
				mapOf("enum" to enumConsts)
			},
			"generatedFrom" to { (_, value) ->
				//提取$dataMap中的路径`$value`对应的值列表
				val enumConsts = dataMap.fromPath(value as String)
				mapOf("enum" to enumConsts)
			},
			"allowedAnnotation" to { (_, _) ->
				//NOTE 不生成对应的附加约束，将其归为特殊注释
				mapOf()
			}
		)
	}
	
	//标记为尾部调用以增强性能
	private tailrec fun doRulesRec(map: MutableMap<String, Any?>) {
		map.keys.forEach { key ->
			//如果值为映射，则继续向下递归遍历，否则检查是否匹配规则名
			val value = map[key]
			if(value is MutableMap<*, *>) {
				return doRulesRec(value as MutableMap<String, Any?>)
			} else {
				//如果找到了对应规则名的规则，则执行规则并替换
				ruleMap[key]?.let {
					val newRule = it.invoke(Pair(key, value))
					//居然还能直接这样写？
					map -= key
					map += newRule
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
		 * 从指定路径 [schemaPath] 的扩展json schema文件读取数据映射。
		 */
		fun fromExtJsonSchema(schemaPath: String, dataPath: String, annotationPath: String): JsonSchemaGenerator {
			val generator = JsonSchemaGenerator()
			generator.schemaMap = JsonUtils.fromFile(schemaPath).toMutableMap()
			generator.dataMap = JsonUtils.fromFile(dataPath).toMutableMap()
			generator.annotationMap = JsonUtils.fromFile(annotationPath).toMutableMap()
			return generator
		}
		
		/**
		 * 从指定路径 [schemaPath] 的扩展yaml schema文件读取数据映射。
		 */
		fun fromExtYamlSchema(schemaPath: String, dataPath: String, annotationPath: String): JsonSchemaGenerator {
			val generator = JsonSchemaGenerator()
			generator.schemaMap = YamlUtils.fromFile(schemaPath).toMutableMap()
			generator.dataMap = JsonUtils.fromFile(dataPath).toMutableMap()
			generator.annotationMap = JsonUtils.fromFile(annotationPath).toMutableMap()
			return generator
		}
	}
}
