/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.utility.base.sample;

import com.windea.utility.base.ext.ObjectExt;

import java.io.Serializable;

/**
 * 基础的JavaBean
 */
public class Bean implements Serializable {
	private static final long serialVersionUID = 2823698203996429144L;

	@Override
	public boolean equals(Object obj) {
		return ObjectExt.objEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return ObjectExt.objHashcode(this);
	}

	@Override
	public String toString() {
		return ObjectExt.objToString(this);
	}
}
