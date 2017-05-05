<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>餐品列表 - 点餐管理系统</title>
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
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/report/prepare"> <i
									class="am-icon-angle-right"></i> <span>餐厅备餐报表</span> </a> <a
								href="${ctx}/report/delivery/summary"> <i
									class="am-icon-angle-right"></i> <span>配送汇总报表</span> </a><a
								href="${ctx}/report/delivery"> <i
									class="am-icon-angle-right"></i> <span>配送详情报表</span> </a>
									<a
								href="${ctx}/order/orders"> <i 
									class="am-icon-angle-right"></i> <span>订单报表</span> </a> <a 
								href="${ctx}/report/meal"> <i
									class="am-icon-angle-right"></i> <span>单品统计报表</span> </a>
							</li>
						</ul>
					</li>

					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-th-list"></i> <span>种类管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right "></i>
					</a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/categories"> <i
									class="am-icon-angle-right"></i> <span>种类查询</span> </a> <a
								href="${ctx}/meal/category/add"> <i
									class="am-icon-angle-right"></i> <span>新增种类</span> </a>
							</li>
						</ul>
					</li>

					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list active"> <i
							class="am-icon-cart-plus"></i> <span>菜品管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
					</a>
						<ul class="tpl-left-nav-sub-menu" style="display: block;">
							<li><a href="${ctx}/meal/meals" class="active"> <i
									class="am-icon-angle-right"></i> <span>菜品查询</span> </a> <a
								href="${ctx}/meal/add"> <i class="am-icon-angle-right"></i>
									<span>新增菜品</span> </a>
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
						<span class="am-icon-list-ul"></span> 餐品列表
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g" style="height: 50px;">
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-form-group">
								<select data-am-selected="{searchBox: 1,maxHeight: 500}"
									onchange="categoryChange(this);">
									<c:choose>
										<c:when test="${categoryId == '' }">
											<option value=" " selected="selected">所有类别</option>
										</c:when>
										<c:otherwise>
											<option value=" ">所有类别</option>
										</c:otherwise>
									</c:choose>
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
						<div class="am-u-sm-12 am-u-md-3" style="display: none;">
							<div class="am-form-group">
								<select onchange="periodChange(this);" name="period">
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
						<div class="am-u-sm-12 am-u-md-3"></div>
					</div>
					<div class="am-g">
						<div class="am-u-sm-12">
							<form class="am-form">
								<table
									class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>
											<th class="table-id" style="width: 2%">ID</th>
											<th class="table-title" style="width: 10%">餐品名称</th>
											<th class="table-type" style="width: 5%">价格</th>
											<th class="table-type" style="width: 20%">描述</th>
											<th class="table-type" style="width: 5%">早餐</th>
											<th class="table-type" style="width: 5%">午餐</th>
											<th class="table-type" style="width: 5%">晚餐</th>
											<th class="table-set" style="width: 20%">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${meals }" var="m" varStatus="status">
											<tr id="meal_${m.id}">
												<td>${status.count }</td>
												<td><a href="javascript:void(0)">${m.name }</a>
												</td>
												<td>￥${m.price }</td>
												<td style="width: 50%;overflow: hidden;">${m.description
													}</td>
												<td class="am-text-danger"><c:choose>
														<c:when test="${m.isBreakfast ==1}">是</c:when>
														<c:otherwise>否</c:otherwise>
													</c:choose></td>
												<td><c:choose>
														<c:when test="${m.isLunch ==1}">是</c:when>
														<c:otherwise>否</c:otherwise>
													</c:choose>
												</td>
												<td><c:choose>
														<c:when test="${m.isDinner ==1}">是</c:when>
														<c:otherwise>否</c:otherwise>
													</c:choose>
												</td>
												<td>
													<div class="am-btn-toolbar">
														<div class="am-btn-group am-btn-group-xs">
															<a href="${ctx}/meal/${m.id}/update"
																class="am-btn am-btn-default am-btn-xs am-text-secondary ms-button">
																<span class="am-icon-pencil-square-o"></span> 编辑 </a> <a
																href="${ctx}/meal/${m.id}/delete"
																class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only ms-button">
																<span class="am-icon-trash-o"></span> 删除 </a>
														</div>
													</div></td>
											</tr>
										</c:forEach>
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
			var url = "${ctx}/meal/meals?categoryId=" + categoryId;
			window.location.href = url;
		}
	</script>
</body>

</html>