<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>管理员登录</title>
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
</head>

<body data-type="login">

	<div class="am-g myapp-login">
		<div class="myapp-login-logo-block  tpl-login-max">
			<div class="myapp-login-logo-text">
				<div class="myapp-login-logo-text">医院点餐系统</div>
			</div>

			<div class="login-font">
				<i>管理员登录 </i>
			</div>
			<div class="am-u-sm-10 login-am-center">
				<form action="${ctx}/user/login" method="post" class="am-form">
					<fieldset>
						<div class="am-form-group">
							<input type="text" name="loginId" required="required" class=""
								id="doc-ipt-email-1" placeholder="请输入账号">
						</div>
						<div class="am-form-group">
							<input type="password" name="password" class=""
								required="required" id="doc-ipt-pwd-1" placeholder="密码">
						</div>
						<p>
							<button type="submit" class="am-btn am-btn-default">登录</button>
						</p>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

	<script src="${ctx}/resources/jquery/jquery-1.9.1.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/amazeui.min.js"></script>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/app.js"></script>
</body>

</html>