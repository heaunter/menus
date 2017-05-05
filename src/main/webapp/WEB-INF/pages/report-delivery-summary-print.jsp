<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>送餐员汇总备料打印 -${date }</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<style>
body {
	font-size: 12px;
}
</style>
</head>

<body>
	<div>
		<input id="btnPrint" type="button" value="打印">
	</div>
	<div id="printArea" style="padding-left: 100px;padding-right: 100px;">
		<center>
			<h3>宁波市第一医院 发饭汇总表</h3>
		</center>
		<p>
			<b>餐次</b>：
			<c:choose>
				<c:when test="${period == 'breakfast' }">
					早餐
				</c:when>
				<c:when test="${period == 'lunch' }">
					午餐
				</c:when>
				<c:when test="${period == 'dinner' }">
					晚餐
				</c:when>
				<c:otherwise>
					早餐|午餐|晚餐
				</c:otherwise>
			</c:choose>
			<span style="display: inline-block;float: right;"><b>总金额</b>:${totalMoney
				} </span>
		</p>
		<p>
			<b>就餐日期</b>：${date } <span
				style="display: inline-block;float: right;"><b>打印日期</b>：<fmt:formatDate
					value="${printDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
			</span>
		</p>
		<p>
			<b>所选病区</b>：${areaNames }
		</p>
		<table
			style="border:1px solid black;width:100%;min-height:25px;line-height:20px;text-align: left;border-collapse: collapse;padding: 2px;min-height: 20px;">
			<tr>
				<th style="border: 1px solid black;">餐次</th>
				<th style="border: 1px solid black;">菜品</th>
				<th style="border: 1px solid black;">数量</th>
				<th style="border: 1px solid black;">金额</th>
			</tr>
			<c:forEach items="${prepareReport }" var="re">
				<tr>
					<td style="border: 1px solid black;"><c:choose>
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
						</c:choose>
					</td>
					<td style="border: 1px solid black;">${re.name }</td>
					<td style="border: 1px solid black;">${re.amount }</td>
					<td style="border: 1px solid black;">${re.price }</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<script src="${ctx}/resources/amaze-ui-2.7.2/js/jquery.min.js"></script>
	<script src="${ctx}/resources/js/jquery.PrintArea.min.js"></script>
	<script>
		$("#btnPrint").click(function() {
			$("#printArea").printArea();
		});
	</script>
</body>

</html>