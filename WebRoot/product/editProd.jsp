<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.product.Product"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>产品查询页面</title>
    
	<script type="text/javascript" src="<%=path%>/js/TQEditor.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=path %>/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/consoleMenu.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css"> 
	<script type="text/javascript">
			$(document).ready(
			function() {
				rand = Math.random();
				$.ajax({
					type : "POST",
					url : "/IBS/loadMenu.xhtml?role=0&jsonid=" + rand,// 提交页面
					beforeSend : function() { // 设置表单提交前方法
					},
					error : function(request) { // 设置表单提交出错
					},
					success : function(data) // 设置表单提交成功后的方法
					{
						list = data.menuList;
						if (list.length > 0) {
							function analysis(temp) {
								var tempHtml = "<ul>"

								for ( var i = 0; i < temp.length; i++) {
									tempHtml = tempHtml
											+ "<li><a href='"+temp[i].url+"'>"
											+ temp[i].name + "</a>"
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
							innerhtml = analysis(list);

						} else {
							alert("对不起，加载数据失败");
						}
						$("#consoleId")[0].innerHTML = "<ul id='menu'>"
					+ innerhtml.substr(4, innerhtml.length - 4);
					}
				});
			initMenu();
			}
			);
		
		function fileUpload() {
			$.ajaxFileUpload( {
				url : 'fileUploadAction.xhtml',     //用于文件上传的服务器端请求地址  
				secureuri : false,            //一般设置为false  
				fileElementId : "file",        //文件上传空间的id属性  <input type="file" id="file" name="file" />  
				dataType : 'json',            //返回值类型 一般设置为json  
				success : function(data, status) {
					var fileNames = data.fileFileName; //返回的文件名 
					var filePaths = data.filePath;     //返回的文件地址 
					var last = filePaths.lastIndexOf("\\");
					var fileName = filePaths.substring(last+1, filePaths.length);
						//将上传后的文件 添加到页面中 以进行下载
					$("#down").after("<tr><td height='25'>"+fileNames+
								"</td><td><a href='downloadFile.xhtml?downloadFilePath="+filePaths+"'>下载</a><input type='hidden' name='filePath' id='filePath' value='/upload/" + fileName +"'/></td></tr>")
				}
			})
		}	
	    
    </script>
  </head>
  <body>
  		<div style="float:left;width: 20%;margin-top: 200px;">
			<div  id="consoleId"></div>
		</div>
		<div  style="float:left;width: 80%;margin-top: 200px;">
		<form action="<%=path%>/prodMgr/updateProdPage.xhtml" method="post">
			<table>
				<tr>
					<td><s:textfield name="name"  id="name"  label="产品名" size="20"/><s:hidden name="id" id="id"/> </td>		
				</tr>
				<tr>
					<td><s:textfield name="price" id="price" label="价格" size="20"/></td>
				</tr>			
				<tr>
					<td><s:textfield name="type" id="type"  label="类别" size="20"/></td>
				</tr>
				<tr>
					<td>图片:</td>
					<td>
						<s:hidden name="picture" id="picture"/>
						<input  name="file" id="file" type="file">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="上传文件"  onclick="fileUpload()">
						<br/>
						<span>
							<table id="down">
							</table>
						</span>
					</td>
				</tr>
				<tr>
					<s:textarea name="miaoshu" id="miaoshu"   label="描述"  cols="100" rows="20"/>
					<script type="text/javascript">
						new TQEditor('miaoshu');//必须在表单域后
					</script>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="提   交"/></td>
				</tr>
			</table>
		</form>
		</div>
 </body>
</html>
