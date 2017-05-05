package com.vota.reservation.common.constant;

/**
 * 餐品时段 Created by mengzhg on 2017/3/23.
 */
public enum MealPeriodEnum {

	/**
	 * Breakfast meal period enum.
	 */
	BREAKFAST(1, "早餐", "早"),
	/**
	 * Lunch meal period enum.
	 */
	LUNCH(2, "午餐", "午"),
	/**
	 * Dinner meal period enum.
	 */
	DINNER(3, "晚餐", "晚");

	MealPeriodEnum(int code, String longName, String shortName) {
		this.code = code;
		this.longName = longName;
		this.shortName = shortName;
	}

	private int code;

	private String longName;

	private String shortName;

	/**
	 * Gets code.
	 * 
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets code.
	 * 
	 * @param code
	 *            the code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets long name.
	 * 
	 * @return the long name
	 */
	public String getLongName() {
		return longName;
	}

	public String getLongName(String code) {
		
		return longName;
	}

	/**
	 * Sets long name.
	 * 
	 * @param longName
	 *            the long name
	 */
	public void setLongName(String longName) {
		this.longName = longName;
	}

	/**
	 * Gets short name.
	 * 
	 * @return the short name
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * Sets short name.
	 * 
	 * @param shortName
	 *            the short name
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
