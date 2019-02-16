/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.ext;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * 集合的拓展类
 */
public class SetExt {
	private SetExt() {}

	/**
	 * 判断集合是否为null、为空。
	 * <p>空值安全。
	 * @param set 指定的泛型集合
	 */
	@Contract(value = "null -> true", pure = true)
	public static <E> boolean isEmpty(@Nullable Set<E> set) {
		return set == null || set.isEmpty();
	}

	/**
	 * 判断集合是否为null、小于等于指定长度。
	 * <p>空值安全。
	 * @param set 指定的泛型集合
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <E> boolean isLessE(@Nullable Set<E> set, int length) {
		return set == null || set.size() <= length;
	}

	/**
	 * 判断集合是否大于等于指定长度。
	 * <p>空值安全。
	 * @param set 指定的泛型集合
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <E> boolean isGreaterE(@Nullable Set<E> set, int length) {
		return set != null && set.size() <= length;
	}
}

