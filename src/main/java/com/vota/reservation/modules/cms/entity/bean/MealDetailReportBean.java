package com.vota.reservation.modules.cms.entity.bean;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 餐品信息 Created by mengzhg on 2017/3/23.
 */
public class MealDetailReportBean extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1507436978767153410L;
    /**
     * 时段：早餐；中餐；晚餐
     */
    private String period;
    /**
     * 餐品名称
     */
    private String mealName;
    /**
     * 餐品数量
     */
    private String amount;
    /**
     * 时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    /**
     * Gets period.
     *
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets period.
     *
     * @param period the period
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Gets meal name.
     *
     * @return the meal name
     */
    public String getMealName() {
        return mealName;
    }

    /**
     * Sets meal name.
     *
     * @param mealName the meal name
     */
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets datetime.
     *
     * @return the datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Sets datetime.
     *
     * @param datetime the datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
