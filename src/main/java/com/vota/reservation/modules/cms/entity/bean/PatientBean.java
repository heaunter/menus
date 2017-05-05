package com.vota.reservation.modules.cms.entity.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 病人信息 Created by mengzhg on 2017/3/26.
 */
public class PatientBean extends BaseEntity {
	private static final long serialVersionUID = 7673449905120156143L;

	@JSONField(ordinal = 1)
	private String patientNo;
	@JSONField(ordinal = 2)
	private Long deptId;
	@JSONField(ordinal = 3)
	private String deptName;
	@JSONField(ordinal = 4)
	private Long bedId;
	@JSONField(ordinal = 5)
	private String bedName;
	@JSONField(ordinal = 6)
	private String username;
	@JSONField(ordinal = 7)
	private String sex;
	@JSONField(ordinal = 8)
	private Integer age;
	@JSONField(ordinal = 9, format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * Gets patient no.
	 * 
	 * @return the patient no
	 */
	public String getPatientNo() {
		return patientNo;
	}

	/**
	 * Sets patient no.
	 * 
	 * @param patientNo
	 *            the patient no
	 */
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

	/**
	 * Gets dept name.
	 * 
	 * @return the dept name
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * Sets dept name.
	 * 
	 * @param deptName
	 *            the dept name
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * Gets username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username.
	 * 
	 * @param username
	 *            the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets sex.
	 * 
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets sex.
	 * 
	 * @param sex
	 *            the sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets age.
	 * 
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets age.
	 * 
	 * @param age
	 *            the age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getBedId() {
		return bedId;
	}

	public void setBedId(Long bedId) {
		this.bedId = bedId;
	}

	public String getBedName() {
		return bedName;
	}

	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
}
