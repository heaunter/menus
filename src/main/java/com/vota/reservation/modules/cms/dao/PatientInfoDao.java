package com.vota.reservation.modules.cms.dao;

import com.vota.reservation.modules.cms.entity.bean.PatientBean;
import com.vota.reservation.modules.cms.entity.model.PatientInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The interface Patient info dao.
 * 
 * @author mengzhg @126.com
 * @version 1.0
 * @since 2017 /03/23 21:35
 */
public interface PatientInfoDao {

	/**
	 * 获取所有病人信息.
	 * 
	 * @param cursor
	 *            the cursor
	 * @param size
	 *            the size
	 * @return all patients
	 */
	List<PatientBean> getAllPatients(@Param("cursor") int cursor, @Param("size") int size);

	/**
	 * 根据病人住院号查询病人信息.
	 * 
	 * @param patientNo
	 *            the patient no
	 * @return patient information
	 */
	PatientInfoModel getPatientInformation(@Param("patientNo") String patientNo);

	/**
	 * 根据ID获取病人信息.
	 * 
	 * @param id
	 *            the id
	 * @return patient info by id
	 */
	PatientInfoModel getPatientInfoById(@Param("id") String id);

	/**
	 * 根据patientNo获取病人基本信息.
	 * 
	 * @param id
	 *            the id
	 * @return the patient info by no
	 */
	PatientInfoModel getPatientInfoByNo(@Param("patientNo") String id);

	/**
	 * 保存病人基本信息.
	 * 
	 * @param model
	 *            the model
	 */
	void savePatientInfo(PatientInfoModel model);

	/**
	 * 更新病人基本信息.
	 * 
	 * @param model
	 *            the model
	 * @return the int
	 */
	int updatePatientInfo(PatientInfoModel model);

	/**
	 * Gets the all patient count.
	 * 
	 * @return the all patient count
	 */
	long getAllPatientCount();

	/**
	 * Gets the patient info by depts.
	 * 
	 * @param list
	 *            the list
	 * @return the patient info by depts
	 */
	List<PatientInfoModel> getPatientInfoByDepts(@Param("list") List<String> list);

	/**
	 * Gets the patient info by area.
	 * 
	 * @param list
	 *            the list
	 * @return the patient info by area
	 */
	List<PatientInfoModel> getPatientInfoByAreas(@Param("list") List<String> list);

	int updatePortionPatientInfo(@Param("patientNo") String patientNo, @Param("status") Integer status,
			@Param("outHosTime") Date outHosTime);

}