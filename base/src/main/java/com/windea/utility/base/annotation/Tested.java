/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.annotation;

import java.lang.annotation.*;

/**
 * 已测试的代码的注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Tested {
	String condition() default "";
}
