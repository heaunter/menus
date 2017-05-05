package com.vota.reservation.modules.cms.entity.dto;

import java.util.List;

import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 获取病人信息HIS 接口响应
 * 
 * @author mengzhg@126.com
 * 
 */
public class HisGetPatientResponseDto extends BaseEntity {
	private static final long serialVersionUID = 123071835783084510L;

	private String ret_code;
	private String ret_info;
	private int PCOUNT;
	private int PRCOUNT;

	private List<PatientInfoDto> V_CUR;

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

	public String getRet_code() {
		return this.ret_code;
	}

	public void setV_CUR(List<PatientInfoDto> V_CUR) {
		this.V_CUR = V_CUR;
	}

	public List<PatientInfoDto> getV_CUR() {
		return this.V_CUR;
	}

	public void setRet_info(String ret_info) {
		this.ret_info = ret_info;
	}

	public String getRet_info() {
		return this.ret_info;
	}

	public int getPCOUNT() {
		return PCOUNT;
	}

	public void setPCOUNT(int pCOUNT) {
		PCOUNT = pCOUNT;
	}

	public int getPRCOUNT() {
		return PRCOUNT;
	}

	public void setPRCOUNT(int pRCOUNT) {
		PRCOUNT = pRCOUNT;
	}

}
