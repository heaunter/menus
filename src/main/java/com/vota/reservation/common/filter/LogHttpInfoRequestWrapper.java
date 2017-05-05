package com.vota.reservation.common.filter;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class LogHttpInfoRequestWrapper extends HttpServletRequestWrapper {

	public LogHttpInfoRequestWrapper(ServletRequest request) {
		super((HttpServletRequest) request);
	}

	public LogHttpInfoRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<String> getHeaders(String name) {
		if (null != name && name.equalsIgnoreCase("content-type")) {
			return new Enumeration<String>() {
				private boolean hasGetted = false;

				@Override
				public String nextElement() {
					if (hasGetted) {
						throw new NoSuchElementException();
					} else {
						hasGetted = true;
						return "application/json;charset=utf-8";
					}
				}

				@Override
				public boolean hasMoreElements() {
					return !hasGetted;
				}
			};
		}
		return super.getHeaders(name);
	}

	@Override
	public String getHeader(String name) {
		if (null != name && name.equalsIgnoreCase("content-type")) {
			return "application/json;charset=utf-8";
		} else {
			return super.getHeader(name);
		}
	}

}
