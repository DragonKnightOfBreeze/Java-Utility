package com.windea.kotlin.generator.impl

import com.windea.kotlin.generator.ITextGenerator
import org.apache.commons.logging.LogFactory
import java.nio.file.Files
import java.nio.file.Path

/**
 * Markdown文本的生成器。
 */
class MarkdownGenerator private constructor() : ITextGenerator {
	private var markdownText: String = "<!-- Generated from kotlin script written by DragonKnightOfBreeze.\n -->"
	
	
	override fun execute(): ITextGenerator {
		TODO()
	}
	
	override fun generate(outputPath: String) {
		Files.writeString(Path.of(outputPath), markdownText)
	}
	
	
	companion object {
		@JvmStatic
		private val log = LogFactory.getLog(ITextGenerator::class.java)
		
		
		/**
		 * 从指定路径 [dataPath] 的json文件读取数据映射。
		 */
		@JvmStatic
		fun fromJson(dataPath: String): MarkdownGenerator {
			TODO()
		}
		
		/**
		 * 从指定路径 [dataPath] 的yaml文件读取数据映射。
		 */
		@JvmStatic
		fun fromYaml(dataPath: String): MarkdownGenerator {
			TODO()
		}
	}
}
