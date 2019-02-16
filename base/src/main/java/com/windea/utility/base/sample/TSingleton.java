/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.sample;

import com.windea.utility.base.annotation.NotTested;

import java.lang.reflect.InvocationTargetException;

/**
 * 泛型单例模式 - 双重校验锁
 */
@NotTested
public class TSingleton {
	private static volatile TSingleton instance;

	private TSingleton() { }

	/**
	 * 得到单例实例。
	 */
	@SuppressWarnings("unchecked")
	public static <T extends TSingleton> T getInstance(Class<T> clazz)
	throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			InstantiationException {
		if(instance == null) {
			synchronized(TSingleton.class) {
				if(instance == null) {
					instance = (TSingleton) Class.forName(clazz.getName()).getDeclaredConstructor().newInstance();
				}
			}
		}
		return (T) instance;
	}
}
