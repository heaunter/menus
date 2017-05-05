<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>配送报表 - 点餐管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="${ctx}/resources/amaze-ui-2.7.2/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="${ctx}/resources/amaze-ui-2.7.2/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="${ctx}/resources/amaze-ui-2.7.2/css/amazeui.min.css" />
<link rel="stylesheet" href="${ctx}/resources/amaze-ui-2.7.2/css/admin.css">
<link rel="stylesheet" href="${ctx}/resources/amaze-ui-2.7.2/css/app.css">
<link rel="stylesheet" href="${ctx}/resources/css/meal.css">
</head>

<body data-type="generalComponents">


	<jsp:include page="/common/header.jsp" flush="false"></jsp:include>

	<div class="tpl-page-container tpl-page-header-fixed">
		<div class="tpl-left-nav tpl-left-nav-hover">
			<div class="tpl-left-nav-title">功能列表</div>
			<div class="tpl-left-nav-list">
				<ul class="tpl-left-nav-menu">
					<li class="tpl-left-nav-item"><a href="javascript:;" class="nav-link tpl-left-nav-link-list active"> <i
							class="am-icon-bar-chart"></i> <span>报表</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i> </a>
						<ul class="tpl-left-nav-sub-menu" style="display: block;">
							<li><a href="${ctx}/report/prepare"> <i class="am-icon-angle-right"></i> <span>餐厅备餐报表</span> </a> <a
								href="${ctx}/report/delivery/summary"> <i class="am-icon-angle-right"></i> <span>配送汇总报表</span> </a><a
								href="${ctx}/report/delivery" class="active"> <i class="am-icon-angle-right"></i> <span>配送详情报表</span> </a> <a
								href="${ctx}/order/orders"> <i class="am-icon-angle-right"></i> <span>订单报表</span> </a> <a
								href="${ctx}/report/meal"> <i class="am-icon-angle-right"></i> <span>单品统计报表</span> </a></li>
						</ul></li>

					<li class="tpl-left-nav-item"><a href="javascript:;" class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-th-list"></i> <span>种类管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i> </a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/categories"> <i class="am-icon-angle-right"></i> <span>种类查询</span> </a> <a
								href="${ctx}/meal/category/add"> <i class="am-icon-angle-right"></i> <span>新增种类</span> </a>
							</li>
						</ul>
					</li>

					<li class="tpl-left-nav-item"><a href="javascript:;" class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-cart-plus"></i> <span>菜品管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i> </a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/meals"> <i class="am-icon-angle-right"></i> <span>菜品查询</span> </a> <a
								href="${ctx}/meal/add"> <i class="am-icon-angle-right"></i> <span>新增菜品</span> </a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>

		<div class="tpl-content-wrapper">
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span> 配送报表
					</div>
				</div>
				<p style="font-size: 14px;margin-left: 15px; margin-bottom: 5px;">
					<span style="font-weight: bolder;">当前选择病区: </span>${areaNames}
				</p>
				<div class="tpl-block">
					<div class="am-g" style="height: 50px;">
						<form action="${ctx}/report/delivery" method="get">
							<div class="am-u-sm-12 am-u-md-2">
								<div class="am-form-group">
									<input type="text" name="date" value="${date}" class="am-form-field tpl-form-no-bg" placeholder="请选择时间……"
										data-am-datepicker="" readonly />
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<select name="areaNo" multiple data-am-selected="{searchBox:1,maxHeight: 500}">
									<c:forEach items="${areas }" var="area">
										<option value="${area.areaNo }"
											<c:forEach items="${checkedAreaNos }" var="no"> 
												<c:if test="${area.areaNo == no }">
													selected="selected"
												</c:if>
											</c:forEach>>${area.areaName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<div class="am-form-group">
									<select data-am-selected="{maxHeight: 100}" name="period">
										<c:choose>
											<c:when test="${period == 'breakfast' }">
												<option value="breakfast" selected="selected">早餐</option>
												<option value="lunch">午餐</option>
												<option value="dinner">晚餐</option>
											</c:when>
											<c:when test="${period == 'lunch' }">
												<option value="breakfast">早餐</option>
												<option value="lunch" selected="selected">午餐</option>
												<option value="dinner">晚餐</option>
											</c:when>
											<c:when test="${period == 'dinner' }">
												<option value="breakfast">早餐</option>
												<option value="lunch">午餐</option>
												<option value="dinner" selected="selected">晚餐</option>
											</c:when>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<div class="am-input-group am-input-group-sm">
									<span class="am-input-group-btn" style="width: auto;">
										<button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search" type="submit"></button>
									</span>&nbsp;&nbsp;&nbsp;&nbsp; <span class="am-input-group-btn" style="width: auto;"> <a
										href="${ctx}/report/delivery/print?date=${date}&period=${period}&areaNo=${areaNo}" target="_blank"
										class="am-btn am-btn-default am-btn-success tpl-am-btn-success am-icon-print" type="button"> 打印</a> </span>
								</div>
							</div>
						</form>
					</div>
					<%-- <div class="am-g" style="height: 50px;">
						<div class="am-u-sm-12 am-u-md-12" id="doc-dropdown-justify">
							<div class="am-dropdown"
								data-am-dropdown="{justify: '#doc-dropdown-justify'}">
								<button class="am-btn am-btn-success am-dropdown-toggle">
									请选择病区 <span class="am-icon-caret-down"></span>
								</button>
								<div class="am-dropdown-content">
									<ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-thumbnails">
										<c:forEach items="${areas }" var="area" varStatus="status">
											<label class="am-checkbox-inline" style="padding-top:0px;">
												<input type="checkbox" data-am-ucheck>${area.areaName
												}</label>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div> --%>
					<div class="am-g">
						<div class="am-u-sm-12">
							<form class="am-form">
								<table class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>
											<th class="table-title" style="width:10% ">卡号</th>
											<th class="table-date" style="width: 10%">姓名</th>
											<th class="table-date" style="width: 15%">病区</th>
											<th class="table-date" style="width: 10%">病床</th>
											<th class="table-date" style="width: 5%">价格</th>
											<th class="table-date am-hide-sm-only" style="width: 50%">订单情况</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${deliveryReport }" var="re">
											<tr>
												<td><a href="javascript:void(0);">${re.cardNo }</a></td>
												<td>${re.username }</td>
												<td class="am-hide-sm-only">${re.areaName }</td>
												<td class="am-hide-sm-only">${re.bedName }</td>
												<td class="am-hide-sm-only">${re.price }</td>
												<td>${re.mealDetail }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${ctx}/resources/amaze-ui-2.7.2/js/jquery.min.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/amazeui.min.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/app.js"></script>
</body>

</html>