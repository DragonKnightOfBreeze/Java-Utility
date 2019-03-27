/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.annotation;

import org.intellij.lang.annotations.Language;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Markdown注释的注解
 */
@SuppressWarnings("unused")
@Retention(RetentionPolicy.SOURCE)
public @interface Markdown {
	/** Markdown注释的内容。 */
	@Language("Markdown")
	String[] value() default {};

	/** 是否启用拓展语法。例如，启用下标语法`[^n]`。 */
	boolean extend() default true;

	/** 是否启用CriticMarkup语法。例如 ，删除语法{--text--}。 */
	boolean criticMarkup() default false;

	/** Markdown注释对应的文件存储目录名。 */
	String dirName() default "";

	/** Markdown注释对应的文件名。 */
	String fileName() default "SUMMARY.md";
}
