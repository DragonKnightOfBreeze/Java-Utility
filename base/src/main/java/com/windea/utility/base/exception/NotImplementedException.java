/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.exception;

/**
 * 未完成的异常
 */
public class NotImplementedException extends RuntimeException {
	private static final long serialVersionUID = -1298081639241671242L;

	/**
	 * 实例化未完成的异常。
	 */
	public NotImplementedException() {
		System.out.println("Not implemented code!");
	}

	/**
	 * 实例化未完成的异常。
	 */
	public NotImplementedException(String message) {
		super(message);
		System.out.println("Not implemented code!");
	}
}
