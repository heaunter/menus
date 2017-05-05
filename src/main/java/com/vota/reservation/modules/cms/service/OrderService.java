package com.vota.reservation.modules.cms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vota.reservation.common.exception.SystemException;
import com.vota.reservation.common.util.DateUtil;
import com.vota.reservation.common.util.PropertiesUtil;
import com.vota.reservation.common.util.StringUtil;
import com.vota.reservation.modules.cms.dao.OrderDao;
import com.vota.reservation.modules.cms.dao.PatientInfoDao;
import com.vota.reservation.modules.cms.dao.SickDeptInfoDao;
import com.vota.reservation.modules.cms.entity.bean.DeliveryReportBean;
import com.vota.reservation.modules.cms.entity.bean.DeliveryReportInnerBean;
import com.vota.reservation.modules.cms.entity.bean.OrderBean;
import com.vota.reservation.modules.cms.entity.bean.PrepareReportBean;
import com.vota.reservation.modules.cms.entity.model.OrderItemModel;
import com.vota.reservation.modules.cms.entity.model.OrderModel;
import com.vota.reservation.modules.cms.entity.model.PatientInfoModel;

/**
 * 订单服务 Created by mengzhg on 2017/3/29.
 */
@Service
public class OrderService {

	private Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private PatientInfoDao patientInfoDao;
	@Autowired
	private HisService hisService;
	@SuppressWarnings("unused")
	@Autowired
	private SickDeptInfoDao deptDao;
	@Autowired
	private PatientInfoDao patientDao;

	/**
	 * 提交订单
	 * 
	 * @param order
	 *            the order
	 * @param items
	 *            the items
	 * @return string
	 * @throws SystemException
	 *             the system exception
	 */
	@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
	public long submitOrder(OrderModel order, List<OrderItemModel> items) throws SystemException {
		OrderModel tmpOrder = orderDao.getOrder(order.getOrderNo());
		if (tmpOrder != null) {
			return tmpOrder.getId();
		}
		orderDao.saveOrder(order);
		orderDao.saveOrderItems(items, order.getId());
		return order.getId();
	}

	/**
	 * 查询订单信息<br>
	 * 如果patientNo为空，则查询所有的
	 * 
	 * @param patientNo
	 *            病人住院号
	 * @param cursor
	 *            页码
	 * @param size
	 *            分页大小
	 * @return string list
	 * @throws SystemException
	 *             the system exception
	 */
	public List<OrderBean> queryOrders(String patientNo, int cursor, int size) throws SystemException {
		List<OrderBean> orderBeans = orderDao.queryOrders(patientNo, cursor, size);
		return orderBeans;
	}

	/**
	 * 查询订单信息<br>
	 * 如果patientNo为空，则查询所有的.
	 * 
	 * @param date
	 *            消费日期
	 * @param areaNos
	 *            病区编号
	 * @param cursor
	 *            页码
	 * @param size
	 *            分页大小
	 * @return string list
	 * @throws SystemException
	 *             the system exception
	 */
	public List<OrderBean> queryAllOrders(Date date, List<String> areaNos, String period, int status, int cursor,
			int size) throws SystemException {
		List<OrderBean> orderBeans = orderDao.queryAllOrders(date, areaNos, period, status, cursor, size);
		return orderBeans;
	}

	/**
	 * 订单支付.0 未上送； 1 已支付； 2 申请审核； 3 申请退款； 4 退款成功； 5 退款失败； 6 不处理； 7 支付失败；
	 * 
	 * @param orderNo
	 *            the order no
	 * @throws SystemException
	 *             the system exception
	 */
	public void payOrder(String orderNo) throws SystemException {
		synchronized (orderNo) {
			OrderModel order = orderDao.getOrder(orderNo);
			if (order == null) {
				throw new SystemException("订单不存在");
			}
			if (order.getStatus().intValue() == 1) { // 已经支付，不再进行重复支付
				return;
			}
			// 只有订单未支付和支付失败的，才能进行支付操作
			if (order.getStatus().intValue() != 0 && order.getStatus() != 7) {
				throw new SystemException("订单状态有误");
			}

			PatientInfoModel patientInfo = patientInfoDao.getPatientInfoById(order.getUserId());
			if (patientInfo == null) {
				throw new SystemException("病人信息不存在");
			}
			boolean isSuccess = false;
			if (PropertiesUtil.getBoolean("test.env.flag", false)) {
				logger.info("======Pay success[Test]: orderNo=" + orderNo);
				isSuccess = true;
			} else {
				isSuccess = hisService.pay(patientInfo.getPatientNo(), orderNo, order.getPrice());
			}
			if (isSuccess) {
				orderDao.updateOrderStatus(orderNo, 1);
			} else {
				throw new SystemException("支付失败");
			}
		}
	}

	/**
	 * 订单退款. 0 未上送； 1 已支付； 2 申请审核； 3 申请退款； 4 退款成功； 5 退款失败； 6 不处理； 7 支付失败；
	 * 
	 * @param orderNo
	 *            the order no
	 * @throws SystemException
	 *             the system exception
	 */
	public void refundOrder(String orderNo) throws SystemException {

		synchronized (orderNo) {
			OrderModel order = orderDao.getOrder(orderNo);
			if (order == null || order.getStatus().intValue() != 1) {
				throw new SystemException("订单有误，订单号不存在");
			}
			String orderDate = DateUtil.formatDate(order.getCreateTime(), DateUtil.PATTERN_YYYY_MM_DD);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			String yesterdayDate = DateUtil.formatDate(calendar.getTime(), DateUtil.PATTERN_YYYY_MM_DD);
			if (DateUtil.parseDate(orderDate, DateUtil.PATTERN_YYYY_MM_DD).before(
					DateUtil.parseDate(yesterdayDate, DateUtil.PATTERN_YYYY_MM_DD))) {
				throw new SystemException("订单已处理，无法退款");
			}

			PatientInfoModel patientInfo = patientInfoDao.getPatientInfoById(order.getUserId());
			if (patientInfo == null) {
				throw new SystemException("病人信息不存在");
			}
			double money = -1 * order.getPrice();
			boolean isSuccess = false;
			if (PropertiesUtil.getBoolean("test.env.flag", false)) {
				isSuccess = true;
			} else {
				isSuccess = hisService.pay(patientInfo.getPatientNo(), orderNo, money);
			}
			if (isSuccess) {
				orderDao.updateOrderStatus(orderNo, 4);
			} else {
				throw new SystemException("退款失败");
			}

		}
	}

	/**
	 * 配送人员报表.
	 * 
	 * @param date
	 *            the date
	 * @param period
	 *            the period
	 * @param list
	 *            病区编号集合
	 * @param cursor
	 *            the cursor
	 * @param size
	 *            the size
	 * @return the delivery report
	 */
	public List<DeliveryReportBean> getDeliveryReport(Date date, String period, List<String> list, int cursor, int size) {
		List<DeliveryReportInnerBean> beans = this.orderDao.getDeliveryReport(date, period, cursor, size);
		if (list != null && list.size() > 0) {
			List<PatientInfoModel> models = patientDao.getPatientInfoByAreas(list);
			if (models == null || models.size() <= 0) {
				return new ArrayList<DeliveryReportBean>();
			}
			HashMap<String, String> keys = new HashMap<String, String>();
			for (PatientInfoModel model : models) {
				keys.put(model.getPatientNo(), model.getPatientNo());
			}

			Iterator<DeliveryReportInnerBean> iterator = beans.iterator();
			while (iterator.hasNext()) {
				DeliveryReportInnerBean bean = iterator.next();
				if (!keys.containsKey(bean.getCardNo())) {
					iterator.remove();
				}
			}
		}
		List<DeliveryReportInnerBean> ret = new ArrayList<DeliveryReportInnerBean>();

		for (int i = 0; i < beans.size(); i++) {
			DeliveryReportInnerBean deliveryReportInnerBean = beans.get(i);
			if (ret.size() <= 0) {
				ret.add(deliveryReportInnerBean);
				continue;
			}
			DeliveryReportInnerBean oldBean = ret.get(ret.size() - 1);
			if (deliveryReportInnerBean.getProductId().toString().equals(oldBean.getProductId().toString())
					&& deliveryReportInnerBean.getCardNo().equals(oldBean.getCardNo())) {
				oldBean.setCount(oldBean.getCount() + deliveryReportInnerBean.getCount());
			} else {
				ret.add(deliveryReportInnerBean);
			}
		}
		List<DeliveryReportBean> rett = new ArrayList<DeliveryReportBean>();
		for (DeliveryReportInnerBean bean : ret) {
			DeliveryReportBean bb = new DeliveryReportBean();
			bb.from(bean);
			rett.add(bb);
		}
		return rett;
	}

	/**
	 * 获取备料人员报表
	 * 
	 * @param date
	 * @param cursor
	 * @param size
	 * @return
	 */
	public List<PrepareReportBean> getPrepareMaterialReport(Date date, String period, int cursor, int size) {
		List<PrepareReportBean> ret = new LinkedList<PrepareReportBean>();

		if (StringUtil.isBlank(period)) {
			List<PrepareReportBean> all = this.orderDao.getAllPrepareMaterialReport(date, cursor, size);
			ret.addAll(all);
		} else if (period.equals("breakfast")) {
			List<PrepareReportBean> breakfast = this.orderDao.getPrepareMaterialReport(date, "breakfast", cursor, size);
			ret.addAll(breakfast);
		} else if (period.equals("lunch")) {
			List<PrepareReportBean> lunch = this.orderDao.getPrepareMaterialReport(date, "lunch", cursor, size);
			ret.addAll(lunch);
		} else if (period.equals("dinner")) {
			List<PrepareReportBean> dinner = this.orderDao.getPrepareMaterialReport(date, "dinner", cursor, size);
			ret.addAll(dinner);
		}

		return ret;
	}

	/**
	 * 计算订单金额
	 * 
	 * @param pageSize
	 * @param i
	 * @param list
	 * @param period
	 * @param date
	 * @return
	 */
	public Double calTotalMoney(Date date, String period, List<String> list, int index, int pageSize) {
		return new Double(12D);
	}

	public Long queryOrderCount(String patientNo) {
		Long userId = 0L;
		PatientInfoModel patientInfo = patientInfoDao.getPatientInfoByNo(patientNo);
		if (patientInfo != null) {
			userId = patientInfo.getId();
		}
		return this.orderDao.queryOrderCount(userId);
	}

	/**
	 * 获取配送人员的汇总报表.
	 * 
	 * @param parseDate
	 *            the parse date
	 * @param list
	 *            the list
	 * @return the summary prepare material report
	 */
	public List<PrepareReportBean> getSummaryPrepareMaterialReport(Date date, String period, List<String> list) {
		if (StringUtil.isBlank(period)) {
			List<PrepareReportBean> allList = this.orderDao.getAllSummaryPrepareMaterialReport(date, list);
			return allList;
		} else {
			List<PrepareReportBean> periodList = this.orderDao.getSummaryPrepareMaterialReport(date, period, list);
			return periodList;
		}
	}
}
