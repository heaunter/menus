package com.vota.reservation.common.util;

import org.springframework.core.NamedThreadLocal;

/**
 * ThreadLocal 工具类
 * 
 * @author mengzhg
 * @version 1.0
 */
public class ThreadLocalUtils {

	private static final NamedThreadLocal<Long> TIME_THREAD_LOCAL = new NamedThreadLocal<Long>("TIME");

	/**
	 * 存放时间
	 * 
	 * @param time
	 */
	public static final void putTime(long time) {
		TIME_THREAD_LOCAL.set(time);
	}

	/**
	 * 获取时间
	 */
	public static final long getTime() {
		return TIME_THREAD_LOCAL.get();
	}

	/**
	 * 删除时间
	 */
	public static final void removeTime() {
		TIME_THREAD_LOCAL.remove();
	}
}
