package com.vota.reservation.modules.cms.controller;

import com.vota.reservation.common.cache.MemoryCacheManager;
import com.vota.reservation.common.constant.CommonConstant;
import com.vota.reservation.common.util.StringUtil;
import com.vota.reservation.modules.cms.entity.bean.UserBean;
import com.vota.reservation.modules.cms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 安全相关Controller.
 */
@Controller
@RequestMapping("/user")
public class SecurityController {

	private Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView sysLoginGet(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param session
	 *            the session
	 * @return model and view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView sysLoginPost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView modelView = new ModelAndView();
		if (logger.isDebugEnabled()) {
			logger.debug("Begin to login...");
		}
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		UserBean userBean = userService.sysLogin(loginId, password);

		if (userBean == null) { // 用户账号或者密码错误
			modelView.setViewName("login");
			return modelView;
		}
		session.setAttribute(CommonConstant.KEY_SESSION_USER, userBean);

		modelView.setViewName("redirect:/user/index");
		modelView.addObject(CommonConstant.KEY_SESSION_USER, userBean);
		return modelView;
	}

	/**
	 * 用户退出登录
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return model and view
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView sysLogout(HttpServletRequest request, HttpSession session) {
		if (logger.isDebugEnabled()) {
			logger.debug("Begin to logout...");
		}
		session.removeAttribute(CommonConstant.KEY_SESSION_USER);
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:/user/login");
		return modelView;
	}

	/**
	 * 用户登录后主页
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return model and view
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();

		String flagMac = MemoryCacheManager.get(SUBMIT_ORDER_FLAG);
		if (StringUtil.isNotBlank(flagMac)) {
			modelView.addObject("message", MemoryCacheManager.get(SUBMIT_ORDER_FLAG_USER));
		}
		modelView.setViewName("index");
		return modelView;
	}

	@RequestMapping(value = "/unlock", method = RequestMethod.GET)
	public ModelAndView unlock() {
		ModelAndView modelView = new ModelAndView();
		MemoryCacheManager.put(SUBMIT_ORDER_FLAG, "");
		MemoryCacheManager.put(SUBMIT_ORDER_FLAG_USER, "");

		modelView.setViewName("redirect:/user/index");
		return modelView;
	}

	private String SUBMIT_ORDER_FLAG = "submit-order-flag";
	private String SUBMIT_ORDER_FLAG_USER = "submit-order-flag-user";

}
