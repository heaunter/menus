package com.vota.reservation.modules.cms.entity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
public class DictItemModel implements Serializable {

	private static final long serialVersionUID = 4523700829708102793L;

	/** 主键 */
	private Long id;

	/** 名称 */
	private String name;

	/** 值 */
	private String value;

	/** 字典ID */
	private Long dictId;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
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