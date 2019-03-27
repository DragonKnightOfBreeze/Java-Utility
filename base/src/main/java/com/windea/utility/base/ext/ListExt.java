/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 列表的拓展类
 */
public class ListExt {
	private ListExt() {}

	/**
	 * 判断列表是否为null、为空。
	 * <p>空值安全。
	 * @param list 指定的泛型列表
	 */
	@Contract(value = "null -> true", pure = true)
	public static <E> boolean isEmpty(@Nullable List<E> list) {
		return list == null || list.isEmpty();
	}

	/**
	 * 判断列表是否为null、小于等于指定长度。
	 * <p>空值安全。
	 * @param list 指定的泛型列表
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <E> boolean isLessE(@Nullable List<E> list, int length) {
		return list == null || list.size() <= length;
	}

	/**
	 * 判断列表是否大于等于指定长度。
	 * <p>空值安全。
	 * @param list 指定的泛型列表
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <E> boolean isGreaterE(@Nullable List<E> list, int length) {
		return list != null && list.size() <= length;
	}


}
