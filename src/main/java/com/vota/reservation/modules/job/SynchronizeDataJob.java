package com.vota.reservation.modules.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.modules.cms.entity.dto.HisGetPatientResponseDto;
import com.vota.reservation.modules.cms.entity.dto.PatientInfoDto;
import com.vota.reservation.modules.cms.entity.model.SysLogModel;
import com.vota.reservation.modules.cms.service.AuditLogService;
import com.vota.reservation.modules.cms.service.HisService;
import com.vota.reservation.modules.cms.service.UserService;

/**
 * 同步数据 Created by mengzhg on 2017/3/17.
 */
@Component
public class SynchronizeDataJob {

	private Logger logger = LoggerFactory.getLogger(SynchronizeDataJob.class);

	@Autowired
	private HisService hisService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuditLogService auditLogService;

	@Scheduled(cron = "0 0/15 * * * ?")
	public void synchronizeData() {
		try {
			Date yesterday = DateUtil.yesterday();
			SysLogModel log = new SysLogModel();
			log.setType(3);
			log.setRequestObject("后台定时JOB-开始执行");
			log.setRemoteAddr("system-localhost");
			log.setParams(DateUtil.formatDate(yesterday));
			auditLogService.save(log);

			// 取前一天的数据
			HisGetPatientResponseDto patientInfos = hisService.queryDischargePatientInfo(yesterday, yesterday);
			if (patientInfos == null) {
				return;
			}
			List<PatientInfoDto> curs = patientInfos.getV_CUR();
			if (curs == null || curs.size() <= 0) {
				return;
			}

			for (int i = 0; i < curs.size(); i++) {
				PatientInfoDto dto = curs.get(i);
				if (dto.getINP_NO().startsWith("-")) {
					logger.warn("cancle the hospitalized:" + JSON.toJSONString(dto));
					continue;
				}
				int processCount = userService.updatePatientInfo(dto.getINP_NO(), 9, dto.getOUT_HOS_DATE());
				if (processCount <= 0) {
					logger.warn("The patient is not exits in system, patient no={}", dto.getINP_NO());
				}
			}

			SysLogModel log2 = new SysLogModel();
			log2.setType(3);
			log2.setRequestObject("后台定时JOB-执行成功");
			log2.setRemoteAddr("system-localhost");
			log2.setParams(DateUtil.formatDate(yesterday));
			auditLogService.save(log2);

		} catch (SystemException e) {
			logger.error("Failed to update discharge patient information.", e);
			SysLogModel log3 = new SysLogModel();
			log3.setType(3);
			log3.setRequestObject("后台定时JOB-执行失败");
			log3.setRemoteAddr("system-localhost");
			log3.setParams(e.getMessage());
			auditLogService.save(log3);
		}
	}

}
