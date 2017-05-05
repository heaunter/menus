package com.vota.reservation.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.vota.reservation.common.exception.SystemException;

/**
 * 对字符串进行处理的工具类
 * 
 * @author sunhao(sunhao.java@gmail.com)
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

	private static final String DEFAULT_DELIMITERS = ",";

	/**
	 * 私有化构造器
	 */
	private StringUtil() {
	}

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 判断是否是非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 传入的字符串是否在传入的数组中
	 * 
	 * @param strings
	 * @param string
	 * @return
	 */
	public static boolean contains(String[] strings, String string) {
		if (strings == null || strings.length == 0)
			return false;
		if (string == null)
			return false;

		for (int i = 0; i < strings.length; i++) {
			if (strings[i].equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串转成base64的
	 * 
	 * @param baseString
	 * @return
	 */
	public static String encodeBase64(String baseString) {
		String result = null;

		if (isNotBlank(baseString)) {
			result = new String(Base64.encodeBase64(baseString.getBytes()));
		}

		return result;
	}

	/**
	 * base64的字符串转成正常字符串(解码)
	 * 
	 * @param baseString
	 * @return
	 */
	public static String decodeBase64(String baseString) {
		String result = null;

		if (isNotBlank(baseString)) {
			result = new String(Base64.decodeBase64(baseString));
		}

		return result;
	}

	/**
	 * 将list中元素转化成字符串类型。list中值为null的元素将被删除
	 * 
	 * @param list
	 *            要被转化的集合
	 * @return 包含字符串的集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toStringList(List list) {
		List result = new ArrayList(list.size());
		Object obj;
		for (int i = 0; i < list.size(); i++) {
			obj = list.get(i);
			if (obj != null) {
				result.add(obj.toString());
			}
		}
		return result;
	}

	/**
	 * 有待研究
	 * 
	 * @param strcontent
	 * @param oldstr
	 * @param newstr
	 * @param len
	 * @return
	 */
	public static String replaceString(String strcontent, String oldstr, String newstr, int len) {
		StringBuffer buffer = new StringBuffer();
		int pos = 0;
		int i = 0;
		for (i = strcontent.indexOf(oldstr, pos); i >= 0; i = strcontent.indexOf(oldstr, pos)) {
			buffer.append(strcontent.substring(pos, i));
			buffer.append(newstr);
			pos = i + len;
		}

		buffer.append(strcontent.substring(pos));
		return buffer.toString();
	}

	/**
	 * 按传入的字符分隔字符串
	 * 
	 * @param str
	 * @param character
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] split(String str, String character) {
		StringTokenizer stk = new StringTokenizer(str, character);
		List list = new ArrayList();
		for (; stk.hasMoreTokens(); list.add(stk.nextToken()))
			;
		String strs[] = new String[list.size()];
		list.toArray(strs);
		return strs;
	}

	/**
	 * 传入的字符串是否在传入的数组中
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public static boolean contain(String string, String strings[]) {
		if (strings == null)
			return false;
		for (int i = 0; i < strings.length; i++) {
			String s = strings[i];
			if (string.equals(s))
				return true;
		}

		return false;
	}

	/**
	 * 获取字符串中两个字符中间的那段字符串
	 * 
	 * @param string
	 * @param first
	 * @param second
	 * @return
	 */
	public static String getBetweenTwoLetters(String string, String first, String second) {
		if (isEmpty(string)) {
			return EMPTY;
		} else {
			int firstIndex = StringUtil.indexOf(string, first);
			int secondIndex = StringUtil.indexOf(string, second);

			return StringUtil.substring(string, firstIndex + 1, secondIndex);
		}
	}

	/**
	 * 对StringBuffer切割
	 * 
	 * @param src
	 *            源
	 * @param start
	 * @param end
	 * @return
	 */
	public static StringBuffer substringbuffer(StringBuffer src, int start, int end) {
		String result = substring(src.toString(), start, end);

		return new StringBuffer(result);
	}

	/**
	 * 对StringBuffer切割
	 * 
	 * @param src
	 *            源
	 * @param start
	 * @return
	 */
	public static StringBuffer substringbuffer(StringBuffer src, int start) {
		String result = substring(src.toString(), start);

		return new StringBuffer(result);
	}

	/**
	 * 获取给定字符串中符合正则表达式的片段个数
	 * 
	 * @param text
	 *            给定字符串
	 * @param excerpt
	 *            片段的正则表达式
	 * @return
	 */
	public static int hasExcerpt(String text, String excerpt) {
		Assert.hasText(text, "text不能为空！");
		Assert.hasText(excerpt, "excerpt不能为空！");

		Pattern pattern = Pattern.compile(excerpt);
		Matcher matcher = pattern.matcher(text);

		int i = 0;
		while (matcher.find()) {
			i++;
		}

		return i;
	}

	/**
	 * 将给定的字符串按<code>character</code>分割后，转换成指定类型的数据类型的集合
	 * 
	 * @param str
	 *            给定的字符串
	 * @param character
	 *            分割符
	 * @param parse
	 *            类型转换器
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> convert(String str, String character, StringParse<T> parse) {
		if (StringUtil.isEmpty(str) || StringUtil.isEmpty(character) || parse == null) {
			return Arrays.asList();
		}

		String[] temps = split(str, character);
		List<T> result = new ArrayList<T>();
		for (String t : temps) {
			result.add(parse.parse(t));
		}

		return result;
	}

	/**
	 * 将给定的字符串按{@link #DEFAULT_DELIMITERS}分割后，转换成指定类型的数据类型的集合
	 * 
	 * @param str
	 *            给定的字符串
	 * @param parse
	 *            类型转换器
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> convert(String str, StringParse<T> parse) {
		return convert(str, DEFAULT_DELIMITERS, parse);
	}

	/**
	 * list转成字符串数组
	 * 
	 * @param list
	 *            集合
	 * @param containNull
	 *            当为null的时候，是否放入空字符串
	 * @return
	 */
	public static String[] convert(List<?> list, boolean containNull) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		List<String> ss = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (obj == null && !containNull) {
				continue;
			}

			if (obj == null) {
				ss.add(StringUtil.EMPTY);
			} else {
				ss.add(obj.toString());
			}
		}

		return ss.toArray(new String[ss.size()]);
	}

	/**
	 * 校验请求参数.
	 * 
	 * @param reqJsonObject
	 *            the req json object
	 * @param args
	 *            the args
	 * @throws SystemException
	 *             the system exception
	 */
	public static void verifyReqParam(JSONObject reqJsonObject, String... args)
			throws SystemException {
		logger.debug("--->Request json:{}.", reqJsonObject.toJSONString());
		for (int i = 0; i < args.length; i++) {
			if (!reqJsonObject.containsKey(args[i])) {
				throw new SystemException("参数[" + args[i] + "]为必填项");
			}
		}
	}
}
