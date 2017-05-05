<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>备餐报表 - 点餐管理系统</title>
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
							<li><a href="${ctx}/report/prepare" class="active"> <i class="am-icon-angle-right"></i> <span>餐厅备餐报表</span>
							</a> <a href="${ctx}/report/delivery/summary"> <i class="am-icon-angle-right"></i> <span>配送汇总报表</span> </a><a
								href="${ctx}/report/delivery"> <i class="am-icon-angle-right"></i> <span>配送详情报表</span> </a><a
								href="${ctx}/order/orders"> <i class="am-icon-angle-right"></i> <span>订单报表</span> </a> <a
								href="${ctx}/report/meal"> <i class="am-icon-angle-right"></i> <span>单品统计报表</span> </a>
							</li>
						</ul></li>

					<li class="tpl-left-nav-item"><a href="javascript:;" class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-th-list"></i> <span>种类管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i> </a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/categories"> <i class="am-icon-angle-right"></i> <span>种类查询</span> </a> <a
								href="${ctx}/meal/category/add"> <i class="am-icon-angle-right"></i> <span>新增种类</span> </a></li>
						</ul></li>

					<li class="tpl-left-nav-item"><a href="javascript:;" class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-cart-plus"></i> <span>菜品管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i> </a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/meals"> <i class="am-icon-angle-right"></i> <span>菜品查询</span> </a> <a
								href="${ctx}/meal/add"> <i class="am-icon-angle-right"></i> <span>新增菜品</span> </a></li>
						</ul></li>
				</ul>
			</div>
		</div>

		<div class="tpl-content-wrapper">
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span> 备餐报表
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g" style="height: 50px;">
						<form action="${ctx}/report/prepare" method="get">
							<div class="am-u-sm-12 am-u-md-3">
								<div class="am-form-group">
									<input type="text" name="date" value="${date}" class="am-form-field tpl-form-no-bg" placeholder="请选择时间……"
										data-am-datepicker="" readonly />
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<div class="am-form-group">
									<select data-am-selected name="period">
										<c:if test="${period == 'all' or period == '' }">
											<option value="all" selected="selected">全部</option>
											<option value="breakfast">早餐</option>
											<option value="lunch">午餐</option>
											<option value="dinner">晚餐</option>
										</c:if>
										<c:if test="${period == 'breakfast' }">
											<option value="all">全部</option>
											<option value="breakfast" selected="selected">早餐</option>
											<option value="lunch">午餐</option>
											<option value="dinner">晚餐</option>
										</c:if>
										<c:if test="${period == 'lunch' }">
											<option value="all">全部</option>
											<option value="breakfast">早餐</option>
											<option value="lunch" selected="selected">午餐</option>
											<option value="dinner">晚餐</option>
										</c:if>
										<c:if test="${period == 'dinner' }">
											<option value="all">全部</option>
											<option value="breakfast">早餐</option>
											<option value="lunch">午餐</option>
											<option value="dinner" selected="selected">晚餐</option>
										</c:if>
									</select>
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-3">
								<div class="am-input-group am-input-group-sm">
									<span class="am-input-group-btn" style="width: auto;">
										<button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search" type="submit"></button>
									</span>&nbsp;&nbsp;&nbsp;&nbsp;
									<span class="am-input-group-btn" style="width: auto;"> <a
										href="${ctx}/report/prepare/print?date=${date}&period=${period}" target="_blank"
										class="am-btn am-btn-default am-btn-success tpl-am-btn-success am-icon-print" type="button"> 打印</a> </span>
								</div>
							</div>
						</form>
					</div>
					<div class="am-g">
						<div class="am-u-sm-12">
							<form class="am-form">
								<table class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>
											<th class="table-title">餐次</th>
											<th class="table-date">菜品</th>
											<th class="table-date">数量</th>
											<th class="table-date">金额</th>
											<th class="table-date am-hide-sm-only">就餐日期</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${prepareReport }" var="re">
											<tr>
												<td class="table-title"><a href="#"> <c:choose>
															<c:when test="${re.period =='breakfast'}">
														早餐
														</c:when>
															<c:when test="${re.period =='lunch' }">
														午餐
														</c:when>
															<c:when test="${re.period =='dinner' }">
														晚餐
														</c:when>
															<c:otherwise>
															全天
														</c:otherwise>
														</c:choose> </a>
												</td>
												<td class="table-date">${re.name }</td>
												<td class="table-date">${re.amount }</td>
												<td class="table-date">${re.price }</td>
												<td class="am-hide-sm-only"><fmt:formatDate pattern="yyyy-MM-dd" value="${re.datetime }" />
												</td>
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