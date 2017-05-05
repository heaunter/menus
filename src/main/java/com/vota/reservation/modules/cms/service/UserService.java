package com.vota.reservation.modules.cms.service;

import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.EncryptUtils;
import com.vota.reservation.common.util.StringUtil;
import com.vota.reservation.modules.cms.dao.PatientInfoDao;
import com.vota.reservation.modules.cms.dao.UserDao;
import com.vota.reservation.modules.cms.entity.bean.PatientBean;
import com.vota.reservation.modules.cms.entity.bean.UserBean;
import com.vota.reservation.modules.cms.entity.dto.PatientInfoDto;
import com.vota.reservation.modules.cms.entity.model.PatientInfoModel;
import com.vota.reservation.modules.cms.entity.model.SysUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关Service
 * 
 * @author mengzhg@126.com
 */
@Service
public class UserService {

	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private PatientInfoDao patientInfoDao;
	@Autowired
	private HisService hisService;

	/**
	 * 管理员登录
	 * 
	 * @param loginId
	 * @param password
	 * @return
	 */
	public UserBean sysLogin(String loginId, String password) {
		SysUserModel model = userDao.login(loginId, EncryptUtils.encodeMD5(password));
		if (model == null) {
			return null;
		}
		UserBean user = new UserBean();
		user.from(model);
		return user;

	}

	/**
	 * 修改密码
	 * 
	 * @param loginId
	 * @param oldPass
	 * @param password
	 * @return
	 */
	public boolean modifyPassword(String loginId, String oldPass, String password) throws SystemException {
		if (StringUtil.isBlank(oldPass) || StringUtil.isBlank(password)) {
			throw new SystemException("密码不能为空");
		}
		SysUserModel usermodel = userDao.getUserInfo(loginId);
		if (usermodel == null) {
			throw new SystemException("用户不存在");
		}
		if (!usermodel.getPassword().equals(oldPass)) {
			throw new SystemException("旧密码错误，无法修改密码");
		}
		final int update = userDao.update(loginId, password);
		if (update <= 0) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * 获取所有的病人信息
	 * 
	 * @param size
	 * @param cursor
	 * 
	 * @return
	 */
	public List<PatientBean> getAllPatients(int cursor, int size) throws SystemException {
		try {
			if (cursor == 0) {// 只有第一次请求的时候，调用同步HIS方法
				logger.info("Sync user info from his system.");
				hisService.syncUserInfo();
			}
		} catch (Exception e) {
			logger.error("Failed to sync data", e);
		}
		List<PatientBean> result = patientInfoDao.getAllPatients(cursor * size, size);
		return result;
	}

	/**
	 * 获取某个病人的信息.
	 * 
	 * @param patientNo
	 *            the patient no
	 * @return the patient information
	 */
	public PatientInfoModel getPatientInformation(String patientNo) {
		return patientInfoDao.getPatientInformation(patientNo);
	}

	/**
	 * Save patient information.
	 * 
	 * @param dto
	 *            the dto
	 */
	public void savePatientInformation(PatientInfoDto dto, Long deptId, Long areaId, Long bedId, boolean ignoreUpdate)
			throws SystemException {
		PatientInfoModel patientInfoModel = patientInfoDao.getPatientInfoByNo(dto.getINP_NO());
		if (patientInfoModel == null) {
			patientInfoModel = new PatientInfoModel();
			patientInfoModel.setPatientNo(dto.getINP_NO());
			patientInfoModel.setName(dto.getNAME());
			patientInfoModel.setSex("F".equals(dto.getSEX()) ? "女" : "男");
			patientInfoModel.setAge(StringUtil.isBlank(dto.getAGE()) ? 0 : Integer.valueOf(dto.getAGE()));
			patientInfoModel.setDeptId(deptId);
			patientInfoModel.setAreaId(areaId);
			patientInfoModel.setBedId(bedId);
			patientInfoModel.setStatus(1);
			if (StringUtil.isNotBlank(dto.getOUT_HOS_DATE())) {
				patientInfoModel.setStatus(9);
			}
			patientInfoModel.setCreateTime(DateUtil.now());
			if (StringUtil.isNotBlank(dto.getIN_HOS_DATE())) {
				try {
					patientInfoModel.setCreateTime(DateUtil.parseDate(dto.getIN_HOS_DATE(), DateUtil.DEFAULT_PATTERN));
				} catch (Exception e) {
					logger.error("Failed to format.", e);
				}
			}
			patientInfoModel.setUpdateTime(DateUtil.now());
			if (StringUtil.isNotBlank(dto.getOUT_HOS_DATE())) {
				try {
					patientInfoModel.setUpdateTime(DateUtil.parseDate(dto.getOUT_HOS_DATE(), DateUtil.DEFAULT_PATTERN));
				} catch (Exception e) {
					logger.error("Failed to format.", e);
				}
			}
			patientInfoDao.savePatientInfo(patientInfoModel);
		} else {
			if (ignoreUpdate == false) {
				patientInfoModel.setPatientNo(dto.getINP_NO());
				patientInfoModel.setName(dto.getNAME());
				patientInfoModel.setSex("F".equals(dto.getSEX()) ? "女" : "男");
				patientInfoModel.setAge(StringUtil.isBlank(dto.getAGE()) ? 0 : Integer.valueOf(dto.getAGE()));
				patientInfoModel.setDeptId(deptId);
				patientInfoModel.setAreaId(areaId);
				patientInfoModel.setBedId(bedId);
				patientInfoModel.setStatus(1);
				if (StringUtil.isNotBlank(dto.getOUT_HOS_DATE())) {
					patientInfoModel.setStatus(9);
				}
				patientInfoDao.updatePatientInfo(patientInfoModel);
			}
		}
	}

	public long getAllPatientCount() {
		return patientInfoDao.getAllPatientCount();
	}

	public int updatePatientInfo(String patientNo, int status, String outHosDate) {
		 return patientInfoDao.updatePortionPatientInfo(patientNo, status,
				DateUtil.parseDate(outHosDate, "yyyy/MM/dd HH:mm:ss"));
	}
}
