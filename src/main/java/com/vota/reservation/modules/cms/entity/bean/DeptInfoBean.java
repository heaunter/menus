package com.vota.reservation.modules.cms.entity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 病区信息Bean Created by mengzhg on 2017/3/29.
 */
public class DeptInfoBean extends BaseEntity {

    private static final long serialVersionUID = -8691739092797647682L;

    @JSONField(ordinal = 1)
    private Long deptId;
    @JSONField(ordinal = 2)
    private String deptNo;
    @JSONField(ordinal = 3)
    private String deptName;
    @JSONField(ordinal = 4)
    private List<BedInfoBean> bedInfos;
    @JSONField(ordinal = 5, format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(final String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(final String deptName) {
        this.deptName = deptName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<BedInfoBean> getBedInfos() {
        return bedInfos;
    }

    public void setBedInfos(List<BedInfoBean> bedInfos) {
        this.bedInfos = bedInfos;
    }
}
