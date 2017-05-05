package com.vota.reservation.modules.cms.dao;

import com.vota.reservation.modules.cms.entity.model.SysLogModel;

/**
 * 日志记录
 * 
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
public interface SysLogDao {

	/**
	 * 保存LOG对象.
	 * 
	 * @param log
	 *            the log
	 */
	public void saveLog(SysLogModel log);

}