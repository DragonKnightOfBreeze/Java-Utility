package com.windea.java.generator;

import java.util.Map;

public interface JsonSchemaRule {
	Map doRule(Map originRule);
}
