package com.vota.reservation.modules.cms.entity.dto;

import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 产品汇总报表
 * 
 */
@SuppressWarnings("serial")
public class MealReportDto extends BaseEntity {

	private String patientNo;
	private String username;
	private Integer count;
	private String areaName;
	private String bedName;

	public String getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBedName() {
		return bedName;
	}

	public void setBedName(String bedName) {
		this.bedName = bedName;
	}

}
