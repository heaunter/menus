package com.vota.reservation.modules.cms.entity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 订单信息Bean Created by mengzhg on 2017/3/30.
 */
public class OrderBean extends BaseEntity {
	private static final long serialVersionUID = 8135910554787306728L;

	@JSONField(ordinal = 1)
	private Long orderId;
	@JSONField(ordinal = 2)
	private String orderNo;
	@JSONField(ordinal = 3)
	private Long userId;
	@JSONField(ordinal = 4)
	private String patientNo;
	@JSONField(ordinal = 5)
	private String areaName;
	@JSONField(ordinal = 6)
	private Integer itemCount;
	@JSONField(ordinal = 7)
	private Double price;
	@JSONField(ordinal = 8)
	private Integer status;
	@JSONField(ordinal = 9, format = "yyyy-MM-dd")
	private Date consumeDate;
	@JSONField(ordinal = 10)
	private String period;
	@JSONField(ordinal = 11, format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@JSONField(ordinal = 12)
	private List<OrderItemBean> items;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<OrderItemBean> getItems() {
		return items;
	}

	public void setItems(List<OrderItemBean> items) {
		this.items = items;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
}
