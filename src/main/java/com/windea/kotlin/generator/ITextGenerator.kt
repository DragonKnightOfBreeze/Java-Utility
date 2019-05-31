package com.windea.kotlin.generator

/**
 * 文本生成器接口。
 */
interface ITextGenerator {
	fun execute(): ITextGenerator
	
	fun generate(outputPath: String)
}
