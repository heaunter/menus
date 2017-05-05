package com.vota.reservation.modules.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.vota.reservation.common.exception.CacheException;
import com.vota.reservation.modules.cms.dao.SysConfigDao;
import com.vota.reservation.modules.cms.entity.bean.AppVersionBean;
import com.vota.reservation.modules.cms.entity.model.SysConfigModel;

/**
 * 配置信息Service
 * 
 * @author mengzhg
 */
@Service
public class SysConfigService {

	@Autowired
	private SysConfigDao sysConfigDao;

	/**
	 * 获取APP版本信息
	 * 
	 * @return
	 * @throws CacheException
	 */
	public AppVersionBean getAppVersion() throws CacheException {
		SysConfigModel model = sysConfigDao.getConfiguration("app_version");
		AppVersionBean bean = JSON.parseObject(model.getValue(), AppVersionBean.class);
		return bean;
	}

}
