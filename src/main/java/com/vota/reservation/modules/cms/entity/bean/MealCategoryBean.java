package com.vota.reservation.modules.cms.entity.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;
import com.vota.reservation.modules.cms.entity.model.ProductCategoryModel;

/**
 * 餐品种类Bean
 *
 * @author mengzhg@126.com
 */
public class MealCategoryBean extends BaseEntity {

    private static final long serialVersionUID = 3845693156288179855L;

    @JSONField(ordinal = 1)
    private Long id;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private Integer sort;
    @JSONField(ordinal = 4, format = "yyyy-MM-dd HH:mm:ss")
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void from(ProductCategoryModel model) {
        this.setId(model.getId());
        this.setName(model.getName());
        this.setSort(model.getSort());
        this.setUpdateTime(model.getUpdateTime());
    }

}
