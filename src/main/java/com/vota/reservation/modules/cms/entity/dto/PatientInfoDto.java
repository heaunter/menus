package com.vota.reservation.modules.cms.entity.dto;

import com.vota.reservation.modules.cms.entity.BaseEntity;

/**
 * 病人信息
 * 
 * @author mengzhg@126.com
 * 
 */
public class PatientInfoDto extends BaseEntity {

	private static final long serialVersionUID = 6234347215193221582L;

	private String BALANCE;
	private String BIRTHDAY;

	private String INP_NO;

	private String NAME;

	private String AGE;

	private String ADDRESS;

	private String DEPT_CODE;

	private String DEPT_NAME;

	private String WARD_CODE;
	private String WARD_NAME;

	private String BED_NO;

	private String ID_NO;

	private String OUT_HOS_DATE;
	private String MOBILE_PHONE;

	private String SETTLED_INDICATOR;

	private String SEX;

	private String DIAGNOSIS;

	private String IN_HOS_DATE;

	public void setBALANCE(String BALANCE) {
		this.BALANCE = BALANCE;
	}

	public String getBALANCE() {
		return this.BALANCE;
	}

	public void setBIRTHDAY(String BIRTHDAY) {
		this.BIRTHDAY = BIRTHDAY;
	}

	public String getBIRTHDAY() {
		return this.BIRTHDAY;
	}

	public void setINP_NO(String INP_NO) {
		this.INP_NO = INP_NO;
	}

	public String getINP_NO() {
		return this.INP_NO;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getNAME() {
		return this.NAME;
	}

	public void setAGE(String AGE) {
		this.AGE = AGE;
	}

	public String getAGE() {
		return this.AGE;
	}

	public void setADDRESS(String ADDRESS) {
		this.ADDRESS = ADDRESS;
	}

	public String getADDRESS() {
		return this.ADDRESS;
	}

	public void setDEPT_CODE(String DEPT_CODE) {
		this.DEPT_CODE = DEPT_CODE;
	}

	public String getDEPT_CODE() {
		return this.DEPT_CODE;
	}

	public void setDEPT_NAME(String DEPT_NAME) {
		this.DEPT_NAME = DEPT_NAME;
	}

	public String getDEPT_NAME() {
		return this.DEPT_NAME;
	}

	public void setOUT_HOS_DATE(String OUT_HOS_DATE) {
		this.OUT_HOS_DATE = OUT_HOS_DATE;
	}

	public String getOUT_HOS_DATE() {
		return this.OUT_HOS_DATE;
	}

	public void setWARD_NAME(String WARD_NAME) {
		this.WARD_NAME = WARD_NAME;
	}

	public String getWARD_NAME() {
		return this.WARD_NAME;
	}

	public void setBED_NO(String BED_NO) {
		this.BED_NO = BED_NO;
	}

	public String getBED_NO() {
		return this.BED_NO;
	}

	public void setID_NO(String ID_NO) {
		this.ID_NO = ID_NO;
	}

	public String getID_NO() {
		return this.ID_NO;
	}

	public void setMOBILE_PHONE(String MOBILE_PHONE) {
		this.MOBILE_PHONE = MOBILE_PHONE;
	}

	public String getMOBILE_PHONE() {
		return this.MOBILE_PHONE;
	}

	public void setSETTLED_INDICATOR(String SETTLED_INDICATOR) {
		this.SETTLED_INDICATOR = SETTLED_INDICATOR;
	}

	public String getSETTLED_INDICATOR() {
		return this.SETTLED_INDICATOR;
	}

	public void setSEX(String SEX) {
		this.SEX = SEX;
	}

	public String getSEX() {
		return this.SEX;
	}

	public void setDIAGNOSIS(String DIAGNOSIS) {
		this.DIAGNOSIS = DIAGNOSIS;
	}

	public String getDIAGNOSIS() {
		return this.DIAGNOSIS;
	}

	public void setIN_HOS_DATE(String IN_HOS_DATE) {
		this.IN_HOS_DATE = IN_HOS_DATE;
	}

	public String getIN_HOS_DATE() {
		return this.IN_HOS_DATE;
	}

	public void setWARD_CODE(String WARD_CODE) {
		this.WARD_CODE = WARD_CODE;
	}

	public String getWARD_CODE() {
		return this.WARD_CODE;
	}
}
