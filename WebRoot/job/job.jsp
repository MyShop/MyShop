<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.user.Userinfo" %>
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
  		<div style="width: 80%; margin-left: 10%;">
  			<div id ="top" style="weidth:80%;height:120px;background-color:blue;">
  			</div>
  			<div id="menu" style="background-color:silver;height: 30px;display:block; ">
  				<table>
					<tr>
						<td width="80px" align="center"><a href="<%=basePath%>job.xhtml">职位</a></td>
						<td width="80px" align="center"><a href="<%=basePath%>zhufang.xhtml">租房</a></td>
						<td width="80px" align="center"><a href="<%=basePath%>shoufang.xhtml">售房</a></td>
					</tr>
				</table>
			</div>
			<div id="content" style="width:100%;height:400px;display:block;">
				<div id="search" style="width:100%;display: float: left;">
					<table>
						<tr><td style="width:40px;padding-left:10px;font-size: 13px;border-left-width:5px;border-left-color:red;display: block" ondblclick="">关键词</td><td style="width:30px;padding-left:10px;font-size: 13px;">公司</td><td style="width:30px;padding-left:10px;font-size: 13px;">地点</td></tr>
					</table>
					<input style="width: 200px;height:30px;" type="text" name="key"/>
			  </div>
				<div id="left-content" style="height:400px;width:20%;background-color:yellow;float: left;">
				</div>
				<div id="right-content" style="height:400px;width:80%; background-color:red;float: left;">
				</div>
			</div>
		</div>
  </body>
</html>
