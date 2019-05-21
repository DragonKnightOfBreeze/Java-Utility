package com.windea.java.utils;

import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class YamlUtils {
	private static LoaderOptions loaderOptions = new LoaderOptions();
	private static DumperOptions dumperOptions = new DumperOptions();

	static {
		loaderOptions.setAllowDuplicateKeys(false);
		dumperOptions.setAllowReadOnlyProperties(true);
		dumperOptions.setIndent(2);
		dumperOptions.setTimeZone(TimeZone.getTimeZone("GMT-8:00"));
		dumperOptions.setWidth(120);
		dumperOptions.setAllowUnicode(true);
	}


	private YamlUtils() {
	}


	public static void setLoaderOptions(LoaderOptions options) {
		loaderOptions = options;
	}

	public static void setDumperOptions(DumperOptions options) {
		dumperOptions = options;
	}

	public static void setTags(Map<String, String> tagMap) {
		dumperOptions.setTags(tagMap);
	}

	public static Map fromFile(String path) throws Exception {
		return fromFile(path, Map.class);
	}

	public static <T> T fromFile(String path, Class<T> type) throws Exception {
		var reader = new FileReader(path);
		T result = yaml().loadAs(reader, type);
		return result;
	}

	public static Map fromString(String string) {
		return fromString(string, Map.class);
	}

	public static <T> T fromString(String string, Class<T> type) {
		T result = yaml().loadAs(string, type);
		return result;
	}

	public static List<Object> fromFileAll(String path) throws Exception {
		var reader = new FileReader(path);
		var resultList = new ArrayList<>();
		for(var elem : yaml().loadAll(reader)) {
			resultList.add(elem);
		}
		return resultList;
	}

	public static List<Object> fromStringAll(String string) {
		var resultList = new ArrayList<>();
		for(var elem : yaml().loadAll(string)) {
			resultList.add(elem);
		}
		return resultList;
	}

	public static <T> void toFile(T data, String path) throws Exception {
		var writer = new FileWriter(path);
		yaml().dump(data, writer);
	}

	public static <T> String toString(T data) {
		return yaml().dump(data);
	}

	public static <T> void toFileAll(List<T> dataList, String path) throws Exception {
		var writer = new FileWriter(path);
		yaml().dumpAll(dataList.iterator(), writer);
	}

	public static <T> String toStringAll(List<T> dataList) {
		return yaml().dumpAll(dataList.iterator());
	}


	private static Yaml yaml() {
		var constructor = new Constructor();
		var representer = new Representer();
		return new Yaml(constructor, representer, dumperOptions, loaderOptions);
	}
}
