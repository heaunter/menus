package com.vota.reservation.modules.cms.entity.bean;

import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 缓存结果检查结果Bean.
 *
 * @author mengzhg@126.com
 */
public class CacheCheckBean extends BaseEntity {

    private static final long serialVersionUID = -1815438403115627643L;

    /** 病人信息. */
    private Boolean patientInfo = Boolean.TRUE;

    /** 餐品种类. */
    private Boolean mealCategory = Boolean.TRUE;

    /** 餐品信息. */
    private Boolean mealInfo = Boolean.TRUE;

    /** 订单信息. */
    private Boolean orderInfo = Boolean.TRUE;

    /** 病区病床信息. */
    private Boolean deptBedInfo = Boolean.TRUE;

    /**
     * Gets the patient info.
     *
     * @return the patient info
     */
    public Boolean getPatientInfo() {
        return patientInfo;
    }

    /**
     * Sets the patient info.
     *
     * @param patientInfo the new patient info
     */
    public void setPatientInfo(Boolean patientInfo) {
        this.patientInfo = patientInfo;
    }

    /**
     * Gets the meal category.
     *
     * @return the meal category
     */
    public Boolean getMealCategory() {
        return mealCategory;
    }

    /**
     * Sets the meal category.
     *
     * @param mealCategory the new meal category
     */
    public void setMealCategory(Boolean mealCategory) {
        this.mealCategory = mealCategory;
    }

    /**
     * Gets the meal info.
     *
     * @return the meal info
     */
    public Boolean getMealInfo() {
        return mealInfo;
    }

    /**
     * Sets the meal info.
     *
     * @param mealInfo the new meal info
     */
    public void setMealInfo(Boolean mealInfo) {
        this.mealInfo = mealInfo;
    }

    /**
     * Gets the order info.
     *
     * @return the order info
     */
    public Boolean getOrderInfo() {
        return orderInfo;
    }

    /**
     * Sets the order info.
     *
     * @param orderInfo the new order info
     */
    public void setOrderInfo(Boolean orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * Gets the dept bed info.
     *
     * @return the dept bed info
     */
    public Boolean getDeptBedInfo() {
        return deptBedInfo;
    }

    /**
     * Sets the dept bed info.
     *
     * @param deptBedInfo the new dept bed info
     */
    public void setDeptBedInfo(Boolean deptBedInfo) {
        this.deptBedInfo = deptBedInfo;
    }

}
