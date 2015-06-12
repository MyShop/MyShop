<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.user.Userinfo"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	Userinfo userinfo = (Userinfo) session.getAttribute("userInfo");
%>

<html>
<head>
<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js" ></script>
<script type="text/javascript" src="<%=path %>/js/public.js"></script>
<script type="text/javascript" src="<%=path %>/js/site.js"></script>
<link href="<%=path %>/css/all.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="<%=path %>/css/main.css">
    <!--[if IE 7]><link href="css/ie7.css" rel="stylesheet" type="text/css" media="all" />
<![endif]-->
    <!--[if IE 6]><link href="css/ie6.css" rel="stylesheet" type="text/css" media="all" />
<![endif]-->

<script type="text/javascript">
$(document).ready(function(){
	
// 	loadMenu();				
//	mainmenu();
    var returnHtml = loadProdType();
    $("#menuItem").append(returnHtml);
    $('.all-sort-list > .item').hover(function(){
			var eq = $('.all-sort-list > .item').index(this),				//获取当前滑过是第几个元素
				h = $('.all-sort-list').offset().top,						//获取当前下拉菜单距离窗口多少像素
				s = $(window).scrollTop(),									//获取游览器滚动了多少高度
				i = $(this).offset().top,									//当前元素滑过距离窗口多少像素
				item = $(this).children('.item-list').height(),				//下拉菜单子类内容容器的高度
				sort = $('.all-sort-list').height();						//父类分类列表容器的高度
			
			if ( item < sort ){												//如果子类的高度小于父类的高度
				if ( eq == 0 ){
					$(this).children('.item-list').css('top', (i-h));
				} else {
					$(this).children('.item-list').css('top', (i-h)+1);
				}
			} else {
				if ( s > h ) {												//判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度
					if ( i-s > 0 ){											//则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,
						$(this).children('.item-list').css('top', (s-h)+2 );
					} else {
						$(this).children('.item-list').css('top', (s-h)-(-(i-s))+2 );
					}
				} else {
					$(this).children('.item-list').css('top', 3 );
				}
			}	

			$(this).addClass('hover');
			$(this).children('.item-list').css('display','block');
		},function(){
			$(this).removeClass('hover');
			$(this).children('.item-list').css('display','none');
		});

		$('.item > .item-list > .close').click(function(){
			$(this).parent().parent().removeClass('hover');
			$(this).parent().hide();
		});
});

function getProductById(id)
{
	window.location.href = "<%=path%>/Product/getProdByType.xhtml?type="+id;
}
</script>
</head>
<body>
<div id="header" style="margin-top:0px;height: 200px;">
	<div class="top">
		<div style="display: inline;width: 100%;">
			<div style="width: 15%;float: right; display: inline;"><a href="<%=path%>/userOrderList.xhtml">我已购买的</a></div>
			<div id="infor" style="width: 15%;height: 100px;float: right;display: inline;"  align="left"><a href="<%=path%>/userInfo.xhtml"><%if (userinfo != null) {%><%=userinfo.getName()%><%}else{ %>尊贵的新客户<%} %></a></div>
			<div id="index" style="float: right;width: 10%;display: inline;"><a href="<%=path%>/loginindex.xhtml">首页</a></div>
		</div>
	</div>
	<div class="menu">
		<div class="all-sort"><h2><a href="">全部商品分类</a></h2></div>
		<div class="ad">
			<a href="">
				<img src="http://img12.360buyimg.com/da/g14/M08/12/0D/rBEhVlJEA84IAAAAAAAZY9Mm2-UAADmFQNaVy4AABl7123.jpg" width="141" height="40" />
			</a>
		</div>
		<div class="nav">
			<ul class="clearfix">
				<li><a href="" class="current">首页</a></li>
				<li><a href="">服装城</a></li>
				<li><a href="">京东超市</a></li>
				<li><a href="">团购</a></li>
				<li><a href="">拍卖</a></li>
				<li><a href="">在线游戏</a></li>
			</ul>
		</div>
	</div>
	<div class="wrap">
		<div class="all-sort-list" id="menuItem">
		
		</div>
	
	</div>
	
</div>
<script type="text/javascript">
		
	</script>
</body>
</html>