<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.product.Product" %>
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
  Product prod = (Product)request.getAttribute("prod");			
 %>
<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js" ></script>
<script type="text/javascript">
function addToCart()
{
	$("#productInfo").submit();
}

function reduc()
{
	if(($("#count")[0].value) == 1)
	{
	}
	else 
	$("#count")[0].value = Number($("#count")[0].value) - 1;	
}

function add()
{
	$("#count")[0].value = Number($("#count")[0].value) + 1;
}
</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div style="pomargin-top:0px;margin-left: 20%;float: left;width: 80%">
	<div style="width: 100%;">
		<div  style="padding:5px;  width: 240px;height: 300px;border:1px solid blue;float: left;display: block;">
			<img  src="<%=prod.getFirstPicture()%>" width="220px" height="280px" style="margin-left: 10px;margin-top: 10px;"/>
		</div>
		<form action="<%=path%>/buy/addToCart.xhtml" id="productInfo" method="post">
		<div style="width: 300px;height:300px;float: left;">
			
			<div align="center" style="width: 100%;height: 200px;"><h1><%=prod.getName()%></h1>价格：<%=prod.getPrice()%></div>
			
			<div style="width:100%;display:inline; height:20px; float: left;">
				<div style="display: inline;margin-left: 30px;float: left;" align="left" ><a onclick="reduc()" style="width: 20px;height: 20px;display:inline-block;">-</a><s:textfield name="count" id="count" size="1" cssStyle="width:20px;"  value="1"></s:textfield>&nbsp;&nbsp;&nbsp;<a onclick="add()" style="width: 20px;height: 20px;display:inline-block;">+</a></div>
				<div style="float: left;display: inline;margin-left: 20px;" align="right"  >
				<input type="button" value="添加到购物车" onclick="addToCart()"/>
				<input type="hidden" name="id" value="<%=prod.getId()%>"/>
				<input type="hidden" name="isBuy" value="true"/>
				</div>
			</div>
		</div>	
		</form>
		
	</div>
	<div style="width:100%;float: left;">
	<hr>
	<%=prod.getMiaoshu()%>
	</div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>