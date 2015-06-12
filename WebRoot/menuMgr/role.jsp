<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'role.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div style="float:left;width: 20%;margin-top: 200px;">
  	 <jsp:include page="../common/ConsoleHeader.jsp"></jsp:include>
  </div>
  <div style="float:left;width:80%;margin-top: 200px;">
  <form action="<%=basePath%>menuMgr/IntoMenu.xhtml" method="post">
  	<s:label>角色名</s:label>
  	<select name="role" list="">
  		<option value="">请选择角色</option>
  		<s:iterator value="#request.roleList" var="RoleType">
  			<option  value="<s:property value="#RoleType.id"/>"><s:property value="#RoleType.name"/></option>
  		</s:iterator>
  	</select>
  	<input type="submit" value="提交">
  </form>
  <a ondblclick="" style="display: block;"></a>
  </div>
  </body>
</html>
