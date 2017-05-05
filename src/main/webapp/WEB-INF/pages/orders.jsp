<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>订单列表 - 点餐管理系统</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="${ctx}/resources/amaze-ui-2.7.2/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${ctx}/resources/amaze-ui-2.7.2/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet"
	href="${ctx}/resources/amaze-ui-2.7.2/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${ctx}/resources/amaze-ui-2.7.2/css/admin.css">
<link rel="stylesheet"
	href="${ctx}/resources/amaze-ui-2.7.2/css/app.css">
<link rel="stylesheet" href="${ctx}/resources/css/meal.css">
<link rel="stylesheet"
	href="${ctx}/resources/jquery/jquery.dialogbox.css">
</head>

<body data-type="generalComponents">


	<jsp:include page="/common/header.jsp" flush="false"></jsp:include>

	<div class="tpl-page-container tpl-page-header-fixed">
		<div class="tpl-left-nav tpl-left-nav-hover">
			<div class="tpl-left-nav-title">功能列表</div>
			<div class="tpl-left-nav-list">
				<ul class="tpl-left-nav-menu">
					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-bar-chart"></i> <span>报表</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
					</a>
						<ul class="tpl-left-nav-sub-menu" style="display: block;">
							<li><a href="${ctx}/report/prepare"> <i
									class="am-icon-angle-right"></i> <span>餐厅备餐报表</span> </a> <a
								href="${ctx}/report/delivery/summary"> <i
									class="am-icon-angle-right"></i> <span>配送汇总报表</span> </a><a
								href="${ctx}/report/delivery"> <i
									class="am-icon-angle-right"></i> <span>配送详情报表</span> </a><a
								href="${ctx}/order/orders" class="active"> <i
									class="am-icon-angle-right"></i> <span>订单报表</span> </a> <a 
								href="${ctx}/report/meal"> <i
									class="am-icon-angle-right"></i> <span>单品统计报表</span> </a>
							</li>
						</ul></li>

					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-th-list"></i> <span>种类管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right "></i>
					</a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/categories"> <i
									class="am-icon-angle-right"></i> <span>种类查询</span> </a> <a
								href="${ctx}/meal/category/add"> <i
									class="am-icon-angle-right"></i> <span>新增种类</span> </a></li>
						</ul></li>

					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list active"> <i
							class="am-icon-cart-plus"></i> <span>菜品管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
					</a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/meals"> <i
									class="am-icon-angle-right"></i> <span>菜品查询</span> </a> <a
								href="${ctx}/meal/add"> <i class="am-icon-angle-right"></i>
									<span>新增菜品</span> </a></li>
						</ul></li>
				</ul>
			</div>
		</div>

		<div class="tpl-content-wrapper">
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-list-ul"></span> 订单列表
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g" style="height: 50px;">
						<form action="${ctx}/order/orders" method="get">
							<div class="am-u-sm-12 am-u-md-2">
								<div class="am-form-group">
									<input type="text" name="date" required="required"
										value="${date}" class="am-form-field tpl-form-no-bg"
										placeholder="请选择时间……" data-am-datepicker="" readonly />
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<select name="areaNo" multiple
									data-am-selected="{searchBox:1,maxHeight: 500}">
									<c:forEach items="${areas }" var="area">
										<option value="${area.areaNo }"
											<c:forEach items="${checkedAreaNos }" var="no"> 
												<c:if test="${area.areaNo == no }">
													selected="selected"
												</c:if>
											</c:forEach>>${area.areaName
											}</option>
									</c:forEach>
								</select>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<select name="status" data-am-selected>
									<c:choose>
										<c:when test="${status == 1 }">
											<option value="1" selected="selected">已支付</option>
											<option value="4">已退款</option>
											<option value="0">未支付</option>
											<option value="7">支付失败</option>
										</c:when>
										<c:when test="${status == 4 }">
											<option value="1">已支付</option>
											<option value="4" selected="selected">已退款</option>
											<option value="0">未支付</option>
											<option value="7">支付失败</option>
										</c:when>
										<c:when test="${status == 0 }">
											<option value="1">已支付</option>
											<option value="4">已退款</option>
											<option value="0" selected="selected">未支付</option>
											<option value="7">支付失败</option>
										</c:when>
										<c:when test="${status == 7 }">
											<option value="1">已支付</option>
											<option value="4">已退款</option>
											<option value="0">未支付</option>
											<option value="7" selected="selected">支付失败</option>
										</c:when>
										<c:otherwise>
											<option value="1" selected="selected">已支付</option>
											<option value="4">已退款</option>
											<option value="0">未支付</option>
											<option value="7">支付失败</option>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<div class="am-form-group">
									<select data-am-selected name="period">
										<c:choose>
											<c:when test="${period == 'breakfast' }">
												<option value=" ">所有时段</option>
												<option value="breakfast" selected="selected">早餐</option>
												<option value="lunch">午餐</option>
												<option value="dinner">晚餐</option>
											</c:when>
											<c:when test="${period == 'lunch' }">
												<option value=" ">所有时段</option>
												<option value="breakfast">早餐</option>
												<option value="lunch" selected="selected">午餐</option>
												<option value="dinner">晚餐</option>
											</c:when>
											<c:when test="${period == 'dinner' }">
												<option value=" ">所有时段</option>
												<option value="breakfast">早餐</option>
												<option value="lunch">午餐</option>
												<option value="dinner" selected="selected">晚餐</option>
											</c:when>
											<c:otherwise>
												<option value=" " selected="selected">所有时段</option>
												<option value="breakfast">早餐</option>
												<option value="lunch">午餐</option>
												<option value="dinner">晚餐</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-1">
								<div class="am-input-group am-input-group-sm">
									<span class="am-input-group-btn">
										<button
											class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search"
											type="submit"></button> </span>
								</div>
							</div>
						</form>
					</div>
					<div class="am-g">
						<div class="am-u-sm-12">
							<table
								class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>
										<th class="table-id" style="width: 10%">住院号</th>
										<th class="table-title" style="width: 10%">病区</th>
										<th class="table-type" style="width: 10%">订单号</th>
										<th class="table-type" style="width: 8%">状态</th>
										<th class="table-type" style="width: 5%">金额</th>
										<th class="table-type" style="width: 8%">创建时间</th>
										<th class="table-type" style="width: 5%">时段</th>
										<th class="table-set" style="width: 20%">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${orders }" var="o" varStatus="status">
										<tr id="ON-${o.orderNo}">
											<td>${o.patientNo}</td>
											<td>${o.areaName }</td>
											<td>${o.orderNo }</td>
											<td><c:choose>
													<c:when test="${o.status==0 }">未支付</c:when>
													<c:when test="${o.status==1 }">已支付</c:when>
													<c:when test="${o.status==2 }">申请审核</c:when>
													<c:when test="${o.status==3}">申请退款</c:when>
													<c:when test="${o.status==4}">退款成功</c:when>
													<c:when test="${o.status==5}">退款失败</c:when>
													<c:when test="${o.status==6}">不处理</c:when>
													<c:when test="${o.status==7}">支付失败</c:when>
												</c:choose></td>
											<td>￥${o.price }</td>
											<td><fmt:formatDate value="${o.updateTime }"
													pattern="HH:mm:ss"></fmt:formatDate></td>
											<td><c:choose>
													<c:when test="${o.period =='breakfast' }">
															早餐
														</c:when>
													<c:when test="${o.period =='lunch' }">
															午餐
														</c:when>
													<c:when test="${o.period =='dinner'}">
															晚餐
														</c:when>
												</c:choose></td>
											<td>
												<div class="am-btn-toolbar">
													<div class="am-btn-group am-btn-group-xs">
														<%-- <button id="btnDetail-${o.orderNo }"
																onclick="btnDetailClick(${o.orderNo});"
																class="am-btn am-btn-default am-btn-xs am-text-secondary">
																<span class="am-icon-pencil-square-o"></span> 详情
															</button> --%>
														<c:if test="${o.status == 1 }">
															<button id="btnRefund-${o.orderNo }"
																onclick="btnRefundClick('${o.orderNo}');"
																class="am-btn am-btn-default am-btn-xs am-text-secondary">
																<span class="am-icon-credit-card"></span> 退款
															</button>
														</c:if>
														<c:if test="${o.status == 2 || o.status == 3 || o.status == 4 || o.status == 5|| o.status == 6  }">
															<a class="am-badge am-badge-primary am-radius">无操作</a>
														</c:if>
														<c:if test="${o.status == 0 || o.status == 7  }">
															<a class="am-badge am-badge-warning am-radius">请使用PAD重新支付</a>
														</c:if>
													</div>
												</div></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="tpl-alert"></div>
			</div>
		</div>
		<div id="auto-close-dialogBox"></div>
	</div>


	<script src="${ctx}/resources/amaze-ui-2.7.2/js/jquery.min.js"></script>
	<script src="${ctx}/resources/jquery/jquery.dialogBox.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/amazeui.min.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/app.js"></script>
</body>
<script type="text/javascript">
	function categoryChange(obj) {
		var categoryId = $(obj).val();
		var url = "${ctx}/meal/meals?categoryId=" + categoryId;
		window.location.href = url;
	}

	function btnDetailClick(orderNo) {
		alert(orderNo);
		return;
	}

	function btnRefundClick(orderNo) {
		var url = "${ctx}/order/" + orderNo + "/refund";
		console.log(orderNo);
		console.log(url);
		$.get(url, function(data) {
			console.log(data);
			if (data.code == 1) {
				$("#ON-" + orderNo).detach();
				$('#auto-close-dialogBox').dialogBox({
					autoHide : true,
					time : 1000,
					content : "退款成功"
				});
			} else {
				$('#auto-close-dialogBox').dialogBox({
					autoHide : true,
					time : 1000,
					content : data.message
				});
			}
		});
	}
</script>

</html>