package com.vota.reservation.modules.apis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vota.reservation.common.business.BizCodeBuilder;
import com.vota.reservation.common.cache.MemoryCacheManager;
import com.vota.reservation.common.exception.CacheException;
import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.http.ApiResponse;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.StringUtil;
import com.vota.reservation.modules.cms.entity.bean.*;
import com.vota.reservation.modules.cms.entity.model.OrderItemModel;
import com.vota.reservation.modules.cms.entity.model.OrderModel;
import com.vota.reservation.modules.cms.entity.model.PatientInfoModel;
import com.vota.reservation.modules.cms.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * APP端接口汇总.
 * 
 * @author mengzhg 2017/03/20
 */
@Controller
@RequestMapping("/api")
public class ApiController {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);

	/** 分页大小：1000 */
	private int PAGE_SIZE = 1000;

	/** 用户Service */
	@Autowired
	private UserService userService;
	/** 餐品Service */
	@Autowired
	private MealService mealService;
	/** 病区病床Service */
	@Autowired
	private DeptBedService deptBedService;
	/** 订单service */
	@Autowired
	private OrderService orderService;
	@Autowired
	private SysConfigService configService;

	private String SUBMIT_ORDER_FLAG = "submit-order-flag";
	private String SUBMIT_ORDER_FLAG_USER = "submit-order-flag-user";

	/**
	 * 获取餐品种类.
	 * 
	 * @return 餐品种类列表 meal categories
	 */
	@RequestMapping(value = "/meal/categories", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<List<MealCategoryBean>> getMealCategories() {
		ApiResponse<List<MealCategoryBean>> result = new ApiResponse<List<MealCategoryBean>>();
		try {
			List<MealCategoryBean> mealCategories = mealService.getMealCategories();
			result.setCode(1);
			result.setData(mealCategories);
			result.setMessage("查询成功");
		} catch (SystemException e) {
			logger.error("Failed to get meal category.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		} catch (Exception e) {
			logger.error("Failed to get meal category.", e);
			result.setCode(0);
			result.setMessage("查询失败");
		}
		logger.info("======Get all meal categories, result={}", JSON.toJSONString(result));
		return result;
	}

	/**
	 * 获取餐品信息.
	 * 
	 * @param requestBody
	 *            the request json
	 * @return 餐品信息集合 meals
	 */
	@RequestMapping(value = "/meals", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<JSONObject> getMeals(@RequestBody String requestBody) {
		ApiResponse<JSONObject> result = new ApiResponse<JSONObject>();
		try {
			logger.info("======Get meals: request body={}", requestBody);

			JSONObject requestJson = JSON.parseObject(requestBody);

			int cursor = 0;
			if (requestJson.containsKey("index")) {
				cursor = requestJson.getIntValue("index");
			}
			int size = PAGE_SIZE;
			if (requestJson.containsKey("size")) {
				size = requestJson.getIntValue("size");
			}
			List<MealBean> meals = mealService.getMeals(cursor, size);
			Long allCount = mealService.getMealCount();

			JSONObject obj = new JSONObject();
			obj.put("items", meals);
			obj.put("totalCount", allCount);
			result.setData(obj);

			result.setCode(1);
			result.setMessage("操作成功");
		} catch (SystemException e) {
			logger.error("Failed to get meals.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		} catch (Exception e) {
			logger.error("Failed to get meals.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		}
		logger.info("======Get all meals, result={}", JSON.toJSONString(result));
		return result;
	}

	/**
	 * 管理员登录
	 * 
	 * @param requestBody
	 *            the request json
	 * @return api response
	 */
	@ResponseBody
	@RequestMapping(value = "/user/sys/login", method = RequestMethod.POST)
	public ApiResponse<UserBean> sysLogin(@RequestBody String requestBody) {
		ApiResponse<UserBean> result = new ApiResponse<UserBean>();
		try {
			logger.info("======Sys user Login: request body={}", requestBody);

			JSONObject requestJson = JSON.parseObject(requestBody);

			String loginId = requestJson.getString("loginId");
			String password = requestJson.getString("password");
			UserBean user = userService.sysLogin(loginId, password);
			if (user != null) {
				result.setData(user);
				result.setCode(1);
				result.setMessage("登录成功");
			} else {
				result.setCode(0);
				result.setMessage("账号或者密码错误");
			}
		} catch (Exception e) {
			logger.error("Failed to sys login.", e);
			result.setCode(0);
			result.setMessage("登录失败");
		}
		logger.info("======Sys user login, result={}", JSON.toJSONString(result));
		return result;
	}

	/**
	 * 查询所有的科室病床信息
	 * 
	 * @return api response
	 */
	@ResponseBody
	@RequestMapping(value = "/dept/infos", method = RequestMethod.GET)
	public ApiResponse<List<DeptInfoBean>> queryDeptBedInformation() {
		ApiResponse<List<DeptInfoBean>> result = new ApiResponse<List<DeptInfoBean>>();
		try {
			List<DeptInfoBean> deptBeds = deptBedService.getAllDepts();
			result.setCode(1);
			result.setData(deptBeds);
			result.setMessage("操作成功");
		} catch (SystemException e) {
			logger.error("Failed to query dept bed Information.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		} catch (Exception e) {
			logger.error("Failed to query dept bed Information.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		}
		logger.info("======Query Dept Bed Information, result={}", JSON.toJSONString(result));
		return result;
	}

	/**
	 * 查询所有的用户信息
	 * 
	 * @return api response
	 */
	@ResponseBody
	@RequestMapping(value = "/user/infos", method = RequestMethod.POST)
	public ApiResponse<JSONObject> queryAllUserInformation(@RequestBody String requestBody) {
		ApiResponse<JSONObject> result = new ApiResponse<JSONObject>();
		try {
			logger.info("======Query All User Information: request body={}", requestBody);

			JSONObject requestJson = JSONObject.parseObject(requestBody);
			int cursor = 0;
			int size = PAGE_SIZE;
			if (requestJson.containsKey("index")) {
				cursor = requestJson.getIntValue("index");
			}
			if (requestJson.containsKey("size")) {
				size = requestJson.getIntValue("size");
			}
			List<PatientBean> patients = userService.getAllPatients(cursor, size);
			long allCount = userService.getAllPatientCount();

			JSONObject obj = new JSONObject();
			obj.put("items", patients);
			obj.put("totalCount", allCount);

			result.setData(obj);
			result.setCode(1);
			result.setMessage("操作成功");
		} catch (SystemException e) {
			logger.error("Failed to query all patient information.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		} catch (Exception e) {
			logger.error("Failed to query all patient information.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		}
		logger.info("======Query All User Information, return code={}", result.getCode());
		return result;
	}

	/**
	 * 提交订单
	 * 
	 * @param requestBody
	 *            the request body
	 * @return api response
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<JSONObject> orderSubmit(@RequestBody String requestBody) {
		ApiResponse<JSONObject> result = new ApiResponse<JSONObject>();

		String orderNo = "";
		JSONObject data = new JSONObject();
		try {
			logger.info("======submit order, order info=" + requestBody);
			JSONObject requestJson = JSON.parseObject(requestBody);
			if (requestJson.containsKey("mac") && StringUtil.isBlank(requestJson.getString("mac"))) {
				result.setCode(0);
				result.setMessage("请上传PAD的唯一标示MAC");
				return result;
			}
			String mac = requestJson.getString("mac");
			String submitOrderFlag = MemoryCacheManager.get(SUBMIT_ORDER_FLAG);
			if (StringUtil.isNotBlank(submitOrderFlag) && !mac.equals(submitOrderFlag)) { // 如果有值，说明有人在同步
				result.setCode(9);
				result.setMessage("其他操作员正在同步订单数据，请稍后再进行同步");
				return result;
			}

			String operatorId = requestJson.getString("operatorId");
			String patientNo = requestJson.getString("patientNo");
			orderNo = requestJson.getString("orderNo");
			String mark = requestJson.getString("mark");
			String consumeDate = requestJson.getString("consumeDate");
			String period = requestJson.getString("period");
			Boolean payNow = requestJson.getBoolean("payNow");

			JSONArray orderItemsJson = requestJson.getJSONArray("orderItems");
			PatientInfoModel patient = userService.getPatientInformation(patientNo);
			if (patient == null) {
				throw new SystemException("住院号[" + patientNo + "]不存在");
			}

			if (StringUtil.isBlank(orderNo)) {
				orderNo = BizCodeBuilder.orderNo();
			}
			data.put("orderNo", orderNo);

			Double totalPrice = 0D;
			int totalCount = 0;
			List<OrderItemModel> items = new ArrayList<OrderItemModel>();
			for (int i = 0; i < orderItemsJson.size(); i++) {
				JSONObject itemJson = orderItemsJson.getJSONObject(i);
				OrderItemModel orderItem = new OrderItemModel();
				orderItem.setPrice(itemJson.getDouble("price"));
				orderItem.setCount(itemJson.getIntValue("count"));
				orderItem.setProductId(itemJson.getLongValue("productId"));
				orderItem.setProductName(itemJson.getString("productName"));
				orderItem.setMark(mark);
				orderItem.setCreateTime(DateUtil.now());
				orderItem.setUpdateTime(DateUtil.now());
				items.add(orderItem);

				totalPrice += (itemJson.getIntValue("count") * itemJson.getDoubleValue("price"));
				totalCount += itemJson.getIntValue("count");
			}

			OrderModel order = new OrderModel();
			order.setOrderNo(orderNo);
			order.setPeriod(period);
			order.setConsumeDate(DateUtil.parseDate(consumeDate, DateUtil.PATTERN_YYYY_MM_DD));
			order.setUserId(String.valueOf(patient.getId()));
			order.setStatus(0); // 为支付前的状态
			order.setPrice(totalPrice);
			order.setItemCount(totalCount);
			order.setCreateTime(DateUtil.now());
			order.setUpdateTime(DateUtil.now());

			try {
				orderService.submitOrder(order, items);
				data.put("status", 7);
				data.put("promptMessage", "支付失败");

				if (payNow) {
					try {
						orderService.payOrder(orderNo);
						data.put("status", 1);
						data.put("promptMessage", "支付成功");
					} catch (SystemException e) {
						logger.error("Failed to pay order.", e);
						data.put("status", 7);
						data.put("promptMessage", "支付失败");
						throw e;
					} catch (Exception e) {
						data.put("status", 7);
						data.put("promptMessage", "支付失败");
					}
				}
			} catch (Exception e1) {
				logger.error("提交失败, orderNo={}", order.getOrderNo(), e1);
				throw e1;
			}
			result.setCode(1);
			result.setMessage("操作成功");
			result.setData(data);
		} catch (SystemException e) {
			logger.error("Failed to submit order.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		} catch (Exception e) {
			logger.error("Failed to submit order.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		}

		logger.info("======Submit order result[orderNo=" + orderNo + "],"
				+ JSON.toJSONString(result));
		return result;
	}

	/**
	 * 查询订单
	 * 
	 * @return api response
	 */
	@RequestMapping(value = "/order/orders", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<JSONObject> queryOrders(@RequestBody String requestBody) {
		ApiResponse<JSONObject> result = new ApiResponse<JSONObject>();
		try {
			logger.info("======Query Orders, Request Body=" + requestBody);

			JSONObject requestJson = JSON.parseObject(requestBody);
			int cursor = requestJson.containsKey("index") ? requestJson.getIntValue("index") : 0;
			int size = requestJson.containsKey("size") ? requestJson.getIntValue("size")
					: PAGE_SIZE;
			String patientNo = requestJson.containsKey("patientNo") ? requestJson
					.getString("patientNo") : "";

			List<OrderBean> beans = orderService.queryOrders(patientNo, cursor, size);
			Long allCount = orderService.queryOrderCount(patientNo);

			JSONObject obj = new JSONObject();
			obj.put("items", beans);
			obj.put("totalCount", allCount);

			result.setData(obj);
			result.setCode(1);
			result.setMessage("操作成功");
		} catch (SystemException e) {
			logger.error("Failed to submit order.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		} catch (Exception e) {
			logger.error("Failed to submit order.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		}
		logger.info("======Query Orders result, Request Body:{},result:{}", requestBody,
				JSON.toJSONString(result));
		return result;
	}

	/**
	 * 订单支付. 0 未上送； 1 已支付； 2 申请审核； 3 申请退款； 4 退款成功； 5 退款失败； 6 不处理； 7 支付失败；
	 * 
	 * @param orderNo
	 *            the order no
	 * @return api response
	 */
	@ResponseBody
	@RequestMapping(value = "/order/{orderNo}/pay", method = RequestMethod.POST)
	public ApiResponse<Object> orderPay(@PathVariable("orderNo") String orderNo) {
		ApiResponse<Object> result = new ApiResponse<Object>();
		try {
			logger.info("======Order Pay, orderNo=" + orderNo);

			orderService.payOrder(orderNo);
			result.setCode(1);
			result.setMessage("支付成功");
		} catch (SystemException e) {
			logger.error("Failed to pay order, orderNo=" + orderNo, e);
			result.setCode(0);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("Failed to pay order, orderNo=" + orderNo, e);
			result.setCode(0);
			result.setMessage("支付失败");
		}
		logger.info("======Order pay, orderNo={}, result={}", orderNo, JSON.toJSONString(result));
		return result;
	}

	/**
	 * 订单退款. 0 未上送； 1 已支付； 2 申请审核； 3 申请退款； 4 退款成功； 5 退款失败； 6 不处理； 7 支付失败；
	 * 
	 * @param orderNo
	 *            the order no
	 * @return api response
	 */
	@ResponseBody
	@RequestMapping(value = "/order/{orderNo}/refund", method = RequestMethod.POST)
	public ApiResponse<Object> orderRefund(@PathVariable("orderNo") String orderNo) {
		ApiResponse<Object> result = new ApiResponse<Object>();
		try {
			logger.info("======Order Refund, orderNo=" + orderNo);
			orderService.refundOrder(orderNo);
			result.setCode(1);
			result.setMessage("退款成功");
		} catch (SystemException e) {
			logger.error("Failed to refund order, orderNo=" + orderNo, e);
			result.setCode(0);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("Failed to refund order, orderNo=" + orderNo, e);
			result.setCode(0);
			result.setMessage("退款失败");
		}
		logger.info("======Order Refund, orderNo={}, result={}", orderNo, JSON.toJSONString(result));
		return result;
	}

	/**
	 * 检查缓存数据更新
	 * 
	 * @return api response
	 */
	@ResponseBody
	@RequestMapping(value = "/cache/check", method = RequestMethod.GET)
	public ApiResponse<CacheCheckBean> checkCachedData() {
		ApiResponse<CacheCheckBean> result = new ApiResponse<CacheCheckBean>();
		try {
			CacheCheckBean cacheCheckBean = new CacheCheckBean();
			result.setData(cacheCheckBean);
			result.setCode(1);
			result.setMessage("操作成功");
		} catch (Exception e) {
			logger.error("Failed to check check info.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		}
		return result;
	}

	/**
	 * 版本更新.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the api response
	 */
	@ResponseBody
	@RequestMapping(value = "/version-update", method = RequestMethod.POST)
	public ApiResponse<JSONObject> versionUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		ApiResponse<JSONObject> result = new ApiResponse<JSONObject>();

		try {
			AppVersionBean appVersion = configService.getAppVersion();
			JSONObject data = new JSONObject(true);
			data.put("versionInfo", appVersion);
			result.setCode(1);
			result.setMessage("操作成功");
			result.setData(data);
			return result;
		} catch (CacheException e) {
			logger.error("Failed to get app version.", e);
			result.setCode(0);
			result.setMessage("操作缓存失败");
		} catch (Exception e) {
			logger.error("Failed to get app version.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		}
		logger.info("======Version Update, result={}", JSON.toJSONString(result));
		return result;
	}

	/**
	 * 锁定或者解锁.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the api response
	 */
	@ResponseBody
	@RequestMapping(value = "/operate/lock", method = RequestMethod.POST)
	public ApiResponse<JSONObject> operateLock(@RequestBody String requestBody,
			HttpServletRequest request, HttpServletResponse response) {
		ApiResponse<JSONObject> result = new ApiResponse<JSONObject>();
		try {
			JSONObject bodyJson = JSONObject.parseObject(requestBody);
			String mac = bodyJson.getString("mac");
			String operate = bodyJson.getString("operate");
			String operatorName = bodyJson.getString("operatorName");
			if (StringUtil.isBlank(mac) || StringUtil.isBlank(operatorName)
					|| StringUtil.isBlank(operate)) {
				result.setCode(0);
				result.setMessage("参数不能为空");
				return result;
			}

			if ("unlock".equals(operate)) { // 解锁
				String submitOrderFlag = MemoryCacheManager.get(SUBMIT_ORDER_FLAG);
				if (StringUtil.isBlank(submitOrderFlag)) {
					result.setCode(1);
					result.setMessage("操作成功");
				} else {
					if (mac.equalsIgnoreCase(submitOrderFlag)) {
						MemoryCacheManager.put(SUBMIT_ORDER_FLAG, "");
						MemoryCacheManager.put(SUBMIT_ORDER_FLAG_USER, "");

						result.setCode(1);
						result.setMessage("操作成功");
					} else {
						result.setCode(0);
						// result.setMessage(MemoryCacheManager.get(SUBMIT_ORDER_FLAG_USER)
						// + "正在同步数据，请稍后重试");
						result.setMessage("其他操作员正在同步订单数据，请稍后再进行同步");
					}
				}
			}
			if ("lock".equalsIgnoreCase(operate)) { // 锁定
				String submitOrderFlag = MemoryCacheManager.get(SUBMIT_ORDER_FLAG);
				if (StringUtil.isBlank(submitOrderFlag)) { // 没人锁定
					MemoryCacheManager.put(SUBMIT_ORDER_FLAG, mac);
					MemoryCacheManager.put(SUBMIT_ORDER_FLAG_USER, operatorName);
					result.setCode(1);
					result.setMessage("操作成功");
				} else { // 有人锁定
					if (mac.equalsIgnoreCase(submitOrderFlag)) { // 被自己锁定
						result.setCode(1);
						result.setMessage("操作成功");
					} else { // 被他人锁定
						result.setCode(0);
						// result.setMessage(MemoryCacheManager.get(SUBMIT_ORDER_FLAG_USER)
						// + "正在同步数据，请稍后重试");
						result.setMessage("其他操作员正在同步订单数据，请稍后再进行同步");
					}
				}
			}
		} catch (Exception e) {
			logger.error("Failed to lock or unlock operate.", e);
			result.setCode(0);
			result.setMessage("操作失败");
		}
		logger.info("======operate lock, result={}", JSON.toJSONString(result));
		return result;
	}
}
