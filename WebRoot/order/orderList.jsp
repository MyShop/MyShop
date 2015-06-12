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
<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js" ></script>
<script type="text/javascript">
function  orderRepair()
{
	var id =$("#orderid")[0].value;
	window.location.href = "userOrderRepair.xhtml?id="+id;
}
</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div style="margin-left:20%;float: left;width: 50%;height: 20px;background-color: gray;">
	<s:iterator id="orderList" var="order" value="#request.orderList"
		status="st">
		<s:div cssStyle="margin-left:4%;width:100%;float:left;" onclick="orderRepair()">
	    	订单编号:<s:property value="#order.id"/>
	    	<s:hidden id="orderid" value="%{#order.orderNo}"></s:hidden>
	    	 <s:iterator  id="prodList"  var="prod" value="#order.ProdList">
		 	<s:property value="#prod.name"/>
	    	<s:property value="#prod.count"/>
	    	<s:property value="#prod.price"/>;
	           </s:iterator>
	           订单时间:<s:property value="#order.orderDate"/>
		</s:div>
	
	</s:iterator>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>