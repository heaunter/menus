package com.vota.reservation.modules.cms.entity.bean;

import com.vota.reservation.modules.cms.entity.BaseEntity;

import java.io.Serializable;

/**
 * 配送报表Bean Created by mengzhg on 2017/3/23.
 */
public class DeliveryReportInnerBean extends BaseEntity implements Serializable {

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

    /** The meal detail. */
    private Long productId;
    private Integer count;
    private String productName;
    private Double price;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
