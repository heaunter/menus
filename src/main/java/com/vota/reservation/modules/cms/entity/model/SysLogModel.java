package com.vota.reservation.modules.cms.entity.model;

import java.util.Date;

import java.io.Serializable;

/**
 * 
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
@SuppressWarnings("serial")
public class SysLogModel implements Serializable {

	/** 主键 */
	private Long id;
	
	/** 操作路径 */
	private String requestUri;
	
	/** 操作对象 */
	private String requestObject;
	
	/** 操作类型 */
	private Integer type;
	
	/** 操作人地址 */
	private String remoteAddr;
	
	/** 请求方法 */
	private String method;
	
	/** 参数 */
	private String params;
	
	/** 操作时间 */
	private Date createTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
	public String getRequestObject() {
		return requestObject;
	}

	public void setRequestObject(String requestObject) {
		this.requestObject = requestObject;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}