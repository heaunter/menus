package com.vota.reservation.modules.cms.entity.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
public class MutiLangModel implements Serializable{
    
	private static final long serialVersionUID = -7955939092052225900L;

	/**
     * 主键
     */
    private Long id;
    
    /**
     * 国际化key
     */
    private String langKey;
    
    /**
     * 国际化value
     */
    private String langValue;
    
    /**
     * Locale
     */
    private String langLocale;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date updateTime;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLangKey() {
        return langKey;
    }
    
    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }
    
    public String getLangValue() {
        return langValue;
    }
    
    public void setLangValue(String langValue) {
        this.langValue = langValue;
    }
    
    public String getLangLocale() {
        return langLocale;
    }
    
    public void setLangLocale(String langLocale) {
        this.langLocale = langLocale;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}