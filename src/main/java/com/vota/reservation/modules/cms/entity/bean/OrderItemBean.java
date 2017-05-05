package com.vota.reservation.modules.cms.entity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 订单Item Bean Created by mengzhg on 2017/3/30.
 */
public class OrderItemBean extends BaseEntity {
    private static final long serialVersionUID = 3224362035657517487L;

    @JSONField(ordinal = 1)
    private Long id;
    @JSONField(ordinal = 2)
    private Long orderId;
    @JSONField(ordinal = 3)
    private Long productId;
    @JSONField(ordinal = 4)
    private String productName;
    @JSONField(ordinal = 5)
    private Double price;
    @JSONField(ordinal = 6)
    private Integer count;
    @JSONField(ordinal = 7)
    private String mark;
    @JSONField(ordinal = 8, format = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
