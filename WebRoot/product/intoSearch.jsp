<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.product.Product"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  //定义列表的id号
  int i=0;//List的索引
  int id;//product的id	
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品查询页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
	function nextPage()
	{	
		$("#pageNow")[0].value=Number($("#pageNow")[0].value) + 1;
		$("#pageForm")[0].submit();
	}

	function upPage()
	{
		$("#pageNow")[0].value = Number($("#pageNow")[0].value) -1;
		$("#pageForm")[0].submit();
	}
	
	function edit(id)
	{
		window.location.href ="<%=path%>/prodMgr/editProdPage.xhtml?id="+id
	}
</script>
	
  </head>
  <body>
  		<div style="float:left;width: 20%;margin-top: 200px;">
			<jsp:include page="../common/ConsoleHeader.jsp"></jsp:include>
		</div>
		<div  style="float:left;width: 80%;margin-top: 200px;">
			<s:textfield size="20" name="keyword" id="keyword"></s:textfield>
			<div>
		<table>
			<tr>
				<td  width="20%">产品名称</td>
				<td  width="10%">价格</td>
				<td  width="10%">分类</td>
				<td  width="20%">图片</td>
				<td  width="20%">描述</td>
				<td  width="20"></td>
			</tr>
			<s:iterator  id="productList" var="product" value="#request.products">
				<tr>
					<td><s:property value="#product.name"/></td>
					<td><s:property value="#product.price"/></td>
					<td><s:property value="#product.type"/></td>
					<td><s:property value="#product.picture"/></td>
					<td><s:property value="#product.miaoshu"/></td>
					<%
					Product prod = (Product)((List)request.getAttribute("products")).get(i++);
					id = prod.getId();%>
					<td><input  type="button" onclick="edit(<%=id%>)" value="编辑" width="60px"/><input type="button"     onclick="del()"  value="删除" width="60px"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		<p></p>
		<div style="width: 100%;float: inherit;color: blue;" >
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
	 		<s:form action="prodMgr/intoProdPage.xhtml"  name="pageForm" id="pageForm" >
	 			<s:hidden  name="pageNow"  id="pageNow"></s:hidden>
	 			<s:hidden name="pageCount" id="pageCount"></s:hidden>
	 			<s:hidden name="rowCount" id="rowCount"></s:hidden>	
	 		</s:form>
		</div>
		</div>
 </body>
</html>
