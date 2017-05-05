package com.vota.reservation.common.exception;

/**
 * 缓存操作异常
 */
public class CacheException extends Exception {

	private static final long serialVersionUID = 1812763357136779342L;

	public CacheException(String message) {
		super(message);
	}

	public CacheException(String message, Throwable e) {
		super(message, e);
	}
}
