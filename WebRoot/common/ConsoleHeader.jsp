<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.user.Userinfo" %>
<%@ page pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js"></script>
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
</script>
 <div  id="consoleId">
 </div>