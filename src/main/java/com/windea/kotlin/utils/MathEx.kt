package com.windea.kotlin.utils

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

object MathEx {
	private val log: Log = LogFactory.getLog(MathEx::class.java)

	/**
	 * 最小值方法。可以指定适用处理后的警告消息 [message] 。
	 */
	fun min(number: Int, min: Int, message: String? = null): Int {
		if (number >= min)
			return number
		//如果message不为空则执行这段代码
		message?.apply { log.warn(message) }
		return min
	}

	/**
	 * 最小值方法。可以指定适用处理后的警告消息 [message] 。
	 */
	fun min(number: Float, min: Float, message: String? = null): Float {
		if (number >= min)
			return number
		message?.apply { log.warn(message) }
		return min
	}

	/**
	 * 最大值方法。可以指定适用处理后的警告消息 [message] 。
	 */
	fun max(number: Int, max: Int, message: String? = null): Int {
		if (number <= max)
			return number
		message?.apply { log.warn(message) }
		return max
	}

	/**
	 * 最大值方法。可以指定适用处理后的警告消息 [message] 。
	 */
	fun max(number: Float, max: Float, message: String? = null): Float {
		if (number <= max)
			return number
		message?.apply { log.warn(message) }
		return max
	}

	/**
	 * 夹值方法。可以指定适用处理后的警告消息 [message] 。
	 */
	fun clamp(number: Int, min: Int, max: Int, message: String? = null): Int {
		if (number in min..max)
			return number
		message?.apply { log.warn(message) }
		return if (number < min) min else max
	}

	/**
	 * 夹值方法。可以指定适用处理后的警告消息 [message] 。
	 */
	fun clamp(number: Float, min: Float, max: Float, message: String? = null): Float {
		if (number in min..max)
			return number
		message?.apply { log.warn(message) }
		return if (number < min) min else max
	}

	/**
	 * 夹值方法，从0到1。可以指定适用处理后的警告消息 [message] 。
	 */
	fun clamp01(number: Float, min: Float, max: Float, message: String? = null): Float {
		return clamp(number, 0f, 1f, message)
	}

	/**
	 * 夹值方法，从-1到1。可以指定适用处理后的警告消息 [message] 。
	 */
	fun clampAbs1(number: Float, min: Float, max: Float, message: String? = null): Float {
		return clamp(number, -1f, 1f, message)
	}

	fun lerp(number: Float, target: Float, speed: Float): Float {
		TODO()
	}
}
