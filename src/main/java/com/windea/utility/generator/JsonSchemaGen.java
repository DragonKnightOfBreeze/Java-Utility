package com.windea.utility.generator;

import com.windea.utility.exception.NotImplementedException;
import com.windea.utility.utils.YamlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

public class JsonSchemaGen implements TextGen {
	private static final Log log = LogFactory.getLog(JsonSchemaGen.class);

	private Map schemaMap;

	private Set<JsonSchemaRule> ruleSet;


	private JsonSchemaGen(Map schemaMap) {
		this.schemaMap = schemaMap;
	}

	private static JsonSchemaGen fromYaml(String yamlPath) throws Exception {
		var schemaMap = YamlUtils.fromFile(yamlPath);
		return new JsonSchemaGen(schemaMap);
	}


	//配置生成器

	public JsonSchemaGen addRules(JsonSchemaRule... rules) {
		ruleSet.addAll(Arrays.asList(rules));
		return this;
	}

	//解析约束映射


	//输出

	public void generate(String jsonSchemaPath) {
		throw new NotImplementedException();
	}

	@Override
	public String text() {
		throw new NotImplementedException();
	}

	@Deprecated
	@Override
	public String toString() {
		return text();
	}
}
