package com.vota.reservation.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vota.reservation.common.util.ThreadLocalUtils;

public class LogHttpInfoFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(LogHttpInfoFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("======初始化打印日志Filter.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 保存请求进来的时间到线程变量
		ThreadLocalUtils.putTime(System.currentTimeMillis());

		// 获取并打印Request Body内容
		StringBuffer requestBody = new StringBuffer();
		String line = null;
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			while ((line = reader.readLine()) != null)
				requestBody.append(line);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		// 获取并打印Request parmaeters内容
		StringBuffer requestParams = new StringBuffer();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = String.valueOf(parameterNames.nextElement());
			String parameterValue = request.getParameter(parameterName);
			requestParams.append(parameterName).append("=").append(parameterValue).append(";");
		}

		// 获取并打印Request中的header参数
		StringBuffer requestHeaders = new StringBuffer();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Enumeration<String> headers = httpRequest.getHeaderNames();
		while (headers.hasMoreElements()) {
			String headerName = String.valueOf(headers.nextElement());
			String headerValue = httpRequest.getHeader(headerName);
			requestHeaders.append(headerName).append("=").append(headerValue).append(";");
		}

		// 打印请求路径信息
		String URI = httpRequest.getRequestURI();
		String method = httpRequest.getMethod();
		logger.info("======请求信息，请求方法:" + method + ",请求地址:" + URI + ", 请求消息体:" + requestBody
				+ ", 请求参数:" + requestParams + ", 请求信息头:" + requestHeaders);

		ServletResponse newResponse = response;
		if (request instanceof HttpServletRequest) {
			newResponse = new LogHttpInfoResponseWrapper((HttpServletResponse) response);
		}
		chain.doFilter(new LogHttpInfoRequestWrapper(request), newResponse);

		// 获取并打印Response Data
		if (newResponse instanceof LogHttpInfoResponseWrapper) {
			byte[] data = ((LogHttpInfoResponseWrapper) newResponse).getBytes();
			if (data != null) {
				if (newResponse.getContentType().contains("application/json")) {
					logger.info("======响应信息,请求地址:" + URI + ", 响应数据:"
							+ new String(data, response.getCharacterEncoding()) + ", 响应信息头:"
							+ ((LogHttpInfoResponseWrapper) newResponse).getResponseHeaders()
							+ ", 消耗时长 :"
							+ (System.currentTimeMillis() - ThreadLocalUtils.getTime()) + " ms.");
				}
				response.getOutputStream().write(data);
			}
		}
	}

	@Override
	public void destroy() {
		logger.info("======销毁打印日志filter");
	}

}
