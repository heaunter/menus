package com.vota.reservation.modules.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.PropertiesUtil;
import com.vota.reservation.modules.cms.dao.ProductCategoryDao;
import com.vota.reservation.modules.cms.dao.ProductInfoDao;
import com.vota.reservation.modules.cms.entity.bean.MealBean;
import com.vota.reservation.modules.cms.entity.bean.MealCategoryBean;
import com.vota.reservation.modules.cms.entity.dto.MealReportDto;
import com.vota.reservation.modules.cms.entity.model.ProductCategoryModel;
import com.vota.reservation.modules.cms.entity.model.ProductInfoModel;

/**
 * 餐品Service Created by mengzhg on 2017/3/22.
 */
@Service
public class MealService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductInfoDao productInfoDao;

	/**
	 * 获取餐品种类信息
	 * 
	 * @throws SystemException
	 */
	public List<MealCategoryBean> getMealCategories() throws SystemException {
		List<ProductCategoryModel> models = productCategoryDao.getAllMealCategories();
		if (models == null) {
			return null;
		}
		List<MealCategoryBean> result = new ArrayList<MealCategoryBean>();
		for (ProductCategoryModel productCategoryModel : models) {
			MealCategoryBean bean = new MealCategoryBean();
			bean.from(productCategoryModel);
			result.add(bean);
		}
		return result;
	}

	/**
	 * 获取餐品信息
	 * 
	 * @return
	 * @throws SystemException
	 */
	public List<MealBean> getMeals(int cursor, int size) throws SystemException {
		List<ProductInfoModel> models = productInfoDao.getAllMeals(cursor * size, size);
		if (models == null) {
			return null;
		}
		List<MealBean> result = new ArrayList<MealBean>();
		for (ProductInfoModel productInfoModel : models) {
			MealBean bean = new MealBean();
			bean.from(productInfoModel);
			bean.setImage(PropertiesUtil.getString("api.image.url.prefix") + productInfoModel.getImageUrl());
			if (productInfoModel.getImageUrl().startsWith("/resources")) {
				bean.setImage(PropertiesUtil.getString("api.image.url.prefix") + "order-meal/"
						+ productInfoModel.getImageUrl());
			}
			result.add(bean);
		}
		return result;
	}

	public Long getMealCount() throws SystemException {
		return productInfoDao.getMealCount();
	}

	/**
	 * 新增餐品种类
	 * 
	 * @param categoryBean
	 * @return
	 */
	public Long addCategory(MealCategoryBean categoryBean) throws SystemException {
		ProductCategoryModel model = new ProductCategoryModel();
		model.setName(categoryBean.getName());
		model.setSort(categoryBean.getSort());
		productCategoryDao.insertCaregory(model);
		return model.getId();
	}

	/**
	 * 删除餐品种类
	 * 
	 * @param categoryId
	 */
	public void deleteCategory(Long categoryId) {
		productCategoryDao.delete(categoryId);
	}

	/**
	 * 更新餐品种类
	 * 
	 * @param category
	 */
	public void updateCategory(MealCategoryBean category) {
		ProductCategoryModel model = new ProductCategoryModel();
		model.setId(category.getId());
		model.setName(category.getName());
		model.setSort(category.getSort());
		model.setUpdateTime(DateUtil.now());
		productCategoryDao.update(model);
	}

	/**
	 * 查询餐品信息.
	 * 
	 * @param categoryId
	 *            the category id
	 * @return the list
	 */
	public List<MealBean> queryMeals(String categoryId, int cursor, int size) {
		List<ProductInfoModel> models = productInfoDao.getMeals(categoryId, cursor, size);
		if (models == null) {
			return null;
		}
		List<MealBean> result = new ArrayList<MealBean>();
		for (ProductInfoModel productInfoModel : models) {
			MealBean bean = new MealBean();
			bean.from(productInfoModel);
			result.add(bean);
		}
		return result;
	}

	/**
	 * 查询单个餐品种类.
	 * 
	 * @param categoryId
	 *            the category id
	 * @return the meal category
	 */
	public MealCategoryBean getMealCategory(Long categoryId) {
		ProductCategoryModel model = productCategoryDao.getCategory(categoryId);
		MealCategoryBean bean = new MealCategoryBean();
		bean.setId(model.getId());
		bean.setName(model.getName());
		bean.setSort(model.getSort());
		bean.setUpdateTime(model.getUpdateTime());
		return bean;
	}

	/**
	 * 删除餐品
	 * 
	 * @param mealId
	 */
	public void deleteMeal(Long mealId) {
		productInfoDao.delete(mealId);
	}

	/**
	 * 新增餐品
	 * 
	 * @param model
	 */
	public void addMeal(ProductInfoModel model) {
		productInfoDao.insert(model);
	}

	/**
	 * 获取餐品信息
	 * 
	 * @param mealId
	 * @return
	 */
	public ProductInfoModel getMeal(Long mealId) {
		return this.productInfoDao.getMeal(mealId);
	}

	public void updateMeal(ProductInfoModel model) {
		this.productInfoDao.update(model);

	}

	public boolean checkCatetory(String parameter) {
		ProductCategoryModel model = this.productCategoryDao.selectByName(parameter);
		if (model == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkMeal(String parameter) {
		ProductInfoModel model = this.productInfoDao.selectByName(parameter);
		if (model == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取产品报表.
	 * 
	 * @param mealId
	 *            the meal id
	 * @param date
	 *            the date
	 * @param period
	 *            the period
	 * @return the meal report
	 */
	public List<MealReportDto> getMealReport(String mealId, String date, String period) {
		return this.productInfoDao.getMealReport(mealId, date, period);
	}
}
