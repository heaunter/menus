package com.vota.reservation.modules.cms.entity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.vota.reservation.modules.cms.entity.BaseEntity;
import com.vota.reservation.modules.cms.entity.model.SysUserModel;

/**
 * 用户实体Bean Created by mengzhg on 2017/3/20.
 */
public class UserBean extends BaseEntity {

    private static final long serialVersionUID = -9070167224787891203L;

    /** 登录账号. */
    @JSONField(ordinal = 1)
    private String loginId;
    /** 姓名. */
    @JSONField(ordinal = 2)
    private String name;
    /** 头像. */
    @JSONField(ordinal = 3)
    private String avatar;

    /**
     * Gets the login id.
     *
     * @return the login id
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Sets the login id.
     *
     * @param loginId the new login id
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the avatar.
     *
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar.
     *
     * @param avatar the new avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void from(SysUserModel model) {
        this.setAvatar(model.getAvatar());
        this.setLoginId(model.getLoginId());
        this.setName(model.getName());
    }

}
