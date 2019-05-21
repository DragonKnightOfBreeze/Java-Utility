package com.windea.utility.utils;

public class JsonYamlUtils {
	private JsonYamlUtils() {
	}


	public void jsonToYamlFile(String path) throws Exception {
		jsonToYamlFile(path, PathUtils.changeFileExt(path, ".yml"));
	}

	public void jsonToYamlFile(String jsonPath, String yamlPath) throws Exception {
		var data = JsonUtils.fromFile(jsonPath);
		YamlUtils.toFile(data, yamlPath);
	}

	public void yamlToJsonFile(String path) throws Exception {
		yamlToJsonFile(path, PathUtils.changeFileExt(path, ".json"));
	}

	public void yamlToJsonFile(String yamlPath, String jsonPath) throws Exception {
		var data = YamlUtils.fromFile(yamlPath);
		JsonUtils.toFile(data, jsonPath);
	}
}
