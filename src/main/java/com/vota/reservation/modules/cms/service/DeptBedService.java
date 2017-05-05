package com.vota.reservation.modules.cms.service;

import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.modules.cms.dao.SickDeptInfoDao;
import com.vota.reservation.modules.cms.entity.bean.DeptInfoBean;
import com.vota.reservation.modules.cms.entity.dto.PatientInfoDto;
import com.vota.reservation.modules.cms.entity.model.SickAreaInfoModel;
import com.vota.reservation.modules.cms.entity.model.SickBedInfoModel;
import com.vota.reservation.modules.cms.entity.model.SickDeptInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 病区病床Service Created by mengzhg on 2017/3/26.
 */
@Service
public class DeptBedService {

	@Autowired
	private SickDeptInfoDao sickDeptInfoDao;

	/**
	 * 获取所有的病区信息
	 * 
	 * @return
	 */
	public List<DeptInfoBean> getAllDepts() throws SystemException {
		return sickDeptInfoDao.getAllDepts();
	}

	/**
	 * 保存病人的病区（科室）及病床信息.
	 * 
	 * @param dto
	 *            the dto
	 */
	public Map<String, Long> saveDeptBedInformation(PatientInfoDto dto, boolean ignoreUpdate)
			throws SystemException {
		Map<String, Long> result = new HashMap<String, Long>();

		SickDeptInfoModel deptInfoModel = sickDeptInfoDao.getDeptInfo(dto.getDEPT_CODE());
		if (deptInfoModel == null) {
			deptInfoModel = new SickDeptInfoModel();
			deptInfoModel.setDeptNo(dto.getDEPT_CODE());
			deptInfoModel.setDeptName(dto.getDEPT_NAME());
			deptInfoModel.setCreateTime(DateUtil.now());
			deptInfoModel.setUpdateTime(DateUtil.now());
			sickDeptInfoDao.saveDeptInformation(deptInfoModel);
		} else {
			if (ignoreUpdate == false) {
				deptInfoModel.setDeptName(dto.getDEPT_NAME());
				deptInfoModel.setUpdateTime(DateUtil.now());
				sickDeptInfoDao.updateDeptInformation(deptInfoModel);
			}
		}
		result.put("deptId", deptInfoModel.getId());

		SickAreaInfoModel areaInfoModel = sickDeptInfoDao.getAreaInformation(deptInfoModel.getId(),
				dto.getWARD_CODE());
		if (areaInfoModel == null) {
			areaInfoModel = new SickAreaInfoModel();
			areaInfoModel.setAreaNo(dto.getWARD_CODE());
			areaInfoModel.setAreaName(dto.getWARD_NAME());
			areaInfoModel.setDeptId(deptInfoModel.getId());
			areaInfoModel.setCreateTime(DateUtil.now());
			areaInfoModel.setUpdateTime(DateUtil.now());
			sickDeptInfoDao.saveAreaInformation(areaInfoModel);
		} else {
			if (ignoreUpdate == false) {
				areaInfoModel.setDeptId(deptInfoModel.getId());
				areaInfoModel.setAreaNo(dto.getWARD_CODE());
				areaInfoModel.setAreaName(dto.getWARD_NAME());
				areaInfoModel.setUpdateTime(DateUtil.now());
				sickDeptInfoDao.updateAreaInformation(areaInfoModel);
			}
		}
		result.put("areaId", areaInfoModel.getId());

		SickBedInfoModel bedInfoModel = sickDeptInfoDao.getBedInformation(deptInfoModel.getId(),
				dto.getBED_NO());
		if (bedInfoModel == null) {
			bedInfoModel = new SickBedInfoModel();
			bedInfoModel.setDeptId(deptInfoModel.getId());
			bedInfoModel.setAreaId(areaInfoModel.getId());
			bedInfoModel.setBedName(dto.getBED_NO());
			bedInfoModel.setCreateTime(DateUtil.now());
			bedInfoModel.setUpdateTime(DateUtil.now());
			sickDeptInfoDao.saveBedInformation(bedInfoModel);
		} else {
			if (ignoreUpdate == false) {
				bedInfoModel.setDeptId(deptInfoModel.getId());
				bedInfoModel.setAreaId(areaInfoModel.getId());
				bedInfoModel.setBedName(dto.getBED_NO());
				bedInfoModel.setUpdateTime(DateUtil.now());
				sickDeptInfoDao.updateBedInformation(bedInfoModel);
			}
		}
		result.put("bedId", bedInfoModel.getId());
		return result;
	}

	public List<SickDeptInfoModel> getDetps() {
		return this.sickDeptInfoDao.getDepts();
	}

	public List<SickDeptInfoModel> getDeptsById(List<String> list) {
		return this.sickDeptInfoDao.getDeptsByIds(list);
	}

	public List<SickAreaInfoModel> getAreas() {
		return this.sickDeptInfoDao.getAllAreaInfos();
	}

	public List<SickAreaInfoModel> getDistinctAreas() {
		return this.sickDeptInfoDao.getDistinctAreas();
	}

	public List<SickAreaInfoModel> getAreaByNos(List<String> list) {
		return this.sickDeptInfoDao.getAreasByNos(list);
	}
}
