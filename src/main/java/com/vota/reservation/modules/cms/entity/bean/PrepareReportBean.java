package com.vota.reservation.modules.cms.entity.bean;

import java.io.Serializable;
import java.util.Date;

import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 备料Report Bean Created by mengzhg on 2017/3/23.
 */
public class PrepareReportBean extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 598379587037231316L;

	private String name;
	private String amount;
	/** 时段：早餐；中餐；晚餐 */
	private String period;
	private Date datetime;
	private Double price;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}
