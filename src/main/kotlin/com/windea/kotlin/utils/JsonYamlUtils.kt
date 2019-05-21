package com.windea.kotlin.utils

object JsonYamlUtils {
	@Throws(Exception::class)
	fun jsonToYamlFile(path: String) {
		jsonToYamlFile(path, PathUtils.changeFileExt(path, ".yml"))
	}

	@Throws(Exception::class)
	fun jsonToYamlFile(jsonPath: String, yamlPath: String) {
		val data = JsonUtils.fromFile(jsonPath)
		YamlUtils.toFile(data, yamlPath)
	}

	@Throws(Exception::class)
	fun yamlToJsonFile(path: String) {
		yamlToJsonFile(path, PathUtils.changeFileExt(path, ".json"))
	}

	@Throws(Exception::class)
	fun yamlToJsonFile(yamlPath: String, jsonPath: String) {
		val data = YamlUtils.fromFile(yamlPath)
		JsonUtils.toFile(data, jsonPath)
	}
}
