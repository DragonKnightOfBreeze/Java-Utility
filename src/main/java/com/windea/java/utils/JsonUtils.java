package com.windea.java.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

public class JsonUtils {
	private JsonUtils() {
	}


	public static Map fromFile(String path) throws Exception {
		return fromFile(path, Map.class);
	}

	public static <T> T fromFile(String path, Class<T> type) throws Exception {
		var reader = new FileReader(path);
		T result = json().fromJson(reader, type);
		return result;
	}

	public static Map fromString(String string) {
		return fromString(string, Map.class);
	}

	public static <T> T fromString(String string, Class<T> type) {
		var result = json().fromJson(string, type);
		return result;
	}

	public static <T> void toFile(T data, String path) throws Exception {
		toFile(data, path, 2);
	}

	public static <T> void toFile(T data, String path, int indent) throws Exception {
		var writer = new JsonWriter(new FileWriter(path));
		var type = new TypeToken<T>() {}.getType();
		writer.setIndent(" ".repeat(indent));
		json().toJson(data, type, writer);
	}

	public static <T> String toString(T json) {
		return json().toJson(json);
	}


	private static Gson json() {
		return new Gson();
	}
}
