package com.vota.reservation.common.exception;

/**
 * 鉴权异常.
 */
public class AccessTokenException extends Exception {

	private static final long serialVersionUID = -3419610904430286124L;

	private int code;
	private String message;

	public AccessTokenException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public AccessTokenException(int code, String message, Throwable e) {
		super(message, e);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
