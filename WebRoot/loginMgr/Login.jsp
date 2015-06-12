<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<title>小民小利</title>
<style type="text/css">
.leftMenu {
	clear: left;
	float: left;
	height: 60%;
	width: 50%;
}

.submit {
	width: 60px;
}

.leftMenu .errorMessage {
	font-family: "宋体";
	font-size: 13px;
	font-style: normal;
	color: #990000;
	position: relative;
	left: 70px;
	height: 12px;
	top-padding: 10px;
	width: 160px;
	top: 8px;
}
</style>


<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=path %>/js/public.js"></script>
<script type="text/javascript">
	function enter(formId) {
		$("#" + formId).submit();
	}
	
	function register()
	{
		window.location.href = "register.xhtml";
	}
</script>
</head>
<body>
	<div class="leftMenu">
		<s:form action="/consoleLogin/loginEnter.xhtml" id="login" method="post">
			<table>
			<tr>
				<td><s:textfield  name="email" id="email" label="账号" ></s:textfield></td>
			</tr>
			<tr>
				<td><s:password  name="password" id="password" label="密码"></s:password></td>
			</tr>
			<tr>
				<td><s:textfield  name="rePassword" id="rePassword" label="验证码" ></s:textfield></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" class="submit" onClick="enter('login')" value="登录" style="margin-left:30px"/>
				<img src="<%=path %>/servlet/ShowImg" border="0" style="margin-left:20px" />
			</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><div style="width: 150px;background-color:#9E86F0;" onclick="register()">注&nbsp;&nbsp;&nbsp;册</div></td>
			</tr>
			</table>
		</s:form>
	</div>
</body>
</html>