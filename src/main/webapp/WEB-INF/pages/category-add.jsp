<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>新增餐品种类 - 点餐管理系统</title>
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
									class="am-icon-angle-right"></i> <span>配送详情报表</span> </a><a
								href="${ctx}/order/orders"> <i 
									class="am-icon-angle-right"></i> <span>订单报表</span> </a> <a 
								href="${ctx}/report/meal"> <i
									class="am-icon-angle-right"></i> <span>单品统计报表</span> </a>
							</li>
						</ul></li>

					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list active"> <i
							class="am-icon-th-list"></i> <span>种类管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
					</a>
						<ul class="tpl-left-nav-sub-menu" style="display: block;">
							<li><a href="${ctx}/meal/categories"> <i
									class="am-icon-angle-right"></i> <span>种类查询</span> </a> <a
								href="${ctx}/meal/category/add" class="active"> <i
									class="am-icon-angle-right"></i> <span>新增种类</span> </a></li>
						</ul></li>

					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-cart-plus"></i> <span>菜品管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
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
						<span class="am-icon-code"></span> 新增种类
					</div>
				</div>
				<div class="tpl-block ">
					<div class="am-g tpl-amazeui-form">
						<div class="am-u-sm-12 am-u-md-9">
							<form action="${ctx}/meal/category/add" method="post"
								class="am-form am-form-horizontal">

								<c:if test="${not empty propMsg}">
									<div class="am-alert am-alert-warning" data-am-alert>
										<button type="button" class="am-close">&times;</button>
										<p>${propMsg }</p>
									</div>
								</c:if>
								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">种类名称
									</label>
									<div class="am-u-sm-9">
										<input type="text" id="name" name="name" placeholder="种类名称...">
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-phone" class="am-u-sm-3 am-form-label">排序码
									</label>
									<div class="am-u-sm-9">
										<input type="number" id="sort" name="sort"
											placeholder="请输入排序码..."> <small
											class="am-text-danger">注意：排序码越小，排名越靠前</small>
									</div>
								</div>
								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3">
										<button type="submit" class="am-btn am-btn-primary">提交</button>
									</div>
								</div>
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