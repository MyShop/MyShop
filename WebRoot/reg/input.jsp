<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
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
function enter(formId) {
		$("#" + formId).submit();
	}
</script>
<style type="text/css">
.errorMessage {
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
</head>
<body>
	<div style="width: 80%;margin-left: 20%;margin-top: 160px;">
		<s:form  action="registerAccount.xhtml" id="register"  method="post">
			<table  style="font-size: 14px;font-family: serif;">
				<tr>
					<td><s:textfield  name="account" id="account" label="用户名"></s:textfield></td>
				</tr>
				<tr>
					<td><s:password  name="password" id="password" label="密码" ></s:password></td>
				</tr>
				<tr>
					<td><s:password  name="rePassword" id="rePassword" label="确认密码"></s:password></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					<input type="button" onClick="enter('register')" value="提          交" style="width: 150px;"/>
			</table>
		</s:form>
	</div>
</body>
</html>