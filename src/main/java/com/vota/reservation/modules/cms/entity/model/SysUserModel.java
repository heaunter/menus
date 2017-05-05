package com.vota.reservation.modules.cms.entity.model;

import java.util.Date;

import java.io.Serializable;

/**
 * 
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
public class SysUserModel implements Serializable {

	private static final long serialVersionUID = 5688681441756187259L;

	/** 主键 */
	private Long id;

	/** 登录账号 */
	private String loginId;

	/** 登录密码 */
	private String password;

	/** 邮箱 */
	private String email;

	/** 名称 */
	private String name;

	/** 头像 */
	private String avatar;

	/** 最后登录时间 */
	private Date lastLoginTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}