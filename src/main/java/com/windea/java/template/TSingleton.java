package com.windea.java.template;

import com.windea.java.annotation.PerformanceAffectPossible;

/**
 * 通用单例类 - 双重校验锁。
 */
@PerformanceAffectPossible
@SuppressWarnings("unchecked")
public class TSingleton {
	private static volatile TSingleton instance;

	private TSingleton() { }


	/**
	 * 得到单例实例。
	 */
	public static <T extends TSingleton> T getInstance(Class<T> clazz) throws Exception {
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
