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
String PageURL = request.getAttribute("PageURL").toString();
 %>
<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js" ></script>
<script type="text/javascript">
function nextPage()
{	
	$("#pageNow")[0].value=Number($("#pageNow")[0].value) + 1;
	$($("#pageForm")[0]).attr("action","<%=PageURL%>");
	$("#pageForm")[0].submit();
}

function upPage()
{
	$("#pageNow")[0].value = Number($("#PageURL")[0].value) -1;
	$($("#pageForm")[0]).attr("action","<%=PageURL%>");
	$("#pageForm")[0].submit();
}

function vote(id)
{
	$.getJSON("<%=path%>/Merch/Merch_Vote.xhtml?id="+id,
		function(data)
		{
			if(data[0].result == "success")
			($("#vote"+id)[0]).innerHTML = data[0].votes;
			else
				alert(data[0].message);	
		}
	);
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
.row-even
{
	background:#def7c2;
}
.row-odd
{
	background:#c8e7a6;
}
</style>
</head>
<body>
	<div style="width: 60%;height:1000px;margin-left: 20%;margin-top: 130px;">
	<table width="100%">
		<tr style="background-color:#c8e7a6;font-size: 18px;">
			<td width="16.6%">商家名称</td>
			<td width="16.6%">联系电话</td>
			<td width="16.6%">联系地址</td>
			<td	width="16.6%">经营项目</td>
			<td	width="16.6%">得票数</td>
			<td	width="16.6%"></td>
		</tr>
	<s:iterator id="merchsList" var="merch" value="#request.merchsList" status="st" >
	    <tr class="<s:if test = "#st.even">row-even</s:if><s:else>row-odd</s:else> ">
	    	<td width="16.6%"><s:property value="#merch.name"/></td>
	    	<td width="16.6%"><s:property value="#merch.phone"/></td>
	    	<td width="16.6%"><s:property value="#merch.addr"/></td>
	    	<td width="16.6%"><s:property value="#merch.project"/></td>
	    	<td width="16.6%" id="vote<s:property value="#merch.id"/>"><s:property value="#merch.votes"/></td>
	    	<td width="16.6%"><input type="button" onclick="vote(<s:property value="#merch.id"/>)" value="投票"/> </td>
	    </tr>
	</s:iterator>
	</table>
	<div style="width: 100%;float: inherit;">
	     共<s:property value="rowCount"/>记录&nbsp;&nbsp;总共<s:property value="pageCount"/>页&nbsp;&nbsp;
	     第<s:property value="pageNow"/>页
	     <s:if test="pageNow==1">
	     	<s:if test="pageCount > 1">
	     		<a onclick="nextPage()">下一页</a>
	     	</s:if>
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
</body>
</html>