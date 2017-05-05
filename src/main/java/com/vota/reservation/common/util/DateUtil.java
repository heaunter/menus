package com.vota.reservation.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间的工具类
 * 
 * @author sunhao(sunhao.java gmail.com)
 */
public final class DateUtil extends org.apache.commons.lang3.time.DateUtils {

	/**
	 * 私有化构造器
	 */
	private DateUtil() {
	}

	/** yyyy-MM-dd HH:mm:ss. */
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** yyyy-MM-dd. */
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * yyyyMMddHHmmss.
	 */
	public static final String NO_BLANK_PATTERN = "yyyyMMddHHmmss";

	/**
	 * yyMMddHHmmss.
	 */
	public static final String NO_BLANK_SHORT_YEAR_PATTERN = "yyMMddHHmmss";

	/**
	 * 获取当前系统日历的辅助变量.
	 */
	private static Calendar calendar;

	static {
		calendar = Calendar.getInstance();
	}

	/**
	 * 把字符串转成日期型
	 * 
	 * @param date
	 *            the date
	 * @return date
	 */
	public static Date parseDate(final String date) {
		if (StringUtil.isEmpty(date)) {
			return null;
		}
		String pat = DEFAULT_PATTERN;
		try {
			return new SimpleDateFormat(pat).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 把字符串转成日期型
	 * 
	 * @param date
	 *            the date
	 * @param pattern
	 *            the pattern
	 * @return date date
	 */
	public static Date parseDate(final String date, final String pattern) {
		if (StringUtil.isBlank(date)) {
			return null;
		}
		String pat = pattern;
		if (StringUtil.isEmpty(pat)) {
			pat = DEFAULT_PATTERN;
		}
		try {
			return new SimpleDateFormat(pat).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将日期型转成字符串型
	 * 
	 * @param date
	 *            the date
	 * @param pattern
	 *            the pattern
	 * @return string
	 */
	public static String formatDate(Date date, String pattern) {
		if (StringUtil.isEmpty(pattern)) {
			pattern = DEFAULT_PATTERN;
		}

		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * Format date string.
	 * 
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatDate(Date date) {
		String pattern = DEFAULT_PATTERN;
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 获取给定时间的那天的最后时刻
	 * 
	 * @param day
	 *            给定时间(em.2011-01-25 22:11:00...)
	 * @return 给定时间的那天的最后时刻(em.2011 0125 235959...)
	 */
	public static Date getEndOfDay(Date day) {
		if (day == null)
			day = new Date();
		calendar.setTime(day);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * 获取给定时间的那天的开始时刻
	 * 
	 * @param day
	 *            给定时间(em.2011-01-25 22:11:00...)
	 * @return 给定时间的那天的最后时刻(em.2011 0125 000000...)
	 */
	public static Date getStartOfDay(Date day) {
		if (day == null)
			day = new Date();
		calendar.setTime(day);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * 获取给定时间的那个月的最后时刻
	 * 
	 * @param day
	 *            给定时间(em.2011-01-25 22:11:00...)
	 * @return 给定时间的那个月的最后时刻(em.2011 0131 235959...)
	 */
	public static Date getEndOfMonth(Date day) {
		if (day == null)
			day = new Date();
		calendar.setTime(day);

		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));

		calendar.set(Calendar.DAY_OF_MONTH, 1);

		calendar.add(Calendar.MONTH, 1);

		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return calendar.getTime();
	}

	/**
	 * 获取给定时间的那个月的开始时刻
	 * 
	 * @param day
	 *            给定时间(em.2011-01-25 22:11:00...)
	 * @return 给定时间的那个月的开始时刻(em.2011 0101 000000...)
	 */
	public static Date getStartOfMonth(Date day) {
		if (day == null)
			day = new Date();
		calendar.setTime(day);

		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));

		calendar.set(Calendar.DAY_OF_MONTH, 1);

		return calendar.getTime();
	}

	/**
	 * 获取给定时间的那天的正午时刻
	 * 
	 * @param day
	 *            给定时间(em.2011-01-25 22:11:00...)
	 * @return 给定时间的那天的最后时刻(em.2011 0125 120000...)
	 */
	public static Date getNoonOfDay(Date day) {
		if (day == null)
			day = new Date();
		calendar.setTime(day);
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
		return calendar.getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return the current time
	 */
	public static Date now() {
		return new Date();
	}

	public static Date tomorrow() {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date yesterday() {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取给定日期的星期数
	 * 
	 * @param prefix
	 *            前缀 ep.'星期'
	 * @param date
	 *            给定日期 eg.'2012-01-25 23:07:58'
	 * @return 前缀 +(星期数) ep.'星期三'
	 */
	public static String getDayOfWeek(String prefix, Date date) {
		final String dayNames[] = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;

		return prefix + dayNames[dayOfWeek];
	}

	/**
	 * 获取给定日期的星期数(默认前缀:'星期')
	 * 
	 * @param date
	 *            the date
	 * @return day of week
	 */
	public static String getDayOfWeek(Date date) {
		return getDayOfWeek("星期", date);
	}

	/**
	 * 获取当前日历所属的年.
	 * 
	 * @return 当前日历所属的年. current year
	 */
	public static int getCurrentYear() {
		calendar.setTimeInMillis(System.currentTimeMillis());
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取当前日历所属的月，月份是从1开始的.
	 * 
	 * @return 当前日历所属的月. current month
	 */
	public static int getCurrentMonth() {
		calendar.setTimeInMillis(System.currentTimeMillis());
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 根据时间戳获取时间.
	 * 
	 * @param timestamp
	 *            the timestamp
	 * @return the date
	 */
	public static Date getDate(long timestamp) {
		if (String.valueOf(timestamp).length() == 10) {
			return new Date(timestamp * 1000);
		}
		return new Date(timestamp);

	}

	/**
	 * 根据时间戳获取时间.
	 * 
	 * @param timestamp
	 *            the timestamp
	 * @return the date
	 */
	public static Date getDate(String timestamp) {
		if (timestamp.length() == 10) {
			return new Date(Long.valueOf(timestamp) * 1000);
		}
		return new Date(Long.valueOf(timestamp));

	}
}
