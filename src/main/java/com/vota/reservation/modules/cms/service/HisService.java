package com.vota.reservation.modules.cms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.HttpUtils;
import com.vota.reservation.common.util.PropertiesUtil;
import com.vota.reservation.common.util.XmlConvertUtil;
import com.vota.reservation.modules.cms.entity.dto.HisGetPatientResponseDto;
import com.vota.reservation.modules.cms.entity.dto.PatientInfoDto;

/**
 * HIS系统服务<br>
 * 1,支付接口<br>
 * 2,获取用户信息接口
 * 
 * @author mengzhg@126.com
 */
@Service
public class HisService {

	private Logger logger = LoggerFactory.getLogger(HisService.class);

	private static final String HIS_USER_ID = "test";
	private static final String HIS_USER_PASSWORD = "123";

	private static final String HIS_ORDER_PAY = "FM_01_01_002";
	private static final String HIS_PATIENT_INFO = "PI_01_01_002";
	private static final String HIS_DISCHARGE_PATIENT_INFO = "PI_01_01_003";

	@Autowired
	private UserService userService;
	@Autowired
	private DeptBedService deptBedService;

	private int size = 5000;

	/**
	 * 支付与退款.
	 * 
	 * @param patientNo
	 *            the patient no
	 * @param money
	 *            the money
	 * @throws DocumentException
	 * @throws SystemException
	 */
	public boolean pay(String patientNo, String orderNo, double money) throws SystemException {
		String url = PropertiesUtil.getString("his.ws.api.url");
		int flag = 1;
		if (money < 0) {
			flag = -1;
		}
		String postData = buildPayRequestXml(patientNo, orderNo, Math.abs(money), flag);

		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/x-www-form-urlencoded");

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("XmlString", postData);
		logger.info("======Pay Request XmlString[orderNo=" + orderNo + "]:" + postData);
		String xml = HttpUtils.postByParams(url, headerMap, paramMap);
		xml = xml.replaceAll("&lt;", "<" + "").replaceAll("&gt;", ">" + "");
		String tmpXml = xml.substring((xml.indexOf("body") - 1), (xml.lastIndexOf("body") + 5));
		logger.info("======Pay Process result xml[orderNo=" + orderNo + "]:" + tmpXml);
		try {
			JSONObject json = XmlConvertUtil.xmltoJSON(tmpXml);
			JSONArray responses = json.getJSONArray("response");
			JSONObject response = responses.getJSONObject(0);
			String retCode = response.getString("ret_code");
			if ("0".equals(retCode)) {
				return true;
			} else {
				throw new SystemException(response.getString("ret_info"));
			}
		} catch (DocumentException e) {
			logger.error("", e);
			logger.error("Failed to parse the his result,url={},post data={}, xml={}.", url,
					postData, xml);
			throw new SystemException("支付结果解析失败");
		}
	}

	/**
	 * 查询病区信息.
	 * 
	 * @param index
	 *            the index
	 * @param size
	 *            the size
	 * @return the list
	 * @throws SystemException
	 *             the system exception
	 */
	public HisGetPatientResponseDto queryPatientInfo(int index, int size) throws SystemException {
		if (index < 0) {
			index = 0;
		}
		String url = PropertiesUtil.getString("his.ws.api.url");
		String postData = buildGetPatientRequestXml(index, size);

		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/x-www-form-urlencoded");

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("XmlString", postData);
		logger.info("Query patient info request, XmlString:" + postData);
		String xml = HttpUtils.postByParams(url, headerMap, paramMap);
		xml = xml.replaceAll("&lt;", "<" + "").replaceAll("&gt;", ">" + "");
		String tmpXml = xml.substring((xml.indexOf("body") - 1), (xml.lastIndexOf("body") + 5));
		logger.info("======Process XML:" + tmpXml);
		try {
			JSONObject json = XmlConvertUtil.xmltoJSON(tmpXml);
			JSONArray responses = json.getJSONArray("response");
			JSONObject response = responses.getJSONObject(0);
			String retCode = response.getString("ret_code");
			if ("0".equals(retCode)) {
				return JSON.parseObject(response.toJSONString(), HisGetPatientResponseDto.class);
			} else {
				throw new SystemException(response.getString("ret_info"));
			}
		} catch (DocumentException e) {
			logger.error("", e);
			logger.error("Failed to parse the his result,url={},post data={}, xml={}.", url,
					postData, xml);
			throw new SystemException("查询在院病人信息解析失败");
		}
	}

	/**
	 * 查询出院病人信息
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws SystemException
	 */
	public HisGetPatientResponseDto queryDischargePatientInfo(Date start, Date end)
			throws SystemException {

		String url = PropertiesUtil.getString("his.ws.api.url");
		String postData = buildGetDischargePatientXml(
				DateUtil.formatDate(start, DateUtil.PATTERN_YYYY_MM_DD),
				DateUtil.formatDate(end, DateUtil.PATTERN_YYYY_MM_DD));

		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/x-www-form-urlencoded");

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("XmlString", postData);
		logger.info("Query patient info request, XmlString:" + postData);
		String xml = HttpUtils.postByParams(url, headerMap, paramMap);
		xml = xml.replaceAll("&lt;", "<" + "").replaceAll("&gt;", ">" + "");
		String tmpXml = xml.substring((xml.indexOf("body") - 1), (xml.lastIndexOf("body") + 5));
		logger.info("======Process XML:" + tmpXml);
		try {
			JSONObject json = XmlConvertUtil.xmltoJSON(tmpXml);
			JSONArray responses = json.getJSONArray("response");
			JSONObject response = responses.getJSONObject(0);
			String retCode = response.getString("ret_code");
			if ("0".equals(retCode)) {
				return JSON.parseObject(response.toJSONString(), HisGetPatientResponseDto.class);
			} else {
				throw new SystemException(response.getString("ret_info"));
			}
		} catch (DocumentException e) {
			logger.error("", e);
			logger.error("Failed to parse the his result,url={},post data={}, xml={}.", url,
					postData, xml);
			throw new SystemException("查询出院病人信息解析失败");
		}
	}

	public static String buildGetDischargePatientXml(String start, String end) {
		String reqXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><body><head><userid>"
				+ HIS_USER_ID + "</userid><password>" + HIS_USER_PASSWORD + "</password><trans_no>"
				+ HIS_DISCHARGE_PATIENT_INFO
				+ "</trans_no></head><resquest><PATIENT_INFO1><ENDDATE>" + end
				+ "</ENDDATE><V_CUR></V_CUR><STARTDATE>" + start
				+ "</STARTDATE></PATIENT_INFO1></resquest></body>";
		return reqXml;
	}

	/**
	 * 生成获取病人基本信息的请求XML.
	 * 
	 * @return the string
	 */
	private static String buildGetPatientRequestXml(int index, int size) {
		String reqXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<body><head><userid>"
				+ HIS_USER_ID + "</userid><password>" + HIS_USER_PASSWORD + "</password><trans_no>"
				+ HIS_PATIENT_INFO + "</trans_no></head><resquest><PATIENT_INFO>"
				+ "<INP_NO></INP_NO>" + "<PCOUNT></PCOUNT>" + "<PINDEX>" + index + "</PINDEX>"
				+ "<PRCOUNT></PRCOUNT>" + "<PSIZE>" + size + "</PSIZE>" + "<V_CUR></V_CUR>"
				+ "</PATIENT_INFO>" + "</resquest>" + "</body>";
		return reqXml;
	}

	/**
	 * 生成支付和退款的请求XML.
	 * 
	 * @param patientNo
	 *            住院号
	 * @param orderNo
	 *            订单号
	 * @param money
	 *            金额
	 * @param flag
	 *            -1:退费 1=扣费
	 * @return the string
	 */
	private String buildPayRequestXml(String patientNo, String orderNo, double money, int flag) {
		String reqXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<body><head><userid>"
				+ HIS_USER_ID
				+ "</userid><password>"
				+ HIS_USER_PASSWORD
				+ "</password><trans_no>"
				+ HIS_ORDER_PAY
				+ "</trans_no></head><resquest><PATIENT_ADD_FEE><FEE_DATE>"
				+ DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD)
				+ "</FEE_DATE><INP_NO>"
				+ patientNo
				+ "</INP_NO><ITEM_CODE>24000093</ITEM_CODE><PRICE>"
				+ money
				+ "</PRICE><QTY>"
				+ flag
				+ "</QTY><VI_SRKS>119</VI_SRKS><VI_SRRY>9991</VI_SRRY><VI_YEBZ></VI_YEBZ><VI_YJDH></VI_YJDH><VI_YSBH>9991</VI_YSBH><VI_YZXH></VI_YZXH><VI_YZZH></VI_YZZH><VI_ZFBZ></VI_ZFBZ><VO_JE></VO_JE><VO_XFJE></VO_XFJE><VO_ZFJE></VO_ZFJE></PATIENT_ADD_FEE></resquest></body>";
		return reqXml;
	}

	public static void main(String[] args) throws DocumentException {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\"><?xml version=\"1.0\" encoding=\"utf-8\" ?><body><response><ret_code>0</ret_code><ret_info>成功</ret_info><V_CUR><RN>1</RN><INP_NO>0442044B</INP_NO><DEPT_CODE>205</DEPT_CODE><DEPT_NAME>产科</DEPT_NAME><WARD_CODE>12</WARD_CODE><WARD_NAME>产科(1)病区</WARD_NAME><BED_NO>12B14</BED_NO><NAME>张静婿</NAME><SEX>F</SEX><BIRTHDAY></BIRTHDAY><AGE>0</AGE><MOBILE_PHONE></MOBILE_PHONE><ID_NO></ID_NO><BALANCE>0</BALANCE><IN_HOS_DATE>2005-12-31 19:17:32</IN_HOS_DATE><DIAGNOSIS>657</DIAGNOSIS><SETTLED_INDICATOR>Y</SETTLED_INDICATOR><OUT_HOS_DATE>2006-1-6 8:30:54</OUT_HOS_DATE></V_CUR><V_CUR><RN>2</RN><INP_NO>0442043B</INP_NO><DEPT_CODE>205</DEPT_CODE><DEPT_NAME>产科</DEPT_NAME><WARD_CODE>12</WARD_CODE><WARD_NAME>产科(1)病区</WARD_NAME><BED_NO>12B07</BED_NO><NAME>冯燕波婴</NAME><SEX>F</SEX><BIRTHDAY></BIRTHDAY><AGE>0</AGE><MOBILE_PHONE></MOBILE_PHONE><ID_NO></ID_NO><BALANCE>0</BALANCE><IN_HOS_DATE>2005-12-31 19:17:32</IN_HOS_DATE><DIAGNOSIS>657</DIAGNOSIS><SETTLED_INDICATOR>Y</SETTLED_INDICATOR><OUT_HOS_DATE>2006-1-6 10:36:24</OUT_HOS_DATE></V_CUR><PCOUNT>51510</PCOUNT><PRCOUNT>515096</PRCOUNT></response></body></string>";
		System.out.println(xml.indexOf("body"));
		System.out.println(xml.lastIndexOf("body"));
		String str = xml.substring((xml.indexOf("body") - 1), (xml.lastIndexOf("body") + 5));
		JSONObject json = XmlConvertUtil.xmltoJSON(str);
		System.out.println(json);
	}

	/**
	 * 同步用户数据.
	 * 
	 * @throws SystemException
	 *             the system exception
	 */
	public void syncUserInfo() throws SystemException {
		if (PropertiesUtil.getBoolean("test.env.flag", true)) {
			return;
		}

		long start = System.currentTimeMillis();
		int index = 0;
		boolean ignoreUpdate = PropertiesUtil.getBoolean("his.ignore.update", false);

		boolean flag = true;
		do {
			try {
				HisGetPatientResponseDto getPatientResponseDto = this.queryPatientInfo(index, size);
				processData(getPatientResponseDto, ignoreUpdate, index);
				if ((index + 1) * size < getPatientResponseDto.getPRCOUNT()
						|| (index + 1 < getPatientResponseDto.getPCOUNT())) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (SystemException e) {
				logger.error("Failed to sync data.", e);
			} catch (Exception e) {
				logger.error("Failed to sync data.", e);
			} finally {
				index++;
			}
		} while (flag);
		logger.info("============Job used time: {} ms.", System.currentTimeMillis() - start);

	}

	/**
	 * 处理病人基本信息数据
	 * 
	 * @param hisGetPatientResponseDto
	 */
	private void processData(HisGetPatientResponseDto hisGetPatientResponseDto,
			boolean ignoreUpdate, int index) {
		if (hisGetPatientResponseDto == null) {
			return;
		}
		List<PatientInfoDto> curs = hisGetPatientResponseDto.getV_CUR();
		if (curs == null || curs.size() <= 0) {
			return;
		}
		logger.info("============Process patient info,cursor=" + ((index + 1) * size));
		for (int i = 0; i < curs.size(); i++) {
			PatientInfoDto dto = curs.get(i);
			if (dto.getINP_NO().startsWith("-")) {
				logger.warn("cancle the hospitalized:" + JSON.toJSONString(dto));
				continue;
			}

			Map<String, Long> retMap = null;
			try {
				retMap = deptBedService.saveDeptBedInformation(dto, ignoreUpdate);
			} catch (SystemException e) {
				logger.error("Failed to save dept bed information.", e);
				logger.warn("======Dto:{}", JSON.toJSONString(dto));
			}
			try {
				userService.savePatientInformation(dto, retMap.get("deptId"), retMap.get("areaId"),
						retMap.get("bedId"), ignoreUpdate);
			} catch (SystemException e) {
				logger.error("Failed to save Patient information.", e);
				logger.warn("======Dto:{}", JSON.toJSONString(dto));
			}
		}
	}
}
