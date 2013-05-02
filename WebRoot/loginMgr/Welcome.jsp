<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Welcome.jsp' starting page</title>
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
			
			
</script>
</head>
<body>
	<div style="width: 20%;margin-top: 200px;">
		<jsp:include page="../common/ConsoleHeader.jsp"></jsp:include>
	</div>
</body>
</html>
