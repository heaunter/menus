package com.vota.reservation.common.business;

import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.UUIDUtil;

/**
 * 业务码生成器 Created by mengzhg on 2017/3/31 21:43.
 */
public class BizCodeBuilder {

	/**
	 * 生成订单编号
	 *
	 * @return
	 */
	public static final String orderNo() {
		return DateUtil.formatDate(DateUtil.now(), DateUtil.NO_BLANK_SHORT_YEAR_PATTERN)
				.concat(UUIDUtil.getRandomNumber(3).toUpperCase());
	}

}
