package com.vota.reservation.common.filter;

import com.vota.reservation.common.constant.CommonConstant;
import com.vota.reservation.modules.cms.entity.bean.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 鉴权Filter.
 */
public class AuthFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("init Auth Filter.");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		if (!(servletRequest instanceof HttpServletRequest)
				|| !(servletResponse instanceof HttpServletResponse)) {
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

		HttpSession session = httpRequest.getSession(true);
		Object object = session.getAttribute(CommonConstant.KEY_SESSION_USER);
		UserBean user = (object == null) ? null : (UserBean) object;
		String requestURI = httpRequest.getRequestURI();
		if (user == null
				&& !(requestURI.endsWith("login") || requestURI.contains("resources")
						|| requestURI.contains("api") || requestURI.endsWith("signup"))) {
			boolean isAjaxRequest = isAjaxRequest(httpRequest);
			if (isAjaxRequest) {
				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
			}
			logger.warn("Your session has timeout, address:{}.", httpRequest.getRemoteAddr());
			httpResponse.sendRedirect("../user/login");
			return;
		}
		chain.doFilter(servletRequest, servletResponse);
		return;
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String str = request.getHeader("x-requested-with");
		if (str != null && "XMLHttpRequest".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	public void destroy() {
		logger.info("destory Auth Filter.");
	}

}
