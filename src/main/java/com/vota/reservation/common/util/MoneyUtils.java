package com.vota.reservation.common.util;

import java.text.DecimalFormat;

/**
 * 金钱工具类.
 */
public class MoneyUtils {

	static DecimalFormat formatter = new DecimalFormat("#.##");

	public static Double format(double original) {
		return Double.parseDouble(formatter.format(original));
	}

	public static String format2String(double original) {
		return formatter.format(original);
	}

}
