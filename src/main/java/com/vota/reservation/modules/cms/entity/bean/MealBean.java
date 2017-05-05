package com.vota.reservation.modules.cms.entity.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;
import com.vota.reservation.modules.cms.entity.model.ProductInfoModel;

/**
 * 餐品Bean
 *
 * @author mengzhg@126.com
 */
public class MealBean extends BaseEntity {

    private static final long serialVersionUID = 6709520136593256633L;

    @JSONField(ordinal = 1)
    private Long id;// 餐品ID
    @JSONField(ordinal = 2)
    private Long categoryId;// 餐品种类ID
    @JSONField(ordinal = 3)
    private String name;// 餐品名称
    @JSONField(ordinal = 4)
    private String image;// 图片地址
    @JSONField(ordinal = 5)
    private Double price;// 价格
    @JSONField(ordinal = 6)
    private String description;// 描述
    @JSONField(ordinal = 7)
    private Integer isBreakfast;// 是否可以当作早餐
    @JSONField(ordinal = 8)
    private Integer isLunch;// 是否可以当作午餐
    @JSONField(ordinal = 9)
    private Integer isDinner;// 是否可以当作晚餐
    @JSONField(ordinal = 10, format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;// 最后更新时间 备注：yyyy-MM-DD HH:mm:ss

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void from(ProductInfoModel model) {
        this.setId(model.getId());
        this.setCategoryId(model.getCategory());
        this.setDescription(model.getDescription());
        this.setImage(model.getImageUrl());
        this.setIsBreakfast(model.getIsBreakfast());
        this.setIsLunch(model.getIsLunch());
        this.setIsDinner(model.getIsDinner());
        this.setName(model.getName());
        this.setPrice(model.getPrice());
        this.setUpdateTime(model.getUpdateTime());
    }

}
