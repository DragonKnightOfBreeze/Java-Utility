package com.windea.kotlin.utils

import org.yaml.snakeyaml.*
import org.yaml.snakeyaml.constructor.Constructor
import org.yaml.snakeyaml.representer.Representer

import java.io.FileReader
import java.io.FileWriter
import java.util.*

object YamlUtils {
	private var loaderOptions = LoaderOptions()
	private var dumperOptions = DumperOptions()

	init {
		loaderOptions.isAllowDuplicateKeys = false
		dumperOptions.isAllowReadOnlyProperties = true
		dumperOptions.indent = 2
		dumperOptions.timeZone = TimeZone.getTimeZone("GMT-8:00")
		dumperOptions.width = 120
		dumperOptions.isAllowUnicode = true
	}


	fun setLoaderOptions(options: LoaderOptions) {
		loaderOptions = options
	}

	fun setDumperOptions(options: DumperOptions) {
		dumperOptions = options
	}

	fun setTags(tagMap: Map<String, String>) {
		dumperOptions.tags = tagMap
	}

	@Throws(Exception::class)
	fun fromFile(path: String): Map<*, *> {
		return fromFile(path, Map::class.java)
	}

	@Throws(Exception::class)
	fun <T> fromFile(path: String, type: Class<T>): T {
		val reader = FileReader(path)
		return yaml().loadAs(reader, type)
	}

	fun fromString(string: String): Map<*, *> {
		return fromString(string, Map::class.java)
	}

	fun <T> fromString(string: String, type: Class<T>): T {
		return yaml().loadAs(string, type)
	}

	@Throws(Exception::class)
	fun fromFileAll(path: String): List<Any> {
		val reader = FileReader(path)
		val resultList = ArrayList<Any>()
		for (elem in yaml().loadAll(reader)) {
			resultList.add(elem)
		}
		return resultList
	}

	fun fromStringAll(string: String): List<Any> {
		val resultList = ArrayList<Any>()
		for (elem in yaml().loadAll(string)) {
			resultList.add(elem)
		}
		return resultList
	}

	@Throws(Exception::class)
	fun <T> toFile(data: T, path: String) {
		val writer = FileWriter(path)
		yaml().dump(data, writer)
	}

	fun <T> toString(data: T): String {
		return yaml().dump(data)
	}

	@Throws(Exception::class)
	fun <T> toFileAll(dataList: List<T>, path: String) {
		val writer = FileWriter(path)
		yaml().dumpAll(dataList.iterator(), writer)
	}

	fun <T> toStringAll(dataList: List<T>): String {
		return yaml().dumpAll(dataList.iterator())
	}


	private fun yaml(): Yaml {
		val constructor = Constructor()
		val representer = Representer()
		return Yaml(constructor, representer, dumperOptions, loaderOptions)
	}
}
