package com.vota.reservation.common.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * 加密工具类.
 * 
 * @version V1.0
 * @createTime 2015-1-6 20:57
 */
public class EncryptUtils {

	/**
	 * 用MD5算法进行加密
	 * 
	 * @param plainText
	 *            明文，需要加密的字符串
	 * @return MD5加密后的结果
	 */
	public static String encodeMD5(String plainText) {
		return encode(plainText, EncryptType.MD5);
	}

	/**
	 * 用SHA算法进行加密
	 * 
	 * @param plainText
	 *            明文，需要加密的字符串
	 * @return SHA加密后的结果
	 */
	public static String encodeSHA(String plainText) {
		return encode(plainText, EncryptType.SHA);
	}

	/**
	 * 用base64算法进行加密
	 * 
	 * @param plainText
	 *            明文，需要加密的字符串
	 * @return base64加密后的结果
	 */
	public static String encodeBase64(String plainText) {
		Base64 encoder = new Base64();
		return encoder.encodeAsString(plainText.getBytes());
	}

	/**
	 * 用base64算法进行解密
	 * 
	 * @param cipherText
	 *            密文，需要解密的字符串
	 * @return base64解密后的结果
	 * @throws IOException
	 */
	public static String decodeBase64(String cipherText) {
		Base64 decoder = new Base64();
		return new String(decoder.decode(cipherText));
	}

	/**
	 * 根据类型加密
	 * 
	 * @param plainText
	 *            明文
	 * @param encryptType
	 *            加密类型
	 * @return
	 */
	private static String encode(String plainText, EncryptType encryptType) {
		String dstr = null;
		try {
			MessageDigest md = MessageDigest.getInstance(encryptType.name());
			md.update(plainText.getBytes());
			dstr = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return dstr;
	}

	/**
	 * 加密类型
	 */
	private enum EncryptType {
		MD5, SHA
	}
}
