package com.vota.reservation.common.util;

/**
 * 类型转换器的接口.
 *
 * @author mengzhg
 * @version V1.0
 * @since 2015-2-9 22:07
 */
public interface StringParse<V> {
    /**
     * 转换
     *
     * @param str 待转换的数据
     * @return The V
     */
    V parse(String str);
}
