package com.vota.reservation.modules.cms.dao;

import com.vota.reservation.modules.cms.entity.bean.DeptInfoBean;
import com.vota.reservation.modules.cms.entity.model.SickAreaInfoModel;
import com.vota.reservation.modules.cms.entity.model.SickBedInfoModel;
import com.vota.reservation.modules.cms.entity.model.SickDeptInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Sick dept info dao.
 * 
 * @author mengzhg @126.com
 * @version 1.0
 * @since 2017 /03/23 21:35
 */
public interface SickDeptInfoDao {

	/**
	 * 查询所有的部门信息
	 * 
	 * @return all depts
	 */
	List<DeptInfoBean> getAllDepts();

	/**
	 * 获取科室信息.
	 * 
	 * @param deptCode
	 *            the dept code
	 * @return the dept info
	 */
	SickDeptInfoModel getDeptInfo(@Param("deptNo") String deptCode);

	/**
	 * Save dept information.
	 * 
	 * @param model
	 *            the model
	 */
	void saveDeptInformation(SickDeptInfoModel model);

	/**
	 * Update dept information.
	 * 
	 * @param model
	 *            the model
	 */
	void updateDeptInformation(SickDeptInfoModel model);

	/**
	 * Gets bed information.
	 * 
	 * @param id
	 *            the id
	 * @param bed_no
	 *            the bed no
	 * @return the bed information
	 */
	SickBedInfoModel getBedInformation(@Param("id") Long id, @Param("bedNo") String bed_no);

	/**
	 * Save bed information.
	 * 
	 * @param bedInfoModel
	 *            the bed info model
	 */
	void saveBedInformation(SickBedInfoModel bedInfoModel);

	/**
	 * Update bed information.
	 * 
	 * @param bedInfoModel
	 *            the bed info model
	 */
	void updateBedInformation(SickBedInfoModel bedInfoModel);

	/**
	 * Gets the depts.
	 * 
	 * @return the depts
	 */
	List<SickDeptInfoModel> getDepts();

	/**
	 * Gets depts by ids.
	 * 
	 * @param list
	 *            the list
	 * @return the depts by ids
	 */
	List<SickDeptInfoModel> getDeptsByIds(@Param("list") List<String> list);

	/**
	 * Gets the area information.
	 * 
	 * @param id
	 *            the id
	 * @param ward_CODE
	 *            the ward_ code
	 * @return the area information
	 */
	SickAreaInfoModel getAreaInformation(@Param("id") Long id, @Param("areaNo") String ward_CODE);

	/**
	 * Save area information.
	 * 
	 * @param areaInfoModel
	 *            the area info model
	 */
	void saveAreaInformation(SickAreaInfoModel areaInfoModel);

	/**
	 * Update area information.
	 * 
	 * @param areaInfoModel
	 *            the area info model
	 */
	void updateAreaInformation(SickAreaInfoModel areaInfoModel);

	/**
	 * Gets all area infos.
	 * 
	 * @return the all area infos
	 */
	List<SickAreaInfoModel> getAllAreaInfos();

	/**
	 * Gets the areas by ids.
	 * 
	 * @param list
	 *            the list
	 * @return the areas by ids
	 */
	List<SickAreaInfoModel> getAreasByNos(List<String> list);

	/**
	 * Gets the distinct areas.
	 * 
	 * @return the distinct areas
	 */
	List<SickAreaInfoModel> getDistinctAreas();
}