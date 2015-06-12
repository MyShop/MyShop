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
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/public.js"></script>
<script type="text/javascript">
	//文件上传
	function fileUpload() {
		var fileInput = $("input[name='file']");
		if(fileInput.length < 4)
		{
			$.ajaxFileUpload( {
				url : '<%=path%>/fileUploadAction.xhtml', //用于文件上传的服务器端请求地址  
						secureuri : false, //一般设置为false  
						fileElementId : "file", //文件上传空间的id属性  <input type="file" id="file" name="file" />  
						dataType : 'json', //返回值类型 一般设置为json  
						success : function(data, status) {
							var fileNames = data.fileFileName; //返回的文件名 
							var filePaths = data.filePath; //返回的文件地址 
							var last = filePaths.lastIndexOf("\\");
							var fileName = filePaths.substring(last + 1,
									filePaths.length);
							//将上传后的文件 添加到页面中 以进行下载
							$("#down")
									.after(
											"<tr><td height='25'>"
													+ fileNames
													+ "</td><td><a href='downloadFile.xhtml?downloadFilePath="
													+ filePaths
													+ "'>下载</a><input type='hidden' name='filePath' id='filePath' value='/upload/" + fileName +"'/></td></tr>")
						}
					})
		} else {
			alert("最 多只能提交4个文件");
		}
	}

	//处理提示性输入框
	$(document).ready(function() {
		$("#phone").click(function() {
			if ($(this).val() == "一个号码只允许注册一次") {
				$(this).val("");
			}
		});
		$("#phone").blur(function() {
			if ($(this).val() == "") {
				$(this).val("一个号码只允许注册一次");
			}
		});
		$("#sub").click(function() {
			if ($("#phone").val() == "一个号码只允许注册一次") {
				$("#phone").val("");

			}
		}

		);
	});
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

#phone {
	font-style: italic;
	color: gray;
}
</style>
</head>
<body>
	<div style="width: 100%;float: left;">
		<div style="width: 20%;float: right;margin-top: 5px;">
			<a href="<%=path%>/Merch/Merch_toVote.xhtml" style="text-decoration: none;">去投票</a>
		</div>
		<div style="width: 20%;float: right;margin-top: 5px;">
			<a href="<%=path%>/loginindex.xhtml" style="text-decoration:none;">首页</a>
		</div>
	</div>
	<div style="width: 100%;float: left;">
		<div
			style="width: 60%;height:1000px;margin-left: 20%;margin-top: 30px;">
			<h1 align="center" style="color: blue;">请提交您的商铺信息以供展示</h1>
			<hr>
			<form action="<%=path%>/Merch/Merch_Commit.xhtml" id="Merch"
				method="post">
				<table>
					<tr>
						<td><s:textfield label="(公司/工厂/商铺)名称" name="name" id="name"
								size="40" /></td>
					</tr>
					<tr>
						<td><s:textfield label="联系电话" name="phone" id="phone"
								cssClass="phone" size="40" value="一个号码只允许注册一次" /></td>
					</tr>
					<tr>
						<td><s:textfield label="联系地址" name="addr" id="addr" size="40"></s:textfield>
						</td>
					</tr>
					<tr>
						<td><s:textfield label="经营项目" name="project" id="project"
								size="40" /></td>
					</tr>
					<tr>
						<td><label>店铺靓照:</label>
						</td>
						<td><input type="file" name="file" id="file" /><input
							type="button" value="上    传" onclick="fileUpload()" /> <br>
							<span>
								<table id="down">
								</table> </span></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" id="sub"
							value="发               布" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>