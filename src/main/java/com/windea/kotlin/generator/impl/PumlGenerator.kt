package com.windea.kotlin.generator.impl

import com.windea.kotlin.generator.ITextGenerator
import com.windea.kotlin.utils.JsonUtils
import com.windea.kotlin.utils.YamlUtils
import org.apache.commons.logging.LogFactory
import java.nio.file.Files
import java.nio.file.Path

/**
 * Plant Uml代码的生成器。
 */
class PumlGenerator private constructor() : ITextGenerator {
	private var pumlMap: MutableMap<String, Any?> = HashMap()
	private var pumlText: String = "Generated from kotlin script written by DragonKnightOfBreeze.\n"
	
	
	override fun execute(): ITextGenerator {
		TODO()
	}
	
	override fun generate(outputPath: String) {
		Files.writeString(Path.of(outputPath), pumlText)
	}
	
	
	companion object {
		@JvmStatic
		private val log = LogFactory.getLog(ITextGenerator::class.java)
		
		
		/**
		 * 从指定路径 [dataPath] 的json文件读取数据映射。
		 */
		@JvmStatic
		fun fromJson(dataPath: String): PumlGenerator {
			val generator = PumlGenerator()
			generator.pumlMap = JsonUtils.fromFile(dataPath).toMutableMap()
			return generator
		}
		
		/**
		 * 从指定路径 [dataPath] 的yaml文件读取数据映射。
		 */
		@JvmStatic
		fun fromYaml(dataPath: String): PumlGenerator {
			val generator = PumlGenerator()
			generator.pumlMap = YamlUtils.fromFile(dataPath).toMutableMap()
			return generator
		}
	}
}
