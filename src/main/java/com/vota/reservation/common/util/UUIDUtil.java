package com.vota.reservation.common.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取UUID<无中划线>
     *
     * @return the string
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取指定长度的随机字符串
     *
     * @param length the length
     * @return the random string
     */
    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的随机数字.
     *
     * @param length the length
     * @return the random number
     */
    public static String getRandomNumber(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
