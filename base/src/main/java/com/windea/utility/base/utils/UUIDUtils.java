/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.utils;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * uuid的工具类
 * @noinspection unused, WeakerAccess
 */
public class UUIDUtils {
	private UUIDUtils() {}

	/**
	 * 得到随机的uuid。
	 */
	@NotNull
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
