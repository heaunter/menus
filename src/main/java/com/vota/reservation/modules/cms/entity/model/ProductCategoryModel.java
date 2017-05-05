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
public class ProductCategoryModel implements Serializable {

	/** 主键 */
	private Long id;
	
	/** 种类名称 */
	private String name;
	
	/** 种类父节点 */
	private Long parentId;
	
	/** 种类排序 */
	private Integer sort;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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