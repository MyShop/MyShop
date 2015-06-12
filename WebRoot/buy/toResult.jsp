<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.product.Product" %>
<%@ page import="com.ibs.hibernate.bean.buy.cartOfProd" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style type="text/css">
a{cursor: pointer;}

</style>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
 %>
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js" ></script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
	You Success!
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>