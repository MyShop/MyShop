<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(function() {
		setInterval(function() {
			$(".endtime").each(
					function() {
						var obj = $(this);
						var endTime = new Date(
								parseInt($("#time")[0].value));
						var nowTime = new Date();
						var nMS = endTime.getTime() - nowTime.getTime();
						var myD = Math.floor(nMS / (1000 * 60 * 60 * 24));
						var myH = Math.floor(nMS / (1000 * 60 * 60)) % 24; //小时 
						var myM = Math.floor(nMS / (1000 * 60)) % 60; //分钟 
						var myS = Math.floor(nMS / 1000) % 60; //秒 
						var myMS = Math.floor(nMS / 100) % 10; //拆分秒
						if (myD >= 0) {
							var str = myD + "天" + myH + "小时" + myM + "分" + myS
									+ "." + myMS + "秒";
						} else {
							var str = "已到期！";
						}
						obj.html("订单维修时间还剩："+str);
					});
		}, 100);
	});
</script>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div>
		<div id="timeRepair" name="timeRepair" class="endtime" style="width: 80%;margin-left: 20%;color: red;"></div>
		<s:hidden id="time" value="%{#request.timeRepair}"></s:hidden>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>