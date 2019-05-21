package com.windea.kotlin.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonWriter

import java.io.FileReader
import java.io.FileWriter

object JsonUtils {
	@Throws(Exception::class)
	fun fromFile(path: String): Map<*, *> {
		return fromFile(path, Map::class.java)
	}

	@Throws(Exception::class)
	fun <T> fromFile(path: String, type: Class<T>): T {
		val reader = FileReader(path)
		return json().fromJson(reader, type)
	}

	fun fromString(string: String): Map<*, *> {
		return fromString(string, Map::class.java)
	}

	fun <T> fromString(string: String, type: Class<T>): T {
		return json().fromJson(string, type)
	}

	@Throws(Exception::class)
	fun <T> toFile(data: T, path: String) {
		toFile(data, path, 2)
	}

	@Throws(Exception::class)
	fun <T> toFile(data: T, path: String, indent: Int) {
		val writer = JsonWriter(FileWriter(path))
		val type = object : TypeToken<T>() {}.type
		writer.setIndent(" ".repeat(indent))
		json().toJson(data, type, writer)
	}

	fun <T> toString(json: T): String {
		return json().toJson(json)
	}


	private fun json(): Gson {
		return Gson()
	}
}
