package com.vota.reservation.modules.cms.dao;

import org.apache.ibatis.annotations.Param;

import com.vota.reservation.modules.cms.entity.model.SysConfigModel;

/**
 * 系统配置信息
 * 
 * @author mengzhg
 * @version 1.0
 * @since 2016/11/20 22:35
 */
public interface SysConfigDao {

	/**
	 * 获取最新的版本信息
	 * 
	 * @return the app version
	 */
	public SysConfigModel getConfiguration(@Param("code") String code);

	/**
	 * 保存最新版本信息.
	 * 
	 * @param model
	 *            the model
	 */
	public void insert(SysConfigModel model);

	/**
	 * 更新配置信息
	 * 
	 * @param json
	 *            the json
	 * @param json2
	 */
	public void updateConfig(@Param("code") String code, @Param("value") String json);
}