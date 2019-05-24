@file:Suppress("UNCHECKED_CAST")

package com.windea.kotlin.generator

import com.windea.kotlin.utils.JsonUtils
import com.windea.kotlin.utils.YamlUtils
import org.apache.commons.logging.LogFactory
import java.nio.file.Files.writeString
import java.nio.file.Path
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

/**
 * Sql数据语句的生成器类。
 */
class SqlGenerator private constructor() : ITextGenerator {
	//结构：DatabaseName/table_name/0/column_name
	private var dataMap: MutableMap<String, Any?> = HashMap()
	private var sqlText: String = "-- Generate From Kotlin Script Written By DragonKnightOfBreeze.\n"


	override fun execute() {
		generateSchemaText()
		generateDataText()
	}

	private fun generateSchemaText() {
		TODO()
	}

	private fun generateDataText() {
		val databaseName = dataMap.keys.first()
		val database = dataMap[databaseName] as Map<String, List<Map<String, Any?>>>

		sqlText += """
		|use $databaseName;
		|
		|${database.map { (tableName, table) ->
			"""
			|insert into $tableName (${table[0].keys.joinToString(", ")}) values
			|${table.map {
				"\t(${it.values.map { column -> escapeText(quoteText(column)) }})"
			}.joinToString(",\n", "", ";\n")}
			""".trimMargin()
		}.joinToString("\n\n")}
		""".trimMargin()
	}

	private fun quoteText(text: Any?): String {
		return when (text) {
			is String -> "'$text'"
			is Date -> "'${SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(text)}'"
			else -> text.toString()
		}
	}

	private fun escapeText(text: String): String {
		return text.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t")
	}

	override fun generate(outputPath: String) {
		writeString(Path.of(outputPath), sqlText)
	}


	companion object {
		private val log = LogFactory.getLog(SqlGenerator::class.java)


		fun fromJson(jsonPath: String): SqlGenerator {
			val generator = SqlGenerator()
			generator.dataMap = JsonUtils.fromFile(jsonPath).toMutableMap()
			return generator
		}

		fun fromYaml(yamlPath: String): SqlGenerator {
			val generator = SqlGenerator()
			generator.dataMap = YamlUtils.fromFile(yamlPath).toMutableMap()
			return generator
		}
	}
}
