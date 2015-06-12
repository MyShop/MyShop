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
<jsp:include page="../common/header.jsp"></jsp:include>
<%
  String prodUrl = request.getAttribute("prodUrl").toString();
  String type = request.getAttribute("type").toString();
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
 %>
<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js" ></script>
<script type="text/javascript">
function nextPage()
{	
	$("#pageNow")[0].value=Number($("#pageNow")[0].value) + 1;
	$($("#pageForm")[0]).attr("action","<%=prodUrl%>?type=<%=type%>");
	$("#pageForm")[0].submit();
}

function upPage()
{
	$("#pageNow")[0].value = Number($("#pageNow")[0].value) -1;
	$($("#pageForm")[0]).attr("action","<%=prodUrl%>?type=<%=type%>");
	$("#pageForm")[0].submit();
}
</script>
</head>
<body>
<div style=" position:absolute; margin-top:100px;margin-left: 20%;float: left;width: 80%">
	<s:iterator id="productList" var="product" value="#request.productList" status="st" >
	    <s:div cssStyle="margin-left:4%;width:20%;float:left;">
	    <s:div cssStyle="width:100%;">
	    	<a href="<%=path%>/Product/InfoProduct.xhtml?id=<s:property value="#product.id"/>"><img src="<s:property value="#product.firstPicture"/>"  width="120px" height="160px"  /></a>
	    </s:div>
	    <s:div   cssStyle="margin-left:8%;width:100%;">
	    	<a href="<%=path%>/Product/InfoProduct.xhtml?id=<s:property value="#product.id"/>"><s:property value="#product.name"/></a>
	    </s:div>
	    </s:div>
	</s:iterator>
	<div style="width: 100%;float: inherit;">
	     共<s:property value="rowCount"/>记录&nbsp;&nbsp;总共<s:property value="pageCount"/>页&nbsp;&nbsp;
	     第<s:property value="pageNow"/>页
	     <s:if test="pageNow==1">
	     	<a onclick="nextPage()">下一页</a>
	     </s:if>
	     <s:elseif test="pageNow>1 && pageNow<pageCount">
	     	<a onclick="upPage()">上一页</a>
	     	<a onclick="nextPage()">下一页</a>
	     </s:elseif>
	     <s:else>
	     	<a onclick="upPage()">上一页</a>
	     </s:else>
	 <s:form action=""  name="pageForm" id="pageForm" >
	 	<s:hidden  name="pageNow"  id="pageNow"></s:hidden>
	 	<s:hidden name="pageCount" id="pageCount"></s:hidden>
	 	<s:hidden name="rowCount" id="rowCount"></s:hidden>	
	 </s:form>
	</div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>