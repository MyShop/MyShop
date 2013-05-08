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
<%String role = (String)request.getAttribute("role");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
<script type="text/javascript">
  $(document).ready(
  		rand = Math.random(),
  		$.getJSON("/IBS/console/getMenuListByRole.xhtml?role="+<%=role%>+"&jsonid="+rand,function(data)
  			{
						if (data.length > 0) {
							function analysis(temp) {
								var tempHtml = "<ul>";
								for ( var i = 0; i < temp.length; i++) {
									tempHtml = tempHtml
											+ "<li id='node_" + temp[i].id +"'>"+"<a  " + "onclick = \"getMenu(" + temp[i].id +")\">"
											+ temp[i].name +"(" + temp[i].id +")" +"</a>";
									if (temp[i].child != null
											&& temp[i].child.length > 0) {
										tempHtml = tempHtml
												+ analysis(temp[i].child);
									}
									tempHtml = tempHtml + "</li>";
								}
								tempHtml = tempHtml + "</ul>";
								return tempHtml;
							}
							innerhtml = analysis(data);

						} else {
							alert("对不起，加载数据失败");
						}
						$("#menuTree")[0].innerHTML = innerhtml;
					}
  	     )
  );
  
  function getMenu(id)
  {
  	rand = Math.random();
  	$.getJSON("/IBS/console/getMenuById.xhtml?id="+id +"&jsonid="+rand,function(data)
  	{
  		$("input[name='id']").val(data.id);
  		$("input[name='name']").val(data.name);
  		$("input[name='url']").val(data.url);
  		$("input[name='role']").val(data.role);
  		$("input[name='parent']").val(data.parent);
  	}
  	);
  	$("input[value='添加']").attr("disabled",true);
  }
  
  function add()
  {
  	$("form[name='menu']").attr("action","/IBS/console/addMenu.xhtml");
  	$("form[name='menu']").submit();
  }
  function del()
  {
  	$("form[name='menu']").attr("action","/IBS/console/delMenu.xhtml?id="+$("input[name='id']").val());
  	$("form[name='menu']").submit();
  }
  
  function edit()
  {
  	  $("form[name='menu']").attr("action","/IBS/console/editMenu.xhtml?id="+$("input[name='id']").val());
  	  $("form[name='menu']").submit();
  }
  
  function enableAdd()
  {
  	$("input[value='添加']").attr("disabled",false);
  	$("input[type='text']").val("");
  }
</script>
</head>
<body>
	<div style="float:left;width: 20%;margin-top: 200px;">
		<jsp:include page="../common/ConsoleHeader.jsp"></jsp:include>
	</div>
	<div style="float:left;width:20%;margin-top: 200px;">
		<input type="button" value="添加菜单" onclick="enableAdd()"/>
		<div id="menuTree">
		</div>
	</div>
	<div style="float:left;width:40%;margin-top: 200px;">
		<h1 align="center" style="font-family : cursive;font-style : normal;font-size : 16pt;font-weight : bold;font-variant : small-caps;">修改的菜单</h1>
		<s:form id="addMenu" name="menu" action="">
		 	<table>
		 		<tr>
		 			<td><label>菜单编号</label></td>
		 			<td><input name="id" type="text" value="" disabled="disabled"/></td>
		 			<td><label>菜单名</label></td>
		 			<td><input type="text" name="name" value=""/></td>
		 		</tr>
		 		<tr>
		 			<td><label>菜单链接地址</label></td>
					<td><input type="text" name="url" value=""/></td>
			 		<td><label>菜单所属角色</label></td>
					<td><input type="text" name="role" value=""/></td>
		 		</tr>
		 		<tr>
		 			<td>父级菜单</td>
		 			<td><input type="text" name="parent" value=""/></td>
		 			<td></td>
		 			<td></td>
		 		</tr>
		 	</table>
			<input type="button" value="添加" onclick="add()" disabled="disabled"/><input type="button" onclick="edit()" value="修改"/><input type="button" onclick="del()" value="删除"/>		
		</s:form>
	</div>
</body>
</html>
