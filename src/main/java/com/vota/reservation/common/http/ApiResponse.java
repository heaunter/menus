package com.vota.reservation.common.http;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Api响应.
 * 
 */
public class ApiResponse<T> implements Serializable {

	private static final long serialVersionUID = -6673568719411446296L;

	/** 返回码 */
	@JSONField(ordinal = 1)
	private int code = 0;

	/** 返回消息 */
	@JSONField(ordinal = 2)
	private String message;

	/** 返回数据 */
	@JSONField(ordinal = 3)
	private T data;

	public ApiResponse() {

	}

	public ApiResponse(int code, String message, T data) {
		this.setCode(code);
		this.setMessage(message);
		this.setData(data);
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code
	 *            the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data
	 *            the new data
	 */
	public void setData(T data) {
		this.data = data;
	}

}
