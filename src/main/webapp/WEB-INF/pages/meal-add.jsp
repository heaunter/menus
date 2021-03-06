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
<link rel="stylesheet"
	href="${ctx}/resources/kindeditor/themes/default/default.css" />
<script charset="utf-8" src="${ctx}/resources/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${ctx}/resources/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
	KindEditor.ready(function(K) {
		var editor = K.editor({
			uploadJson : "${ctx}/meal/image?thumb",
			allowFileManager : true
		});
		K("#uploadImage").click(
				function() {
					editor.loadPlugin("image", function() {
						editor.plugin.imageDialog({
							showRemote : false,
							imageUrl : K("#imageUrl").val(),
							clickFn : function(url, title, width, height,
									border, align) {
								K("#imageUrlDisplay").attr("src", url);
								K("#imageUrlDisplay").css("display", "block");
								K("#imageUrl").val(url);
								editor.hideDialog();
							}
						});
					});
				});
	});
</script>
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
						</ul></li>

					<li class="tpl-left-nav-item"><a href="javascript:;"
						class="nav-link tpl-left-nav-link-list "> <i
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
						<ul class="tpl-left-nav-sub-menu" style="display: block;">
							<li><a href="${ctx}/meal/meals"> <i
									class="am-icon-angle-right"></i> <span>菜品查询</span> </a> <a
								href="${ctx}/meal/add" class="active"> <i
									class="am-icon-angle-right"></i> <span>新增菜品</span> </a></li>
						</ul></li>
				</ul>
			</div>
		</div>

		<div class="tpl-content-wrapper">
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span> 新增菜品
					</div>
				</div>
				<div class="tpl-block ">
					<div class="am-g tpl-amazeui-form">
						<div class="am-u-sm-12 am-u-md-9">
							<form action="${ctx}/meal/add" method="post"
								class="am-form am-form-horizontal">
								<c:if test="${not empty propMsg}">
									<div class="am-alert am-alert-warning" data-am-alert>
										<button type="button" class="am-close">&times;</button>
										<p>${propMsg }</p>
									</div>
								</c:if>
								<div class="am-form-group">
									<label for="name" class="am-u-sm-3 am-form-label">餐品名称
									</label>
									<div class="am-u-sm-9">
										<input type="text" required id="name" name="name"
											placeholder="餐品名称...">
									</div>
								</div>
								<div class="am-form-group">
									<label for="name" class="am-u-sm-3 am-form-label">描述 </label>
									<div class="am-u-sm-9">
										<textarea rows="2" name="description"
											placeholder="餐品简单描述..."></textarea>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">种类
									</label>
									<div class="am-u-sm-9">
										<select name="category">
											<c:forEach items="${categories}" var="c">
												<option value="${c.id }">${c.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">价格
									</label>
									<div class="am-u-sm-9">
										<input type="text" required id="price" name="price">
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-weibo" class="am-u-sm-3 am-form-label">封面图
									</label>
									<div class="am-u-sm-9">
										<div class="am-form-group am-form-file">
											<img id="imageUrlDisplay" src="" style="display: none;">
											<input id="imageUrl" name="imageUrl" value=""
												style="display: none;">
											<button id="uploadImage" type="button"
												class="am-btn am-btn-default am-btn-sm">
												<i class="am-icon-cloud-upload"></i> 添加封面图片
											</button>
										</div>
										<p style="font-size: 12px;">图片尺寸：300 * 300像素，图片大小不超过300KB，不支持GIF格式</p>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">适用时段
									</label>
									<div class="am-u-sm-9">
										<label class="am-checkbox-inline" style="padding-top:0px;">
											<input type="hidden" value="1" name="breakfast"
											id="breakfast"> <input
											onchange="breakfastChange(this);" type="checkbox"
											checked="checked" id="ckBreakfast" value="1" data-am-ucheck>早餐
										</label> <label class="am-checkbox-inline" style="padding-top:0px;">
											<input type="hidden" value="1" name="lunch" id="lunch">
											<input type="checkbox" onchange="lunchChange(this);"
											checked="checked" id="ckLunch" value="1" data-am-ucheck>
											午餐 </label> <label class="am-checkbox-inline"
											style="padding-top:0px;"> <input type="hidden"
											value="1" name="dinner" id="dinner"> <input
											type="checkbox" onchange="dinnerChange(this);"
											checked="checked" id="ckDinner" value="1" data-am-ucheck>
											晚餐 </label>
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
	<script type="text/javascript">
		function breakfastChange(_this) {
			var status = $(_this).prop('checked');
			if (status == false) {
				$(_this).attr("checked", false);
				$("#breakfast").val("0");
			}
			if (status == true) {
				$(_this).attr("checked", true);
				$("#breakfast").val("1");
			}
		}
		function lunchChange(_this) {
			var status = $(_this).prop('checked');
			if (status == false) {
				$(_this).attr("checked", false);
				$("#lunch").val("0");
			}
			if (status == true) {
				$(_this).attr("checked", true);
				$("#lunch").val("1");
			}
		}
		function dinnerChange(_this) {
			var status = $(_this).prop('checked');
			if (status == false) {
				$(_this).attr("checked", false);
				$("#dinner").val("0");
			}
			if (status == true) {
				$(_this).attr("checked", true);
				$("#dinner").val("1");
			}
		}
	</script>
</body>
</html>