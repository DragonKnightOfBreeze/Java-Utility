/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * 图表的拓展类
 */
public class MapExt {
	private MapExt() {}

	/**
	 * 判断图表是否为null、为空。
	 * <p>空值安全。
	 * @param map 指定的泛型图表
	 */
	@Contract(value = "null -> true", pure = true)
	public static <K, V> boolean isEmpty(@Nullable Map<K, V> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 判断图表是否为null、小于等于指定长度。
	 * <p>空值安全。
	 * @param map 指定的泛型图表
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <K, V> boolean isLessE(@Nullable Map<K, V> map, int length) {
		return map == null || map.size() <= length;
	}

	/**
	 * 判断图表是否大于等于指定长度。
	 * <p>空值安全。
	 * @param map 指定的泛型图表
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <K, V> boolean isGreaterE(@Nullable Map<K, V> map, int length) {
		return map != null && map.size() <= length;
	}
}

