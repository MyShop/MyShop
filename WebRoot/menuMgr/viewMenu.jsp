<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="ibs" uri="/IBSTaglib"%>
<%@page import="java.util.List" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%List menuList = (List)request.getAttribute("menuList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
<script type="text/javascript">
  $(document).ready(
  );
  
  function edit(id)
  {
  	menuId = id.substr(5,id.length);
  	alert(menuId);
  	rand = Math.random();
  	$.getJSON("/IBS/console/getMenuById.xhtml?id="+menuId +"&jsonid="+rand,function(data)
  	{
  		alert(data);
  	}
  	);
  	
  }
</script>
</head>
<body>
	<div style="float:left;width: 20%;margin-top: 200px;">
		<jsp:include page="../common/ConsoleHeader.jsp"></jsp:include>
	</div>
	<div style="float:left;width:20%;margin-top: 200px;">
		<div id="menuTree">
			<ibs:ul list="<%=menuList%>"/>
		</div>
	</div>
	<div style="float:left;width:60%;margin-top: 200px;">
		<h1 align="center" style="font-family : cursive;font-style : normal;font-size : 16pt;font-weight : bold;font-variant : small-caps;">修改的菜单</h1>
		<s:form action="">
			<label>菜单编号</label>
			<input name="id" disabled="disabled"/>
			<label>菜单名</label> 
			<input type="text" name="name"/>
			<label>菜单链接地址</label>
			<input type="text" name="url"/>	
			<label>菜单所属角色</label>
			<input type="text" name="role"/>				
		</s:form>
		
	</div>
</body>
</html>
