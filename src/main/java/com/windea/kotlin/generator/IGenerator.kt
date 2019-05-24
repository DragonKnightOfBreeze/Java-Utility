package com.windea.kotlin.generator

/**
 * 生成器接口。
 */
interface IGenerator {
	fun execute(): IGenerator

	fun generate(outputPath: String)
}
