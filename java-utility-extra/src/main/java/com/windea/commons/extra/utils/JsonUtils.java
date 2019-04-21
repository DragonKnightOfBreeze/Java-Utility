package com.windea.commons.extra.utils;

import com.windea.commons.base.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.json.*;

import java.io.*;

/**
 * JSON的工具类
 * <br>使用官方的json插件。
 * @noinspection unused, WeakerAccess
 */
public class JsonUtils {

	private JsonUtils() {}

	/**
	 * 快速生成JSON对象。
	 * @param k1 第一个键
	 * @param v1 第一个值
	 */
	@NotNull
	public static JSONObject of(@NotNull String k1, Object v1) {
		return new JSONObject().put(k1, v1);
	}

	/**
	 * 快速生成JSON对象。
	 * @param k1 第一个键
	 * @param v1 第一个值
	 * @param other 其他的键和值
	 */
	@NotNull
	public static JSONObject of(@NotNull String k1, Object v1, Object... other) {
		//可变参数的长度应该为偶数
		if(other.length % 2 != 0)
			throw new IllegalArgumentException("Vararg params's length is not a even!");

		boolean isKey = true;
		String tempKey = null;

		JSONObject json = new JSONObject().put(k1, v1);
		for(Object o : other) {
			if(isKey) {
				//如果other[i]是一个字符串，且不为null，不为空白，才认为是合法的
				if(o instanceof String) {
					tempKey = (String) o;
					if(StringUtils.isBlank(tempKey)) {
						throw new IllegalArgumentException("Vararg param which presents a key is null or blank!");
					}
				} else {
					throw new IllegalArgumentException("Vararg param which presents a key is not a String!");
				}
			} else {
				json.put(tempKey, o);
			}
			isKey = !isKey;
		}
		return json;
	}

	/**
	 * 快速生成JSON数组。
	 * @param e1 第1个元素
	 */
	@NotNull
	public static JSONArray of2(Object e1) {
		return new JSONArray().put(e1);
	}

	/**
	 * 快速生成JSON数组。
	 * @param v1 第1个元素
	 * @param other 后续的元素
	 */
	@NotNull
	public static JSONArray of2(Object v1, Object... other) {
		JSONArray json = new JSONArray().put(v1);
		for(Object o : other) {
			json.put(o);
		}
		return json;
	}


	/**
	 * 根据指定的路径，读取JSON文件，得到JSON对象。
	 * <p>路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 * @param filePath 要读取的JSON文件的路径
	 */
	@NotNull
	public static JSONObject from(@NotNull String filePath) throws IOException {
		if(!filePath.endsWith(".json"))
			throw new IllegalArgumentException("`" + filePath + "` is not a json file.");

		return new JSONObject(new JSONTokener(new FileReader(filePath)));
	}

	/**
	 * 根据指定的路径，读取JSON文件，得到JSON数组。
	 * <p>路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 * @param filePath 要读取的JSON文件的路径
	 */
	@NotNull
	public static JSONArray from2(@NotNull String filePath) throws IOException {
		if(!filePath.endsWith(".json"))
			throw new IllegalArgumentException("`" + filePath + "` is not a json file.");

		return new JSONArray(new JSONTokener(new FileReader(filePath)));
	}


	/**
	 * 根据指定的JSON对象和路径，写入JSON文件。
	 * <p>默认缩进为4空格。
	 * <br>路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 * @param json 指定的JSON对象
	 * @param filePath 要写入的JSON文件的路径
	 */
	public static void to(@NotNull JSONObject json, @NotNull String filePath)
	throws IOException {
		to(json, filePath, 4);
	}

	/**
	 * 根据指定的JSON对象和路径，写入JSON文件。
	 * <p>默认缩进为4空格
	 * <br>路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 * @param json 指定的JSON对象
	 * @param filePath 要写入的JSON文件的路径
	 * @param indent 指定的缩进
	 */
	public static void to(@NotNull JSONObject json, @NotNull String filePath, int indent)
	throws IOException {
		if(!filePath.endsWith(".json"))
			throw new IllegalArgumentException("`" + filePath + "` is not a json fileName.");

		try(FileWriter writer = new FileWriter(filePath)) {
			writer.write(json.toString(indent));
		}
	}

	/**
	 * 根据指定的JSON对象和路径，写入JSON文件。
	 * <p>路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 * @param json 指定的JSON数组
	 * @param filePath 要写入的JSON文件的路径
	 */
	public static void to2(@NotNull JSONArray json, @NotNull String filePath)
	throws IOException {
		to2(json, filePath, 4);
	}

	/**
	 * 根据指定的JSON对象和路径，写入JSON文件。
	 * <p>路径可以是绝对地址，也可以相对于项目地址并不以/或\\开头。
	 * @param json 指定的JSON数组
	 * @param filePath 要写入的JSON文件的路径
	 * @param indent 指定的缩进
	 */
	public static void to2(@NotNull JSONArray json, @NotNull String filePath, int indent)
	throws IOException {
		if(!filePath.endsWith(".json"))
			throw new IllegalArgumentException("`" + filePath + "` is not a json fileName.");

		try(FileWriter writer = new FileWriter(filePath)) {
			writer.write(json.toString(indent));
		}
	}
}
