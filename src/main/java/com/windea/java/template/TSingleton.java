package com.windea.java.template;

import com.windea.java.annotation.PerformanceAffectPossible;

import java.lang.reflect.InvocationTargetException;

/**
 * 通用单例类 - 双重校验锁。
 */
@PerformanceAffectPossible
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
