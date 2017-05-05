package com.vota.reservation.modules.cms.entity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
public class ProductInfoModel implements Serializable {

	private static final long serialVersionUID = -1207935291762042136L;

	/** 主键 */
	private Long id;

	/** 名称 */
	private String name;

	/** 餐品图片地址 */
	private String imageUrl;

	/** 价格 */
	private Double price;

	/** 描述 */
	private String description;

	/** 种类 */
	private Long category;

	/** The is breakfast. */
	private Integer isBreakfast;
	private Integer isLunch;
	private Integer isDinner;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
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

	public Integer getIsBreakfast() {
		return isBreakfast;
	}

	public void setIsBreakfast(Integer isBreakfast) {
		this.isBreakfast = isBreakfast;
	}

	public Integer getIsLunch() {
		return isLunch;
	}

	public void setIsLunch(Integer isLunch) {
		this.isLunch = isLunch;
	}

	public Integer getIsDinner() {
		return isDinner;
	}

	public void setIsDinner(Integer isDinner) {
		this.isDinner = isDinner;
	}

}