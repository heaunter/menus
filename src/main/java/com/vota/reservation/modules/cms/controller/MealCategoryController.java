package com.vota.reservation.modules.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.modules.cms.entity.bean.MealCategoryBean;
import com.vota.reservation.modules.cms.service.MealService;

/**
 * 餐品相关Apis Created by mengzhg on 2017/3/22.
 */
@Controller
@RequestMapping("/meal")
public class MealCategoryController {

	private Logger logger = LoggerFactory.getLogger(MealCategoryController.class);

	@Autowired
	private MealService mealService;

	/**
	 * 获取餐品种类
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return meal categories
	 */
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ModelAndView getMealCategories(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.setViewName("categories");
			List<MealCategoryBean> mealCategories = mealService.getMealCategories();
			modelView.addObject("categories", mealCategories);
		} catch (SystemException e) {
			logger.error("Failed to get all categories.", e);
		}
		return modelView;
	}

	/**
	 * 新增餐品种类
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return model and view
	 */
	@RequestMapping(value = "/category/add", method = RequestMethod.GET)
	public ModelAndView addMealCategoryGet(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("category-add");
		return modelView;
	}

	/**
	 * 新增餐品种类
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return model and view
	 */
	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public ModelAndView addMealCategoryPost(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			MealCategoryBean categoryBean = new MealCategoryBean();
			categoryBean.setName(request.getParameter("name"));
			int sort = 1;
			try {
				sort = Integer.parseInt(request.getParameter("sort"));
			} catch (Exception e) {
				logger.error("", e);
			}
			boolean flag = mealService.checkCatetory(request.getParameter("name"));
			if (flag) {
				modelView.setViewName("category-add");
				modelView.addObject("propMsg", "种类已经存在");
				return modelView;
			}
			categoryBean.setSort(sort);
			categoryBean.setSort(Integer.parseInt(request.getParameter("sort")));
			mealService.addCategory(categoryBean);
			modelView.setViewName("redirect:/meal/categories");
		} catch (Exception e) {
			logger.error("Failed to add category.", e);
		}
		return modelView;
	}

	/**
	 * 删除餐品种类
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return model and view
	 */
	@RequestMapping(value = "/category/{categoryId}/delete", method = RequestMethod.GET)
	public ModelAndView deleteMealCategory(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("categoryId") Long categoryId) {
		ModelAndView modelView = new ModelAndView();
		try {
			mealService.deleteCategory(categoryId);
			modelView.setViewName("redirect:/meal/categories");
			mealService.deleteCategory(categoryId);
		} catch (Exception e) {
			logger.error("Failed to delete category.", e);
		}
		return modelView;
	}

	/**
	 * 修改餐品种类GET
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return model and view
	 */
	@RequestMapping(value = "/category/{categoryId}/update", method = RequestMethod.GET)
	public ModelAndView updateMealCategoryGet(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("categoryId") Long categoryId) {
		ModelAndView modelView = new ModelAndView();
		try {
			MealCategoryBean bean = mealService.getMealCategory(categoryId);
			modelView.addObject("category", bean);
			modelView.setViewName("category-update");
		} catch (Exception e) {
			logger.error("Failed to get category info.", e);
		}
		return modelView;
	}

	/**
	 * 修改餐品种类POST
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return model and view
	 */
	@RequestMapping(value = "/category/{categoryId}/update", method = RequestMethod.POST)
	public ModelAndView updateMealCategoryPost(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("categoryId") Long categoryId) {
		ModelAndView modelView = new ModelAndView();
		try {
			MealCategoryBean category = new MealCategoryBean();
			category.setId(categoryId);
			category.setName(request.getParameter("name"));
			category.setSort(Integer.parseInt(request.getParameter("sort")));

			mealService.updateCategory(category);
			modelView.setViewName("redirect:/meal/categories");
		} catch (Exception e) {
			logger.error("Failed to update meal category.", e);
		}
		return modelView;
	}

}
