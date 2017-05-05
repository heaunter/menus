package com.vota.reservation.modules.cms.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.MoneyUtils;
import com.vota.reservation.common.util.StringUtil;
import com.vota.reservation.modules.cms.entity.bean.DeliveryReportBean;
import com.vota.reservation.modules.cms.entity.bean.MealCategoryBean;
import com.vota.reservation.modules.cms.entity.bean.PrepareReportBean;
import com.vota.reservation.modules.cms.entity.dto.MealReportDto;
import com.vota.reservation.modules.cms.entity.model.ProductInfoModel;
import com.vota.reservation.modules.cms.entity.model.SickAreaInfoModel;
import com.vota.reservation.modules.cms.service.DeptBedService;
import com.vota.reservation.modules.cms.service.MealService;
import com.vota.reservation.modules.cms.service.OrderService;

/**
 * 报表Apis Created by mengzhg on 2017/3/23.
 */
@Controller
@RequestMapping("/report")
public class ReportController {
	private Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private DeptBedService deptBedService;
	@Autowired
	private MealService mealService;

	/**
	 * 配送人员报表
	 * 
	 * @param request
	 *            请求
	 * @param session
	 *            会话
	 * @param date
	 *            the date
	 * @param pageIndex
	 *            the page index
	 * @return ModelAndView model and view
	 */
	@RequestMapping(value = "/delivery", method = RequestMethod.GET)
	public ModelAndView deliveryReport(@RequestParam(value = "date", required = false, defaultValue = "") String date,
			@RequestParam(value = "period", required = false, defaultValue = "breakfast") String period,
			@RequestParam(value = "areaNo", required = false, defaultValue = "") String areaNo) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("areaNo", areaNo);

		int pageSize = Integer.MAX_VALUE;
		if (StringUtil.isBlank(date)) {
			date = DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD);
		}

		String periodName = "[早]：";
		if ("breakfast".equals(period)) {
			periodName = "[早]：";

		} else if ("lunch".equals(period)) {
			periodName = "[午]：";

		} else if ("dinner".equals(period)) {
			periodName = "[晚]：";
		}

		// 全部病区
		List<SickAreaInfoModel> areas = deptBedService.getDistinctAreas();
		modelAndView.addObject("areas", areas);

		List<String> areaNoList = new ArrayList<String>();
		if (StringUtil.isNotBlank(areaNo)) {
			areaNoList = Arrays.asList(areaNo.split(","));
		}

		String areaNames = "全部病区";
		if (areaNoList.size() > 0) {
			List<SickAreaInfoModel> areasById = deptBedService.getAreaByNos(areaNoList);
			areaNames = "";
			for (SickAreaInfoModel sickAreaInfoModel : areasById) {
				areaNames += (sickAreaInfoModel.getAreaName() + "，");
			}
		}

		List<DeliveryReportBean> deliveryReportBeanList = orderService.getDeliveryReport(
				DateUtil.parseDate(date, "yyyy-MM-dd"), period, areaNoList, 0, pageSize);

		List<DeliveryReportBean> ret = new ArrayList<DeliveryReportBean>();
		for (int i = 0; i < deliveryReportBeanList.size(); i++) {
			DeliveryReportBean bean = deliveryReportBeanList.get(i);
			if (i == 0) {
				bean.setMealDetail(periodName + bean.getMealDetail());
				ret.add(bean);
				continue;
			}
			DeliveryReportBean oldBean = ret.get(ret.size() - 1);
			if (bean.getCardNo().equals(oldBean.getCardNo())) {
				oldBean.setPrice(MoneyUtils.format(oldBean.getPrice() + bean.getPrice()));
				oldBean.setMealDetail(oldBean.getMealDetail() + " " + bean.getMealDetail());
			} else {
				bean.setMealDetail(periodName + bean.getMealDetail());
				ret.add(bean);
			}
		}

		modelAndView.addObject("checkedAreaNos", areaNoList);
		modelAndView.addObject("deliveryReport", ret);
		modelAndView.addObject("date", date);
		modelAndView.addObject("period", period);
		modelAndView.setViewName("report-delivery");
		modelAndView.addObject("areaNames", areaNames);
		return modelAndView;
	}

	/**
	 * 配送报表打印
	 * 
	 * @param request
	 * @param session
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/delivery/print", method = RequestMethod.GET)
	public ModelAndView printDeliveryReport(
			@RequestParam(value = "date", required = false, defaultValue = "") String date,
			@RequestParam(value = "period", required = false, defaultValue = "breakfast") String period,
			@RequestParam(value = "areaNo", required = false, defaultValue = "") String areaNo) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("printDate", DateUtil.now());

		int pageSize = Integer.MAX_VALUE;
		if (StringUtil.isBlank(date)) {
			date = DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD);
		}

		String periodName = "[早]：";
		if ("breakfast".equals(period)) {
			periodName = "[早]：";

		} else if ("lunch".equals(period)) {
			periodName = "[午]：";

		} else if ("dinner".equals(period)) {
			periodName = "[晚]：";
		}

		List<String> list = new ArrayList<String>();
		if (StringUtil.isNotBlank(areaNo)) {
			list = Arrays.asList(areaNo.split(","));
		}

		String areaNames = "全部病区";
		if (list.size() > 0) {
			List<SickAreaInfoModel> areasById = deptBedService.getAreaByNos(list);
			areaNames = "";
			for (SickAreaInfoModel sickAreaInfoModel : areasById) {
				areaNames += (sickAreaInfoModel.getAreaName() + "，");
			}
		}

		Double money = 0D;
		List<DeliveryReportBean> deliveryReportBeanList = orderService.getDeliveryReport(
				DateUtil.parseDate(date, "yyyy-MM-dd"), period, list, 0, pageSize);

		List<DeliveryReportBean> ret = new ArrayList<DeliveryReportBean>();
		for (int i = 0; i < deliveryReportBeanList.size(); i++) {
			DeliveryReportBean bean = deliveryReportBeanList.get(i);
			money += bean.getPrice();
			if (i == 0) {
				bean.setMealDetail(periodName + bean.getMealDetail());
				ret.add(bean);
				continue;
			}
			DeliveryReportBean oldBean = ret.get(ret.size() - 1);
			if (bean.getCardNo().equals(oldBean.getCardNo())) {
				oldBean.setPrice(MoneyUtils.format(oldBean.getPrice() + bean.getPrice()));
				oldBean.setMealDetail(oldBean.getMealDetail() + " " + bean.getMealDetail());
			} else {
				bean.setMealDetail(periodName + bean.getMealDetail());
				ret.add(bean);
			}
		}

		modelAndView.addObject("deliveryReport", ret);
		modelAndView.addObject("money", MoneyUtils.format2String(money));
		modelAndView.addObject("date", date);
		modelAndView.addObject("period", period);
		modelAndView.addObject("areaNames", areaNames);
		modelAndView.setViewName("report-delivery-print");
		return modelAndView;
	}

	/**
	 * 配送总汇总报表.
	 * 
	 * @param date
	 *            the date
	 * @param period
	 *            the period
	 * @param areaNo
	 *            the area no
	 * @return the model and view
	 */
	@RequestMapping(value = "/delivery/summary", method = RequestMethod.GET)
	public ModelAndView deliveryAllReport(
			@RequestParam(value = "date", required = false, defaultValue = "") String date,
			@RequestParam(value = "period", required = false, defaultValue = "") String period,
			@RequestParam(value = "areaNo", required = false, defaultValue = "") String areaNo) {

		ModelAndView modelAndView = new ModelAndView();
		if (StringUtil.isBlank(date)) {
			date = DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD);
		}

		// 全部病区
		List<SickAreaInfoModel> areas = deptBedService.getDistinctAreas();
		modelAndView.addObject("areas", areas);
		modelAndView.addObject("period", period);
		if ("all".equals(period)) {
			period = "";
		}
		List<String> areaNoList = new ArrayList<String>();
		if (StringUtil.isNotBlank(areaNo)) {
			areaNoList = Arrays.asList(areaNo.split(","));
		}

		String areaNames = "全部病区";
		if (areaNoList.size() > 0) {
			List<SickAreaInfoModel> areasById = deptBedService.getAreaByNos(areaNoList);
			areaNames = "";
			for (SickAreaInfoModel sickAreaInfoModel : areasById) {
				areaNames += (sickAreaInfoModel.getAreaName() + "，");
			}
		}
		try {
			List<PrepareReportBean> prepareReportBeanList = orderService.getSummaryPrepareMaterialReport(
					DateUtil.parseDate(date, "yyyy-MM-dd"), period, areaNoList);
			modelAndView.addObject("prepareReport", prepareReportBeanList);
			modelAndView.setViewName("report-delivery-summary");
		} catch (Exception e) {
			logger.error("Failed to prepare report.", e);
		}
		modelAndView.addObject("checkedAreaNos", areaNoList);
		modelAndView.addObject("areaNo", areaNo);
		modelAndView.addObject("date", date);
		modelAndView.addObject("areaNames", areaNames);
		return modelAndView;
	}

	/**
	 * 配送总汇总报表.
	 * 
	 * @param date
	 *            the date
	 * @param period
	 *            the period
	 * @param areaNo
	 *            the area no
	 * @return the model and view
	 */
	@RequestMapping(value = "/delivery/summary/print", method = RequestMethod.GET)
	public ModelAndView printDeliveryAllReport(
			@RequestParam(value = "date", required = false, defaultValue = "") String date,
			@RequestParam(value = "period", required = false, defaultValue = "") String period,
			@RequestParam(value = "areaNo", required = false, defaultValue = "") String areaNo) {

		ModelAndView modelAndView = new ModelAndView();
		if (StringUtil.isBlank(date)) {
			date = DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD);
		}

		modelAndView.addObject("period", period);
		if ("all".equals(period)) {
			period = "";
		}
		List<String> areaNoList = new ArrayList<String>();
		if (StringUtil.isNotBlank(areaNo)) {
			areaNoList = Arrays.asList(areaNo.split(","));
		}

		String areaNames = "全部病区";
		if (areaNoList.size() > 0) {
			List<SickAreaInfoModel> areasById = deptBedService.getAreaByNos(areaNoList);
			areaNames = "";
			for (SickAreaInfoModel sickAreaInfoModel : areasById) {
				areaNames += (sickAreaInfoModel.getAreaName() + "，");
			}
		}
		try {
			List<PrepareReportBean> prepareReportBeanList = orderService.getSummaryPrepareMaterialReport(
					DateUtil.parseDate(date, "yyyy-MM-dd"), period, areaNoList);

			Double totalAmount = 0D;
			for (PrepareReportBean prepareReportBean : prepareReportBeanList) {
				totalAmount += prepareReportBean.getPrice();
			}
			modelAndView.addObject("totalMoney", MoneyUtils.format2String(totalAmount));

			modelAndView.addObject("prepareReport", prepareReportBeanList);
			modelAndView.setViewName("report-delivery-summary-print");
		} catch (Exception e) {
			logger.error("Failed to prepare report.", e);
		}
		modelAndView.addObject("printDate", DateUtil.now());
		modelAndView.addObject("date", date);
		modelAndView.addObject("areaNames", areaNames);
		return modelAndView;
	}

	/**
	 * 备料人员报表
	 * 
	 * @param request
	 *            请求
	 * @param session
	 *            会话
	 * @param date
	 *            查看日期
	 * @param pageIndex
	 *            the page index
	 * @return ModelAndView model and view
	 */
	@RequestMapping(value = "/prepare", method = RequestMethod.GET)
	public ModelAndView prepareMaterial(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "period", required = false, defaultValue = "") String period,
			@RequestParam(value = "index", required = false, defaultValue = "0") int pageIndex) {

		ModelAndView modelAndView = new ModelAndView();

		int pageSize = Integer.MAX_VALUE;
		if (StringUtil.isBlank(date)) {
			date = DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD);
		}
		modelAndView.addObject("date", date);
		modelAndView.addObject("period", period);
		if ("all".equals(period)) {
			period = "";
		}
		try {
			List<PrepareReportBean> prepareReportBeanList = orderService.getPrepareMaterialReport(
					DateUtil.parseDate(date, "yyyy-MM-dd"), period, pageIndex, pageSize);

			modelAndView.addObject("prepareReport", prepareReportBeanList);
			modelAndView.setViewName("report-prepare");
		} catch (Exception e) {
			logger.error("Failed to prepare report.", e);
		}
		return modelAndView;
	}

	/**
	 * 打印备料报表.
	 * 
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @param date
	 *            the date
	 * @param pageIndex
	 *            the page index
	 * @return the model and view
	 */
	@RequestMapping(value = "/prepare/print", method = RequestMethod.GET)
	public ModelAndView printPrepareMaterial(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "period", required = false, defaultValue = "") String period,
			@RequestParam(value = "date", required = false, defaultValue = "") String date) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("report-prepare-print");
		modelView.addObject("date", date);
		modelView.addObject("period", period);
		modelView.addObject("printDate", DateUtil.now());
		if (StringUtil.isBlank(date)) {
			modelView.addObject("prepareReport", new ArrayList<PrepareReportBean>());
		} else {

			if ("all".equals(period)) {
				period = "";
			}
			List<PrepareReportBean> prepareReportBeanList = orderService.getPrepareMaterialReport(
					DateUtil.parseDate(date, "yyyy-MM-dd"), period, 0, Integer.MAX_VALUE);

			Double totalAmount = 0D;
			for (PrepareReportBean prepareReportBean : prepareReportBeanList) {
				totalAmount += prepareReportBean.getPrice();
			}

			modelView.addObject("totalMoney", MoneyUtils.format2String(totalAmount));
			modelView.addObject("prepareReport", prepareReportBeanList);
		}
		return modelView;

	}

	/**
	 * 某个产品，都有多少人预订.
	 * 
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @param date
	 *            the date
	 * @param period
	 *            the period
	 * @param pageIndex
	 *            the page index
	 * @return the model and view
	 */
	@RequestMapping(value = "/meal", method = RequestMethod.GET)
	public ModelAndView mealReport(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "categoryId", defaultValue = "", required = false) String categoryId,
			@RequestParam(value = "mealId", defaultValue = "", required = false) String mealId,
			@RequestParam(value = "date", defaultValue = "", required = false) String date,
			@RequestParam(value = "period", required = false, defaultValue = "") String period) {

		ModelAndView modelAndView = new ModelAndView("meal-cal");
		try {
			if (StringUtil.isBlank(date)) {
				date = DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD);
			}
			if (StringUtil.isNotBlank(period) && !"all".equals(period)) {
				List<MealReportDto> result = mealService.getMealReport(mealId, date, period);
				modelAndView.addObject("reports" + period, result);
			} else {
				List<MealReportDto> breakfastResult = mealService.getMealReport(mealId, date, "breakfast");
				modelAndView.addObject("reportsbreakfast", breakfastResult);
				List<MealReportDto> lunchResult = mealService.getMealReport(mealId, date, "lunch");
				modelAndView.addObject("reportslunch", lunchResult);
				List<MealReportDto> dinnerResult = mealService.getMealReport(mealId, date, "dinner");
				modelAndView.addObject("reportsdinner", dinnerResult);
			}

			List<MealCategoryBean> categories = mealService.getMealCategories();
			modelAndView.addObject("categories", categories);
		} catch (SystemException e) {
			logger.error("Failed to get meal report.", e);
		}
		modelAndView.addObject("date", date);
		modelAndView.addObject("categoryId", categoryId);
		modelAndView.addObject("mealId", mealId);
		modelAndView.addObject("period", period);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/meal/print", method = RequestMethod.GET)
	public ModelAndView printMealReport(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "mealId", defaultValue = "", required = false) String mealId,
			@RequestParam(value = "date", defaultValue = "", required = false) String date,
			@RequestParam(value = "period", required = false, defaultValue = "") String period) {

		ModelAndView modelAndView = new ModelAndView("meal-cal-print");
		try {
			if (StringUtil.isBlank(date)) {
				date = DateUtil.formatDate(DateUtil.now(), DateUtil.PATTERN_YYYY_MM_DD);
			}
			if (StringUtil.isNotBlank(period) && !"all".equals(period)) {
				List<MealReportDto> result = mealService.getMealReport(mealId, date, period);
				modelAndView.addObject("reports" + period, result);
			} else {
				List<MealReportDto> breakfastResult = mealService.getMealReport(mealId, date, "breakfast");
				modelAndView.addObject("reportsbreakfast", breakfastResult);
				List<MealReportDto> lunchResult = mealService.getMealReport(mealId, date, "lunch");
				modelAndView.addObject("reportslunch", lunchResult);
				List<MealReportDto> dinnerResult = mealService.getMealReport(mealId, date, "dinner");
				modelAndView.addObject("reportsdinner", dinnerResult);
			}
			ProductInfoModel meal = mealService.getMeal(Long.valueOf(mealId));
			modelAndView.addObject("mealBean", meal);
			
			List<MealCategoryBean> categories = mealService.getMealCategories();
			modelAndView.addObject("categories", categories);
		} catch (SystemException e) {
			logger.error("Failed to get meal report.", e);
		}
		modelAndView.addObject("date", date);
		modelAndView.addObject("period", period);
		modelAndView.addObject("printDate", DateUtil.now());
		return modelAndView;
	}

}
