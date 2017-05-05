package com.vota.reservation.modules.cms.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 基础Entity
 * Created by mengzhg on 2017/3/20.
 */
public abstract class BaseEntity implements Serializable{
    
    private static final long serialVersionUID = -6612489998391274114L;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
