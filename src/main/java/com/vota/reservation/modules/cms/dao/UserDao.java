package com.vota.reservation.modules.cms.dao;

import org.apache.ibatis.annotations.Param;

import com.vota.reservation.modules.cms.entity.model.SysUserModel;

/**
 * 用户登录.
 */
public interface UserDao {

	/**
	 * 管理员登录.
	 * 
	 * @param loginId
	 *            the login id
	 * @param password
	 *            the password
	 * @return the user model
	 */
	SysUserModel login(@Param("loginId") String loginId, @Param("password") String password);

	/**
	 * 获取用户信息.
	 * 
	 * @param loginId
	 *            the login id
	 * @return user info
	 */
	SysUserModel getUserInfo(@Param("loginId") String loginId);

	/**
	 * 修改用户信息
	 * 
	 * @param loginId
	 *            the login id
	 * @param password
	 *            the password
	 * @return the int
	 */
	int update(@Param("loginId") String loginId, @Param("password") String password);
}
