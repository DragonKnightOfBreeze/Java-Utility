@file:Suppress("UNCHECKED_CAST")

package com.windea.kotlin.generator

import com.windea.kotlin.utils.JsonUtils
import com.windea.kotlin.utils.PathUtils
import com.windea.kotlin.utils.YamlUtils
import org.apache.commons.logging.LogFactory
import java.nio.file.Files
import java.nio.file.Path

/**
 * Intellij IDEA动态模版配置文件的生成器。
 */
class IdeaLiveTemplateGenerator : ITextGenerator {
	// 结构：
	// definitions/$templateName/description
	// definitions/$templateName/properties/$paramName/description
	// definitions/$templateName/properties/$paramName/default
	private var configMap: MutableMap<String, Any?> = HashMap()
	private var configName: String = "Custom Template"
	private var configText: String = "<!-- Generate From Kotlin Script Written By DragonKnightOfBreeze. -->\n"
	
	
	override fun execute() {
		generateYamlAnnotation()
	}
	
	private fun generateYamlAnnotation() {
		val definitions = configMap["definitions"] as Map<String, Map<String, Any?>>
		
		configText += """
		|<templateSet group = "$configName">
		|${definitions.map { (templateName, template) ->
			val description = template["description"]
			val params = if (template.containsKey("properties"))
				template["properties"] as Map<String, Map<String, Any?>>
			else
				HashMap()
			val paramSnippet = if (params.isNotEmpty())
				": {${params.entries.joinToString(", ") { (k, v) -> "$k: $$v$" }}"
			else
				""
			
			//value的格式示例：@Scope: {scope: $scope$, ...}
			"""
			|  <template name="$templateName" value="$templateName$paramSnippet"
		    |            description="$description"
		    |            toReformat="true" toShortenFQNames="true" useStaticImport="true">
			|${params.entries.joinToString("\n") { (paramName, param) ->
				val defaultValue = param.getOrDefault("default", "")
				
				"""
				|    <variable name="$paramName" expression="" defaultValue="&quot;$defaultValue&quot;" alwaysStopAt="true"/>
				""".trimMargin()
			}}
			|    <context>
			|      <option name="CSS" value="false"/>
			|      <option name="CUCUMBER_FEATURE_FILE" value="false"/>
			|      <option name="CoffeeScript" value="false"/>
			|      <option name="DART" value="false"/>
			|      <option name="ECMAScript6" value="false"/>
			|      <option name="HAML" value="false"/>
			|      <option name="HTML" value="false"/>
			|      <option name="Handlebars" value="false"/>
			|      <option name="JADE" value="false"/>
			|      <option name="JAVA_SCRIPT" value="false"/>
			|      <option name="JSON" value="false"/>
			|      <option name="JSP" value="false"/>
			|      <option name="OTHER" value="true"/>
			|      <option name="PL/SQL" value="false"/>
			|      <option name="REQUEST" value="false"/>
			|      <option name="SQL" value="false"/>
			|      <option name="TypeScript" value="false"/>
			|      <option name="Vue" value="false"/>
			|      <option name="XML" value="false"/>
			|    </context>
			|  </template>
			""".trimMargin()
		}.joinToString("\n")}
		|</templateSet>
		""".trimMargin()
	}
	
	override fun generate(outputPath: String) {
		Files.writeString(Path.of(outputPath), configText)
	}
	
	
	companion object {
		private val log = LogFactory.getLog(IdeaLiveTemplateGenerator::class.java)
		
		
		/**
		 * 从指定路径 [jsonPath] 的json schema文件读取数据映射。
		 */
		fun fromJson(jsonPath: String): IdeaLiveTemplateGenerator {
			val generator = IdeaLiveTemplateGenerator()
			generator.configMap = JsonUtils.fromFile(jsonPath).toMutableMap()
			return generator
		}
		
		/**
		 * 从指定路径 [yamlPath] 的yaml schema文件读取数据映射。
		 */
		fun fromYaml(yamlPath: String): IdeaLiveTemplateGenerator {
			val generator = IdeaLiveTemplateGenerator()
			generator.configName = PathUtils.subFileExt(yamlPath)
			generator.configMap = YamlUtils.fromFile(yamlPath).toMutableMap()
			return generator
		}
	}
}