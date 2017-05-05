package com.vota.reservation.modules.cms.entity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;

import java.util.Date;

/**
 * 病床信息Bean Created by mengzhg on 2017/3/29.
 */
public class BedInfoBean extends BaseEntity {
    private static final long serialVersionUID = 6181058696588179000L;

    @JSONField(ordinal = 1)
    private Long bedId;
    @JSONField(ordinal = 2)
    private String bedName;
    @JSONField(ordinal = 3, format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
