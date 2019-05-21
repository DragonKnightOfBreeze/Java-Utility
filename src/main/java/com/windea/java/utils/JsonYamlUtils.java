package com.windea.java.utils;

public class JsonYamlUtils {
	private JsonYamlUtils() {
	}


	public static void jsonToYamlFile(String path) throws Exception {
		jsonToYamlFile(path, PathUtils.changeFileExt(path, ".yml"));
	}

	public static void jsonToYamlFile(String jsonPath, String yamlPath) throws Exception {
		var data = JsonUtils.fromFile(jsonPath);
		YamlUtils.toFile(data, yamlPath);
	}

	public static void yamlToJsonFile(String path) throws Exception {
		yamlToJsonFile(path, PathUtils.changeFileExt(path, ".json"));
	}

	public static void yamlToJsonFile(String yamlPath, String jsonPath) throws Exception {
		var data = YamlUtils.fromFile(yamlPath);
		JsonUtils.toFile(data, jsonPath);
	}
}
