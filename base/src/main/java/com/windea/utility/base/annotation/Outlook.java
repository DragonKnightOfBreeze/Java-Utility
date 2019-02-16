/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.annotation;

import org.intellij.lang.annotations.Language;

import java.lang.annotation.Documented;

/**
 * 有望找到替代方式的代码的注解
 */
@Documented
public @interface Outlook {
	/** 可能的替代方案。 */
	String[] from() default {};

	/** 可能的替代方法的引用。 */
	@Language("PointcutExpression")
	String[] fromRef() default {};
}
