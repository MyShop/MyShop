<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.user.Userinfo"%>
<%@ page pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	Userinfo userinfo = (Userinfo) session.getAttribute("userInfo");
%>


<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js" ></script>
<script type="text/javascript" src="<%=path %>/js/public.js"></script>
<script type="text/javascript" src="<%=path %>/js/site.js"></script>
<link href="<%=path %>/css/all.css" rel="stylesheet" type="text/css" media="all" />
    <!--[if IE 7]><link href="css/ie7.css" rel="stylesheet" type="text/css" media="all" />
<![endif]-->
    <!--[if IE 6]><link href="css/ie6.css" rel="stylesheet" type="text/css" media="all" />
<![endif]-->

<script type="text/javascript">
$(document).ready(function(){	
	loadMenu();				
	mainmenu();
});
</script>

<div id="header" style="margin-top:0px;;height: 200px;">
	<div id="menu" style="padding-top:179px;margin-left: 20%;height:21px;"></div>
</div>