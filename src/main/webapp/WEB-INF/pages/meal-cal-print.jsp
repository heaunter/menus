<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>单个产品统计打印 - ${date }</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<style>
body {
	font-size: 11px;
}
</style>
</head>

<body>
	<div>
		<input id="btnPrint" type="button" value="打印">
	</div>
	<div id="printArea" style="padding-left: 100px;padding-right: 100px;">
		<center>
			<h3>宁波市第一医院 单餐品统计报表</h3>
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
			<span style="display: inline-block;float: right;"><b>餐品</b>：${mealBean.name }</span>
		</p>
		<p>
			<b>就餐日期</b>：${date } <span style="display: inline-block;float: right;"><b>打印日期</b>：<fmt:formatDate
					value="${printDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span>
		</p>
		<table
			style="border:1px solid black;width:100%;min-height:25px;line-height:20px;text-align: left;border-collapse: collapse;padding: 2px;min-height: 20px;">
			<tr>
				<th style="border: 1px solid black;width: 20%">病区</th>
				<th style="border: 1px solid black;width: 10%">床号</th>
				<th style="border: 1px solid black;width: 15%">住院号</th>
				<th style="border: 1px solid black;width: 10%">姓名</th>
				<th style="border: 1px solid black;width: 8%">数量</th>
				<th style="border: 1px solid black;width: 10%">就餐日期</th>
			</tr>
			<c:if test="${!empty reportsbreakfast}">
				<tr>
					<td colspan="6" style="border: 1px solid black;background-color: #BFBFBF;">早餐</td>
				</tr>
				<c:forEach items="${reportsbreakfast }" var="m" varStatus="status">
					<tr>
						<td style="border: 1px solid black;">${m.areaName }</td>
						<td style="border: 1px solid black;">${m.bedName }</td>
						<td style="border: 1px solid black;">${m.patientNo }</td>
						<td style="border: 1px solid black;">${m.username }</td>
						<td style="border: 1px solid black;">${m.count }</td>
						<td style="border: 1px solid black;">${date }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${!empty reportslunch}">
				<tr>
					<td colspan="6" style="border: 1px solid black;background-color: #BFBFBF;">午餐</td>
				</tr>
				<c:forEach items="${reportslunch }" var="m" varStatus="status">
					<tr>
						<td style="border: 1px solid black;">${m.areaName }</td>
						<td style="border: 1px solid black;">${m.bedName }</td>
						<td style="border: 1px solid black;">${m.patientNo }</td>
						<td style="border: 1px solid black;">${m.username }</td>
						<td style="border: 1px solid black;">${m.count }</td>
						<td style="border: 1px solid black;">${date }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${!empty reportsdinner}">
				<tr>
					<td colspan="6" style="border: 1px solid black;background-color: #BFBFBF;">晚餐</td>
				</tr>
				<c:forEach items="${reportsdinner }" var="m" varStatus="status">
					<tr>
						<td style="border: 1px solid black;">${m.areaName }</td>
						<td style="border: 1px solid black;">${m.bedName }</td>
						<td style="border: 1px solid black;">${m.patientNo }</td>
						<td style="border: 1px solid black;">${m.username }</td>
						<td style="border: 1px solid black;">${m.count }</td>
						<td style="border: 1px solid black;">${date }</td>
					</tr>
				</c:forEach>
			</c:if>
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