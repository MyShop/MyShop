<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.user.Userinfo" %>
<%@ page pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<%Userinfo userinfo = (Userinfo)session.getAttribute("userInfo");%>    
    <title>欢迎您，<%=userinfo.getName()%></title>
    
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
  		<table>
			<tr>
				<td width="80px" align="center"><a href="<%=basePath%>place.xhtml">地区编辑</a></td>
			</tr>
		</table>
  </body>
</html>
