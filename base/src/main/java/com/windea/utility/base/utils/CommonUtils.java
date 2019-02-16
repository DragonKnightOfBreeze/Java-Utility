/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.utils;

import com.windea.utility.base.annotation.Outlook;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * 通用的工具类
 * @noinspection unused, WeakerAccess
 */
public class CommonUtils {
	private CommonUtils() {}

	/**
	 * 模拟空指针检查（如果前者为空，则执行后者对应的操作）。
	 * @param obj 要检查的对象
	 * @param handler 要检查的对象为空时执行的lambda表达式
	 * @param <T> 要检查的对象的类型
	 * @return 处理后的对象，或者抛出异常
	 */
	@Outlook
	@NotNull
	public static <T> T npc(@Nullable T obj, @NotNull Supplier<T> handler) {
		if(obj == null) {
			return handler.get();
		} else {
			return obj;
		}
	}

	/**
	 * 模拟空指针检查（如果前者为空，则执行后者对应的操作）。
	 * @param objGetter 得到要检查的对象的lambda表达式
	 * @param handler 要检查的对象为空时执行的lambda表达式
	 * @param <T> 要检查的对象的类型
	 * @return 处理后的对象，或者抛出异常
	 */
	@Outlook
	@NotNull
	public static <T> T npc(@Nullable Supplier<T> objGetter, @NotNull Supplier<T> handler) {
		T obj = objGetter != null ? objGetter.get() : null;
		if(obj == null) {
			return handler.get();
		} else {
			return obj;
		}
	}

	/**
	 * 模拟错误检查（尝试返回前者，如果出现异常，则返回后者）。
	 * @param obj 首先尝试返回的对象
	 * @param secObj 发生异常时尝试返回的对象
	 * @param <T> 要检查的对象的类型
	 * @return 最终返回的对象
	 */
	public static <T> T ec(T obj, T secObj) {
		try {
			return obj;
		} catch(Exception e) {
			return secObj;
		}
	}
}
