package com.vota.reservation.modules.cms.controller;

import com.alibaba.fastjson.JSONObject;
import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.http.ApiResponse;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.StringUtil;
import com.vota.reservation.modules.cms.entity.bean.OrderBean;
import com.vota.reservation.modules.cms.entity.model.SickAreaInfoModel;
import com.vota.reservation.modules.cms.service.DeptBedService;
import com.vota.reservation.modules.cms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 订单
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private DeptBedService deptBedService;

	/**
	 * 获取所有订单信息.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param cursor
	 *            the cursor
	 * @param size
	 *            the size
	 * @return the model and view
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView orders(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "corsor", defaultValue = "0", required = false) int cursor,
			@RequestParam(name = "size", defaultValue = "100000", required = false) int size,
			@RequestParam(name = "areaNo", defaultValue = "", required = false) String areaNo,
			@RequestParam(name = "period", defaultValue = "", required = false) String period,
			@RequestParam(name = "status", defaultValue = "1", required = false) int status,
			@RequestParam(name = "date", defaultValue = "", required = false) String date) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("period", period);
		modelAndView.addObject("status", status);

		try {
			if (StringUtil.isBlank(date)) {
				date = DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD);
			}
			List<String> areaNos = new ArrayList<String>();
			if (StringUtil.isNotBlank(areaNo)) {
				areaNos = Arrays.asList(areaNo.split(","));
			}
			modelAndView.addObject("checkedAreaNos", areaNos);

			String areaNames = "全部病区";
			if (areaNos.size() > 0) {
				List<SickAreaInfoModel> areasById = deptBedService.getAreaByNos(areaNos);
				areaNames = "";
				for (SickAreaInfoModel sickAreaInfoModel : areasById) {
					areaNames += (sickAreaInfoModel.getAreaName() + "，");
				}
			}

			// 全部病区
			List<SickAreaInfoModel> areas = deptBedService.getDistinctAreas();
			modelAndView.addObject("areas", areas);

			List<OrderBean> orderBeans = orderService.queryAllOrders(
					DateUtil.parseDate(date, DateUtil.PATTERN_YYYY_MM_DD), areaNos, period.trim(),
					status, cursor, size);

			modelAndView.addObject("date", date);
			modelAndView.addObject("areaNames", areaNames);
			modelAndView.addObject("orders", orderBeans);
			modelAndView.addObject("date", date);
		} catch (SystemException e) {
			logger.error("Failed to query all orders.", e);
			modelAndView.setViewName("orders");
		}
		modelAndView.setViewName("orders");
		return modelAndView;
	}

	/**
	 * 订单详情.
	 * 
	 * @param request
	 *            the request
	 * @param orderNo
	 *            the order no
	 * @return the api response
	 */
	@RequestMapping(value = "/{orderNo}/detail", method = RequestMethod.GET)
	public ApiResponse<JSONObject> detail(HttpServletRequest request,
			@PathVariable("orderNo") String orderNo) {

		return null;
	}

	/**
	 * 订单退款.
	 * 
	 * @param request
	 *            the request
	 * @param orderNo
	 *            the order no
	 * @return the api response
	 */
	@RequestMapping(value = "/{orderNo}/refund", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<JSONObject> refund(HttpServletRequest request,
			@PathVariable("orderNo") String orderNo) {
		ApiResponse<JSONObject> result = new ApiResponse<JSONObject>();
		try {
			orderService.refundOrder(orderNo);
			result.setCode(1);
			result.setMessage("退款成功");
		} catch (SystemException e) {
			logger.error("Failed to refund order,orderNo=", orderNo, e);
			result.setCode(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
