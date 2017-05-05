package com.vota.reservation.modules.cms.dao;

import com.vota.reservation.modules.cms.entity.dto.MealReportDto;
import com.vota.reservation.modules.cms.entity.model.ProductInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 餐品信息Dao
 *
 * @author mengzhg@126.com
 * @version 1.0
 * @since 2017/03/23 21:35
 */
public interface ProductInfoDao {

    /**
     * 获取所有的餐品
     *
     * @param size
     * @param cursor
     * @return
     */
    List<ProductInfoModel> getAllMeals(@Param("cursor") int cursor, @Param("size") int size);

    /**
     * 获取餐品信息
     *
     * @param categoryId
     * @param cursor
     * @param size
     * @return
     */
    List<ProductInfoModel> getMeals(@Param("categoryId") String categoryId, @Param("cursor") int cursor,
                                    @Param("size") int size);

    /**
     * 删除餐品
     *
     * @param mealId
     */
    void delete(@Param("id") Long mealId);

    /**
     * 新增餐品
     *
     * @param model
     */
    void insert(ProductInfoModel model);

    /**
     * 获取餐品信息
     *
     * @param mealId
     * @return
     */
    ProductInfoModel getMeal(@Param("id") Long mealId);

    Long getMealCount();

    void update(ProductInfoModel model);

    ProductInfoModel selectByName(@Param("name") String parameter);

    /**
     * 获取产品报表.
     *
     * @param mealId the meal id
     * @param date   the date
     * @param period the period
     * @return the meal report
     */
    List<MealReportDto> getMealReport(@Param("mealId") String mealId, @Param("date") String date, @Param("period") String period);
}