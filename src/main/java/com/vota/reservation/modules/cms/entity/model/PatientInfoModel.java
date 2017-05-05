package com.vota.reservation.modules.cms.entity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
public class PatientInfoModel implements Serializable {

	private static final long serialVersionUID = -3535796363444951317L;

	/** 主键 */
	private Long id;

	/** 病人住院号 */
	private String patientNo;

	/** 科室ID */
	private Long deptId;
	/** 病区ID */
	private Long areaId;
	/** 病床ID */
	private Long bedId;

	/** 姓名 */
	private String name;

	/** 性别 */
	private String sex;

	/** 年龄 */
	private Integer age;

	/** 状态 */
	private Integer status;

	/** 住院时间 */
	private Date createTime;

	/** 修改时间 */
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

}