@file:Suppress("UNCHECKED_CAST")

package com.windea.kotlin.generator

import com.windea.kotlin.annotation.NotTested
import com.windea.kotlin.utils.JsonUtils
import com.windea.kotlin.utils.YamlUtils
import org.apache.commons.logging.LogFactory
import java.nio.file.Files
import java.nio.file.Path
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

/**
 * Sql语句的生成器。
 */
@NotTested
class SqlGenerator private constructor() : ITextGenerator {
	// 结构：
	// $DatabaseName/$table_name/$index/$column_name
	private var dataMap: MutableMap<String, Any?> = HashMap()
	private var sqlText: String = "-- Generated from kotlin script written by DragonKnightOfBreeze.\n"
	
	
	override fun execute(): SqlGenerator {
		generateSchemaText()
		generateDataText()
		return this
	}
	
	private fun generateSchemaText() {
		//NOTE 不生成，因为Spring Boot可以动态创建表
	}
	
	private fun generateDataText() {
		val databaseName = dataMap.keys.first()
		val database = dataMap[databaseName] as Map<String, List<Map<String, Any?>>>
		
		sqlText += """
		|use $databaseName;
		|
		|${database.entries.joinToString("\n\n") { (tableName, table) ->
			val columnNameSnippet = table[0].keys.joinToString(", ")
			
			"""
			|insert into $tableName ($columnNameSnippet) values
			|${table.joinToString(",\n", "", ";\n") { data ->
				val dataSnippet = data.values.joinToString(", ") { column -> escapeText(quoteText(column)) }
				
				"\t($dataSnippet)"
			}}
			""".trimMargin()
		}}
		""".trimMargin()
	}
	
	private fun quoteText(text: Any?): String {
		return when(text) {
			is String -> "'$text'"
			is Date -> "'${SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(text)}'"
			else -> text.toString()
		}
	}
	
	private fun escapeText(text: String): String {
		return text.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t")
	}
	
	override fun generate(outputPath: String) {
		Files.writeString(Path.of(outputPath), sqlText)
	}
	
	
	companion object {
		private val log = LogFactory.getLog(SqlGenerator::class.java)
		
		
		/**
		 * 从指定路径 [dataPath] 的json文件读取数据映射。
		 */
		fun fromJson(dataPath: String): SqlGenerator {
			val generator = SqlGenerator()
			generator.dataMap = JsonUtils.fromFile(dataPath).toMutableMap()
			return generator
		}
		
		/**
		 * 从指定路径 [dataPath] 的yaml文件读取数据映射。
		 */
		fun fromYaml(dataPath: String): SqlGenerator {
			val generator = SqlGenerator()
			generator.dataMap = YamlUtils.fromFile(dataPath).toMutableMap()
			return generator
		}
	}
}
