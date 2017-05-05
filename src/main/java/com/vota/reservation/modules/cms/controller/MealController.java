package com.vota.reservation.modules.cms.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.http.ApiResponse;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.ImageUtil;
import com.vota.reservation.common.util.PropertiesUtil;
import com.vota.reservation.common.util.StringUtil;
import com.vota.reservation.modules.cms.entity.bean.MealBean;
import com.vota.reservation.modules.cms.entity.bean.MealCategoryBean;
import com.vota.reservation.modules.cms.entity.model.ProductInfoModel;
import com.vota.reservation.modules.cms.service.MealService;

/**
 * 
 * 餐品Apis
 * 
 * @author mengzhg@126.com
 */
@Controller
@RequestMapping("/meal")
public class MealController {

	private Logger logger = LoggerFactory.getLogger(MealController.class);

	@Autowired
	private MealService mealService;

	/**
	 * 查询餐品信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/meals", method = RequestMethod.GET)
	public ModelAndView queryMeals(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "categoryId", required = false, defaultValue = "") String categoryId) {
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.setViewName("meals");
			List<MealBean> meals = mealService.queryMeals(categoryId, 0, Integer.MAX_VALUE);
			List<MealCategoryBean> categories = mealService.getMealCategories();

			modelView.addObject("categories", categories);
			modelView.addObject("meals", meals);
			modelView.addObject("categoryId", categoryId);

		} catch (SystemException e) {
			logger.error("Failed to query meals.", e);
		}
		return modelView;
	}

	/**
	 * 查询餐品信息By AJAX
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/meals/ajax", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<List<MealBean>> queryMealsAjax(
			@RequestParam(value = "categoryId", required = false, defaultValue = "") String categoryId) {
		List<MealBean> meals = mealService.queryMeals(categoryId, 0, Integer.MAX_VALUE);
		ApiResponse<List<MealBean>> result = new ApiResponse<List<MealBean>>(1, "操作成功", meals);
		return result;
	}

	/**
	 * 新增餐品Get.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addMealGet(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			List<MealCategoryBean> categories = mealService.getMealCategories();
			modelView.addObject("categories", categories);
		} catch (SystemException e) {
			logger.error("Failed to goto add meals page.", e);
		}
		modelView.setViewName("meal-add");
		return modelView;
	}

	/**
	 * 新增餐品Post.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addMealPost(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:/meal/meals");
		try {
			boolean flag = mealService.checkMeal(request.getParameter("name"));
			if (flag) {
				List<MealCategoryBean> categories = mealService.getMealCategories();
				modelView.addObject("categories", categories);

				modelView.setViewName("meal-add");
				modelView.addObject("propMsg", "餐品已经存在");
				return modelView;
			}

			ProductInfoModel model = new ProductInfoModel();
			model.setName(request.getParameter("name"));
			model.setDescription(request.getParameter("description"));
			model.setPrice(Double.parseDouble(request.getParameter("price")));
			model.setCategory(Long.parseLong(request.getParameter("category")));
			model.setImageUrl(StringUtil.isNoneBlank(request.getParameter("imageUrl")) ? request
					.getParameter("imageUrl") : PropertiesUtil.getString("default-meal-image"));
			model.setIsBreakfast(StringUtil.isNoneBlank(request.getParameter("breakfast")) ? Integer
					.parseInt(request.getParameter("breakfast")) : 1);
			model.setIsLunch(StringUtil.isNotBlank(request.getParameter("lunch")) ? Integer
					.parseInt(request.getParameter("lunch")) : 1);
			model.setIsDinner(StringUtil.isNotBlank(request.getParameter("dinner")) ? Integer
					.parseInt(request.getParameter("dinner")) : 1);
			model.setCreateTime(DateUtil.now());
			model.setUpdateTime(DateUtil.now());

			mealService.addMeal(model);
		} catch (Exception e) {
			logger.error("Failed to add meal.", e);
		}
		return modelView;
	}

	/**
	 * 更新餐品信息Get.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws SystemException
	 */
	@RequestMapping(value = "/{mealId}/update", method = RequestMethod.GET)
	public ModelAndView updateMealGet(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("mealId") Long mealId) throws SystemException {
		ModelAndView modelView = new ModelAndView();

		List<MealCategoryBean> categories = mealService.getMealCategories();
		modelView.addObject("categories", categories);

		ProductInfoModel meal = mealService.getMeal(mealId);
		modelView.addObject("meal", meal);

		modelView.setViewName("meal-update");
		return modelView;
	}

	/**
	 * 更新餐品信息Post.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{mealId}/update", method = RequestMethod.POST)
	public ModelAndView updateMealPost(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("mealId") Long mealId) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:/meal/meals");
		try {
			ProductInfoModel model = new ProductInfoModel();
			model.setId(Long.valueOf(request.getParameter("id")));
			model.setName(request.getParameter("name"));
			model.setDescription(request.getParameter("description"));
			model.setPrice(Double.parseDouble(request.getParameter("price")));
			model.setCategory(Long.parseLong(request.getParameter("category")));
			if (StringUtil.isNotBlank(request.getParameter("imageUrl"))) {
				model.setImageUrl(request.getParameter("imageUrl"));
			}
			model.setIsBreakfast(StringUtil.isNoneBlank(request.getParameter("breakfast")) ? Integer
					.parseInt(request.getParameter("breakfast")) : 1);
			model.setIsLunch(StringUtil.isNotBlank(request.getParameter("lunch")) ? Integer
					.parseInt(request.getParameter("lunch")) : 1);
			model.setIsDinner(StringUtil.isNotBlank(request.getParameter("dinner")) ? Integer
					.parseInt(request.getParameter("dinner")) : 1);
			model.setCreateTime(DateUtil.now());
			model.setUpdateTime(DateUtil.now());
			mealService.updateMeal(model);
		} catch (Exception e) {
			logger.error("Failed to add meal.", e);
		}
		return modelView;
	}

	/**
	 * 删除餐品信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{mealId}/delete", method = RequestMethod.GET)
	public ModelAndView deleteMeal(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("mealId") Long mealId) {
		ModelAndView modelView = new ModelAndView();
		mealService.deleteMeal(mealId);
		modelView.setViewName("redirect:/meal/meals");
		return modelView;
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST, params = "thumb")
	@ResponseBody
	public Map<String, Object> uploadNewsThumb(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "imgFile", required = false) MultipartFile file) {

		String checkedFileExt = "gif,jpg,jpeg,png,bmp,GIF,JPG,JPEG,PNG,BMP";

		String fileName = file.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

		if (!checkedFileExt.contains(fileExt)) {
			return getError("请上传以gif,jpg,jpeg,png,bmp等结尾的图片");
		}

		String dir = DateUtil.formatDate(new Date(), "yyyyMMdd");
		String newFileName = dir + File.separator
				+ DateUtil.formatDate(new Date(), "yyyyMMddHHmmss") + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File targetFile = new File(PropertiesUtil.getString("save.path.image"), newFileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		Map<String, Object> succMap = new HashMap<String, Object>();
		succMap.put("error", 0);
		try {
			file.transferTo(targetFile);
			String thumbFileName = ImageUtil.thumbnailImage(targetFile.getAbsolutePath(), 300, 300);
			targetFile.delete();
			newFileName = dir + File.separator + thumbFileName;
		} catch (Exception e) {
			logger.error("Failed to transfer file.", e);
			succMap.put("error", 0);
		}
		succMap.put("url", PropertiesUtil.getString("url.image") + newFileName);
		return succMap;
	}

	private Map<String, Object> getError(String errorMsg) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("error", 1);
		errorMap.put("message", errorMsg);
		return errorMap;
	}

}
