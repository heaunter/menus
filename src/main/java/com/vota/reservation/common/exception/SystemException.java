package com.vota.reservation.common.exception;

/**
 * 系统异常.
 */
public class SystemException extends Exception {

	private static final long serialVersionUID = 1957491434700297683L;

	public SystemException() {
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable e) {
		super(message, e);
	}

}
