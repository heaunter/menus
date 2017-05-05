<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>医院点餐后台管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="${ctx}/resources/amaze-ui-2.7.2/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet"
	href="${ctx}/resources/amaze-ui-2.7.2/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${ctx}/resources/amaze-ui-2.7.2/css/admin.css">
<link rel="stylesheet"
	href="${ctx}/resources/amaze-ui-2.7.2/css/app.css">
</head>

<body data-type="index">
	<jsp:include page="/common/header.jsp" flush="false"></jsp:include>

	<div class="tpl-page-container tpl-page-header-fixed">
		<div class="tpl-left-nav tpl-left-nav-hover">
			<div class="tpl-left-nav-title">功能列表</div>
			<div class="tpl-left-nav-list">
				<ul class="tpl-left-nav-menu">
					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link active tpl-left-nav-link-list"> <i
							class="am-icon-bar-chart"></i> <span>报表</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
					</a>
						<ul class="tpl-left-nav-sub-menu" style="display: block;">
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
						</ul>
					</li>

					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-th-list"></i> <span>种类管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
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
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-cart-plus"></i> <span>菜品管理</span> <i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
					</a>
						<ul class="tpl-left-nav-sub-menu">
							<li><a href="${ctx}/meal/meals"> <i
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
			<div class="tpl-content-scope">
				<div class="note note-info">
					<h3>
						宁波市第一医院点餐系统 <span class="close" data-close="note"></span>
					</h3>
					<p>欢迎访问订餐管理系统</p>
					<br>
					<p>
						<span class="label label-danger">提示:</span> 当修改数据后，请通知点菜员同步最新数据
					</p>
				</div>
				<c:if test="${not empty message }">
					<div class="note note-info">
						<span class="label label-info">提示:</span>
						<p>
							<br /> 当前${message }正在同步订单(并发锁定中)，<a href="${ctx}/user/unlock">强制解锁</a>
						</p>
					</div>
				</c:if>
			</div>

			<!-- <div class="row">
				<div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="am-icon-comments-o"></i>
						</div>
						<div class="details">
							<div class="number">1349</div>
							<div class="desc">新消息</div>
						</div>
						<a class="more" href="#"> 查看更多 <i
							class="m-icon-swapright m-icon-white"></i> </a>
					</div>
				</div>
				<div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
					<div class="dashboard-stat red">
						<div class="visual">
							<i class="am-icon-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="number">62%</div>
							<div class="desc">收视率</div>
						</div>
						<a class="more" href="#"> 查看更多 <i
							class="m-icon-swapright m-icon-white"></i> </a>
					</div>
				</div>
				<div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="am-icon-apple"></i>
						</div>
						<div class="details">
							<div class="number">653</div>
							<div class="desc">苹果设备</div>
						</div>
						<a class="more" href="#"> 查看更多 <i
							class="m-icon-swapright m-icon-white"></i> </a>
					</div>
				</div>
				<div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="am-icon-android"></i>
						</div>
						<div class="details">
							<div class="number">786</div>
							<div class="desc">安卓设备</div>
						</div>
						<a class="more" href="#"> 查看更多 <i
							class="m-icon-swapright m-icon-white"></i> </a>
					</div>
				</div>
			</div> -->
		</div>
	</div>
	<script src="${ctx}/resources/jquery/jquery-1.9.1.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/amazeui.min.js"></script>
	<script src="${ctx}/resources/js/iscroll.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/app.js"></script>
	<script src="${ctx}/resources/js/echarts.min.js" type="text/javascript"></script>
</body>
</html>