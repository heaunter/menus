package com.vota.reservation.modules.cms.entity.bean;

import java.io.Serializable;

import com.vota.reservation.common.util.MoneyUtils;
import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 配送报表Bean Created by mengzhg on 2017/3/23.
 */
public class DeliveryReportBean extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6990785792696387222L;

	/** The card no. */
	private String cardNo;

	/** The username. */
	private String username;

	/** The dept name. */
	private String areaName;

	/** The bed name. */
	private String bedName;

	private Double price;

	/** The meal detail. */
	private String mealDetail;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * Gets card no.
	 * 
	 * @return the card no
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * Sets card no.
	 * 
	 * @param cardNo
	 *            the card no
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	 * Gets bed name.
	 * 
	 * @return the bed name
	 */
	public String getBedName() {
		return bedName;
	}

	/**
	 * Sets bed name.
	 * 
	 * @param bedName
	 *            the bed name
	 */
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}

	/**
	 * Gets the meal detail.
	 * 
	 * @return the meal detail
	 */
	public String getMealDetail() {
		return mealDetail;
	}

	/**
	 * Sets the meal detail.
	 * 
	 * @param mealDetail
	 *            the new meal detail
	 */
	public void setMealDetail(String mealDetail) {
		this.mealDetail = mealDetail;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void from(DeliveryReportInnerBean bean) {
		this.setBedName(bean.getBedName());
		this.setCardNo(bean.getCardNo());
		this.setAreaName(bean.getAreaName());
		this.setUsername(bean.getUsername());
		this.setMealDetail(bean.getProductName().concat("*")
				.concat(String.valueOf(bean.getCount())));
		this.setPrice(MoneyUtils.format(bean.getCount() * bean.getPrice()));

	}
}
