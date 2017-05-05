package com.vota.reservation.modules.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.modules.cms.dao.SysLogDao;
import com.vota.reservation.modules.cms.entity.model.SysLogModel;

@Service
public class AuditLogService {

	@Autowired
	private SysLogDao dao;

	public void save(SysLogModel log) {
		log.setCreateTime(DateUtil.now());
		dao.saveLog(log);
	}

}
