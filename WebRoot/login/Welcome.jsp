<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>找如意装修，就到买如意</title>
	</head>
	<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div id="zhaoshang" style="position: absolute;left: 290px;top: 80px;">
		<a href="<%=path%>/Merch/Merch_Inform.xhtml"><img src="/upload/page/zhaoshang.jpg"></a>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>