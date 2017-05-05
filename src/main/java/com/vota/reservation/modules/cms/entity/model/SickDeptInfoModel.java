package com.vota.reservation.modules.cms.entity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
@SuppressWarnings("serial")
public class SickDeptInfoModel implements Serializable {

	/** 主键 */
	private Long id;
	
	/** 科室编号 */
	private String deptNo;
	
	/** 科室名称 */
	private String deptName;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	
}