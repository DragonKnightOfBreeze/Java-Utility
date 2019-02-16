/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.ext;

import com.windea.utility.base.annotation.Outlook;
import com.windea.utility.base.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

public class ObjectExt {
	/**
	 * 经过处理的对象哈希码方法。
	 * @param obj 指定的对象
	 * @param props 需要参与计算的属性
	 * @return 处理后的哈希码
	 */
	@Outlook(fromRef = "org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(..)")
	public static <T> int objHashcode(@Nullable T obj, @Nullable Object... props) {
		if(obj == null)
			return 0;
		var hashcode = Objects.hash(props);
		if(hashcode == 0) {
			var id = ReflectionUtils.getFieldValue(obj, "id");
			hashcode = Objects.hashCode(id);
		}
		if(hashcode == 0) {
			hashcode = Objects.hashCode(obj);
		}
		return hashcode;
	}

	/**
	 * 经过处理的对象比较方法。
	 * @param obj 第一个对象
	 * @param secObj 第二个对象
	 * @return 比较结果
	 */
	@Outlook(fromRef = "org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(..)")
	public static <T1, T2> boolean objEquals(@Nullable T1 obj, @Nullable T2 secObj) {
		if(Objects.equals(obj, secObj))
			return true;

		var id = ReflectionUtils.getFieldValue(obj, "id");
		var secId = ReflectionUtils.getFieldValue(secObj, "id");
		return Objects.equals(id, secId);
	}

	/**
	 * 将对象转化为格式化后的字符串。
	 * <p>空值安全。
	 * @param obj 指定的对象
	 * @return 转化后的字符串
	 */
	@Outlook(fromRef = "org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(..)")
	public static @NotNull <T> String objToString(@Nullable T obj) {
		if(obj == null)
			return "(obj is null)";
		var builder = new StringBuilder();

		var objName = obj.getClass().getName();
		builder.append(objName);

		builder.append("[");
		var fieldList = new ArrayList<Field>();

		var clazzIndex = obj.getClass();
		while(!StringExt.equals(clazzIndex.getName(), "java.lang.Object")) {
			var fields = clazzIndex.getDeclaredFields();
			fieldList.addAll(Arrays.asList(fields));
			clazzIndex = clazzIndex.getSuperclass();
		}

		for(var field : fieldList) {
			field.setAccessible(true);
			var name = field.getName();
			Object value;
			try {
				value = field.get(obj);
			} catch(IllegalAccessException e) {
				value = ("(cannot access)");
			}
			builder.append(name).append("=").append(value);
			builder.append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append("]");
		builder.append("\n");

		return builder.toString();
	}
}
