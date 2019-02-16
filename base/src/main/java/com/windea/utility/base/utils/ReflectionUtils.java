/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.utils;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 反射的工具类
 * @noinspection unused, WeakerAccess
 */
@SuppressWarnings("unchecked")
public class ReflectionUtils {
	private ReflectionUtils() {}

	/**
	 * 创建一个对象。如果发生异常，则返回null。
	 * @param clazz 对象对应的Class类
	 * @param <T> 对象的类型
	 * @return 一个新的对象
	 */
	public static <T> T create(@NotNull Class<T> clazz) {
		T obj = null;
		try {
			obj = clazz.getConstructor().newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static String getName(@NotNull Object obj) {
		return obj.getClass().getName();
	}

	public static String getType(@NotNull Object obj) {
		return obj.getClass().getTypeName();
	}

	/**
	 * 得到指定字段的值。
	 * @param obj 指定的对象
	 * @param fieldName 指定的字段名
	 * @return 指定字段的值
	 */
	public static Object getFieldValue(@NotNull Object obj, @NotNull String fieldName) {
		try {
			return obj.getClass().getDeclaredField(fieldName).get(obj);
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * 得到指定类的属性图表。
	 * @param clazz 指定类对应的Class类
	 * @param <T> 指定类
	 * @return 指定类的属性图表
	 */
	public static <T> Map<String, Field> getPropMap(@NotNull Class<T> clazz) {
		var methods = clazz.getMethods();
		var propNameList = new ArrayList<String>();
		for(var method : methods) {
			if(method.getName().startsWith("get") || method.getName().startsWith("set")) {
				var name = method.getName().substring(3);
				if(!propNameList.contains(name)) {
					propNameList.add(name);
				}
			}
		}
		var propMap = new HashMap<String, Field>();
		for(var propName : propNameList) {
			try {
				var field = clazz.getDeclaredField("propName");
				propMap.put(propName, field);
			} catch(Exception ignored) {}
		}
		return propMap;
	}
}
