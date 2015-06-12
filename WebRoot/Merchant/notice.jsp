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
<script type="text/javascript" src="<%=path %>/js/public.js"></script>
<script type="text/javascript">
	function intoVote()
	{
		window.location.href = "<%=path%>/Merch/Merch_toVote.xhtml";
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

h3
{
	font-size: 16px;

}

h4
{
	font-size: 14px;
}
</style>

</head>
<body>
<div style="width: 60%;height:1000px;margin-left: 20%;margin-top: 130px;">
	<table>
		<tr>
			<td align="center"><h1>喜讯!</h1></td>
		</tr>
		<tr>
			<td>
			<hr>
			<h3 style="font-size: 16px;">&nbsp;&nbsp;&nbsp;&nbsp;在此免费为大家提供广告位,同时为了能够选出最有竞争力的商家，现举行投票活动！投票规则很简单，仅此3条:</h3></td>
		</tr>
		<tr>
			<td><h4>&nbsp;&nbsp;&nbsp;&nbsp;1、每个人每天具有五次投票机会。</h4></td>
		</tr>
		<tr>
			<td><h4>&nbsp;&nbsp;&nbsp;&nbsp;2、每次只能向不同的商家的投票。</h4></td>
		</tr>
		<tr>
			<td><h4>&nbsp;&nbsp;&nbsp;&nbsp;3、该活动将进行一个月.得票前6名者，将免费获得本网站广告位。</h4><hr></td>
		</tr>
		<tr>
			<td align="center">
				<input type="button" onclick="intoVote()" width="200px" height="60px"  value="参加投票" style="font-size: 20px;"/>
			</td>
		</tr>
	</table>
	
</div>
</body>
</html>