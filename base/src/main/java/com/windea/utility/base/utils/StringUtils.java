/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.utils;

import com.windea.utility.base.annotation.Outlook;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * 字符串的工具类
 * @noinspection unused, WeakerAccess
 */
public class StringUtils {
	private StringUtils() {}

	/**
	 * 模拟模版字符串。
	 * <p>自动转义单引号。
	 * @param pattern 模版字符串
	 * @param args 参数
	 * @see MessageFormat
	 */
	@Outlook(from = "JavaX")
	@NotNull
	public static String format(@NotNull String pattern, @NotNull Object... args) {
		return MessageFormat.format(pattern.replace("'", "''"), args);
	}

	/**
	 * 模拟多行字符串。
	 * @param strArray 每行的字符串
	 */
	@Outlook(from = "JavaX")
	@NotNull
	public static String multiline(@NotNull String... strArray) {
		return String.join("\n", strArray);
	}
}
