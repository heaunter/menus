<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>单个餐品统计 - 点餐管理系统</title>
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
<style type="text/css">
.am-selected {
	width: 150px;
}
</style>
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
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i> </a>
						<ul class="tpl-left-nav-sub-menu" style="display: block;">
							<li><a href="${ctx}/report/prepare"> <i class="am-icon-angle-right"></i> <span>餐厅备餐报表</span> </a> <a
								href="${ctx}/report/delivery/summary"> <i class="am-icon-angle-right"></i> <span>配送汇总报表</span> </a><a
								href="${ctx}/report/delivery"> <i class="am-icon-angle-right"></i> <span>配送详情报表</span> </a><a
								href="${ctx}/order/orders"> <i class="am-icon-angle-right"></i> <span>订单报表</span> </a> <a
								href="${ctx}/report/meal" class="active"> <i class="am-icon-angle-right"></i> <span>单品统计报表</span> </a>
							</li>
						</ul>
					</li>

					<li class="tpl-left-nav-item"><a href="javascript:;" class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-th-list"></i> <span>种类管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right "></i> </a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/categories"> <i class="am-icon-angle-right"></i> <span>种类查询</span> </a> <a
								href="${ctx}/meal/category/add"> <i class="am-icon-angle-right"></i> <span>新增种类</span> </a>
							</li>
						</ul>
					</li>

					<li class="tpl-left-nav-item"><a href="javascript:;" class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-cart-plus"></i> <span>菜品管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i> </a>
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
						<span class="am-icon-list-ul"></span> 单个餐品统计
					</div>
				</div>
				<div class="tpl-block">
					<form action="${ctx}/report/meal" method="get">
						<div class="am-g" style="height: 50px;">
							<div class="am-u-sm-12 am-u-md-2">
								<div class="am-form-group">
									<input type="text" name="date" required="required" value="${date}" class="am-form-field tpl-form-no-bg"
										placeholder="请选择时间……" data-am-datepicker="" readonly />
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-2">
								<div class="am-form-group">
									<select data-am-selected="{searchBox: 1,maxHeight: 500}" name="categoryId" onchange="categoryChange(this);">
										<c:forEach items="${categories }" var="ca">
											<c:choose>
												<c:when test="${ca.id == categoryId }">
													<option value="${ca.id }" selected="selected">${ca.name}</option>
												</c:when>
												<c:otherwise>
													<option value="${ca.id }">${ca.name }</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-2">
								<div class="am-form-group">
									<select id="mealIdSelect" name="mealId" data-am-selected="{searchBox: 1,maxHeight: 500}">
										<option value=" " selected="selected">请选择餐品</option>
									</select>
								</div>
							</div>
							<div class="am-u-sm-12 am-u-md-2">
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
							<div class="am-u-sm-12 am-u-md-2">
								<div class="am-input-group am-input-group-sm">
									<span class="am-input-group-btn" style="width: auto;">
										<button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search" type="submit"></button>
									</span> &nbsp;&nbsp;&nbsp;&nbsp; <span class="am-input-group-btn" style="width: auto;"> <a
										href="${ctx}/report/meal/print?date=${date}&period=${period}&mealId=${mealId}&categoryId=${categoryId}"
										target="_blank" class="am-btn am-btn-default am-btn-success tpl-am-btn-success am-icon-print" type="button">
											打印</a> </span>
								</div>
							</div>
						</div>
					</form>
					<div class="am-g">
						<div class="am-u-sm-12">
							<form class="am-form">
								<table class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>
											<th class="table-type" style="width: 10%">病区</th>
											<th class="table-type" style="width: 5%">床号</th>
											<th class="table-type" style="width: 10%">住院号</th>
											<th class="table-type" style="width: 10%">姓名</th>
											<th class="table-type" style="width: 5%">数量</th>
											<th class="table-type" style="width: 5%">日期</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!empty reportsbreakfast}">
											<tr>
												<td colspan="6" style="background-color: #32C5D2;color: white;">早餐</td>
											</tr>
											<c:forEach items="${reportsbreakfast }" var="m" varStatus="status">
												<tr>
													<td>${m.areaName }</td>
													<td>${m.bedName }</td>
													<td>${m.patientNo }</td>
													<td>${m.username }</td>
													<td>${m.count }</td>
													<td>${date }</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${!empty reportslunch}">
											<tr>
												<td colspan="6" style="background-color: #32C5D2;color: white;">午餐</td>
											</tr>
											<c:forEach items="${reportslunch }" var="m" varStatus="status">
												<tr>
													<td>${m.areaName }</td>
													<td>${m.bedName }</td>
													<td>${m.patientNo }</td>
													<td>${m.username }</td>
													<td>${m.count }</td>
													<td>${date }</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${!empty reportsdinner}">
											<tr>
												<td colspan="6" style="background-color: #32C5D2;color: white;">晚餐</td>
											</tr>
											<c:forEach items="${reportsdinner }" var="m" varStatus="status">
												<tr>
													<td>${m.areaName }</td>
													<td>${m.bedName }</td>
													<td>${m.patientNo }</td>
													<td>${m.username }</td>
													<td>${m.count }</td>
													<td>${date }</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
				<div class="tpl-alert"></div>
			</div>
		</div>
	</div>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/jquery.min.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/amazeui.min.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/app.js"></script>

	<script type="text/javascript">
		function categoryChange(obj) {
			var categoryId = $(obj).val();
			if ($.trim(categoryId) == '') {
				return;
			}
			var url = "${ctx}/meal/meals/ajax?categoryId=" + categoryId;
			$.get(url, {}, function(data) {
				var html = "";
				$.each(data.data, function(i, item) {
					var op = "<option value='"+item.id+"'>" + item.name + "</option>";
					var mId = "${mealId}";
					if (mId == item.id) {
						op = "<option selected='selected' value='"+item.id+"'>" + item.name + "</option>";
					}
					html += op;
				});
				$("#mealIdSelect").html(html);
			});
		}
		$(document).ready(function() {
			var obj = $("select[name='categoryId']");
			categoryChange(obj);
		});
	</script>
</body>
</html>