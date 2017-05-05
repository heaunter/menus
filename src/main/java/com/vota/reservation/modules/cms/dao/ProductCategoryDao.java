package com.vota.reservation.modules.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vota.reservation.modules.cms.entity.model.ProductCategoryModel;

/**
 * 餐品种类Dao
 * 
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
public interface ProductCategoryDao {

	/**
	 * 获取所有的餐品种类
	 * 
	 * @return
	 */
	List<ProductCategoryModel> getAllMealCategories();

	/**
	 * 删除种类.
	 * 
	 * @param categoryId
	 *            the category id
	 */
	void delete(Long categoryId);

	/**
	 * 新增种类.
	 * 
	 * @param model
	 *            the category bean
	 */
	void insertCaregory(ProductCategoryModel model);

	/**
	 * 查询单个餐品种类
	 * 
	 * @param categoryId
	 * @return
	 */
	ProductCategoryModel getCategory(Long categoryId);

	/**
	 * 更新餐品种类
	 * 
	 * @param model
	 */
	void update(ProductCategoryModel model);

	/**
	 * Select by name.
	 *
	 * @param parameter the parameter
	 * @return the product category model
	 */
	ProductCategoryModel selectByName(@Param("name") String parameter);
}