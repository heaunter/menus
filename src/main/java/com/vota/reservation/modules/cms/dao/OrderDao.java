package com.vota.reservation.modules.cms.dao;

import com.vota.reservation.modules.cms.entity.bean.DeliveryReportInnerBean;
import com.vota.reservation.modules.cms.entity.bean.OrderBean;
import com.vota.reservation.modules.cms.entity.bean.PrepareReportBean;
import com.vota.reservation.modules.cms.entity.model.OrderItemModel;
import com.vota.reservation.modules.cms.entity.model.OrderModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * The interface Order dao.
 * 
 * @author mengzhg @126.com
 * @version 1.0
 * @since 2017 /03/23 21:35
 */
public interface OrderDao {

	/**
	 * 保存订单信息
	 * 
	 * @param order
	 *            the order
	 */
	void saveOrder(OrderModel order);

	/**
	 * 保存订单item
	 * 
	 * @param items
	 *            the items
	 * @param id
	 *            the id
	 */
	void saveOrderItems(@Param("items") List<OrderItemModel> items, @Param("orderId") Long id);

	/**
	 * 查询订单.
	 * 
	 * @param patientNo
	 *            the patient no
	 * @param cursor
	 *            the cursor
	 * @param size
	 *            the size
	 * @return the list
	 */
	List<OrderBean> queryOrders(@Param("patientNo") String patientNo, @Param("cursor") int cursor,
			@Param("size") int size);

	/**
	 * 查询所有订单.
	 * 
	 * @param patientNo
	 *            the patient no
	 * @param cursor
	 *            the cursor
	 * @param size
	 *            the size
	 * @return the list
	 */
	List<OrderBean> queryAllOrders(@Param("date") Date date,
			@Param("areaNos") List<String> areaNos, @Param("period") String period,
			@Param("status") int status, @Param("cursor") int cursor, @Param("size") int size);

	/**
	 * 查询配送报表
	 * 
	 * @param date
	 * @param cursor
	 * @param size
	 * @return
	 */
	List<DeliveryReportInnerBean> getDeliveryReport(@Param("date") Date date,
			@Param("period") String period, @Param("cursor") int cursor, @Param("size") int size);

	/**
	 * 获取配料报表
	 * 
	 * @param date
	 * @param cursor
	 * @param size
	 * @return
	 */
	List<PrepareReportBean> getPrepareMaterialReport(@Param("date") Date date,
			@Param("period") String period, @Param("cursor") int cursor, @Param("size") int size);

	List<PrepareReportBean> getAllPrepareMaterialReport(@Param("date") Date date,
			@Param("cursor") int cursor, @Param("size") int size);

	/**
	 * 获取订单信息
	 * 
	 * @param orderNo
	 * @return
	 */
	OrderModel getOrder(@Param("orderNo") String orderNo);

	/**
	 * 更新订单信息
	 * 
	 * @param orderNo
	 * @param status
	 *            状态 0=未支付；1=已经支付；2=已经退款；9=异常订单
	 * @return
	 */
	int updateOrderStatus(@Param("orderNo") String orderNo, @Param("status") int status);

	Long queryOrderCount(@Param("userId") Long userId);

	/**
	 * Gets the summary prepare material report.
	 * 
	 * @param date
	 *            the date
	 * @param list
	 *            the list
	 * @return the summary prepare material report
	 */
	List<PrepareReportBean> getSummaryPrepareMaterialReport(@Param("date") Date date,
			@Param("period") String period, @Param("list") List<String> list);

	List<PrepareReportBean> getAllSummaryPrepareMaterialReport(@Param("date") Date date,
			@Param("list") List<String> list);

}