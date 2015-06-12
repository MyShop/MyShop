<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.product.Product" %>
<%@ page import="com.ibs.hibernate.bean.buy.cartOfProd" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style type="text/css">
a{cursor: pointer;}

</style>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
 %>
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.js" ></script>
<script type="text/javascript">
function addToCart()
{
	window.location.href ="<%=path%>/buy/";
}

function del(obj)
{
	var p = $($(obj).parent()).parent();
	var id = Number($($(p).children()[0]).children("input[name='id']")[0].value);
	$.getJSON("<%=path%>/buy/delFromCart.xhtml?id="+id,
		function(data)
	     {
	     	if(data[0].result == "success")
	        $(p).remove();
	     }
	);
}

function addToCart(obj)
{
	var p = $($(obj).parent()).parent();
	var id = Number($($(p).children()[0]).children("input[name='id']")[0].value);
	var count = Number($($(p).children()[3]).children("input[name='count']")[0].value);//获取产品数
	var price = Number($(p).children()[2].innerHTML);//获取单价
	count += 1;//产品数+1
	$(p).children()[4].innerHTML = count * price;
	var countInput = $($(p).children()[3]).children("input[name='count']")[0];
	
	$.getJSON("<%=path%>/buy/addOneToCart.xhtml?id="+id,
		function(data)
		{
			if(data[0].result == "success")
			countInput.value = count;
		}
	);
	
	var reduce = ($($(obj).prev()).prev())[0];
	if(count > 1)
	reduce.innerHTML = "-";
}

function submit()
{
	var trEl = $("tr");
	if(trEl.length>1)
	{
		window.location.href = "<%=path%>/buy/toAccountCart.xhtml";
	}
	else
	{
		alert("您的购物车已为空！");
	}
}

</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div  style="width:80%;margin-left: 10%">
	<table  id="cartTable" style="width: 80%">
		<thead>
			<tr>
				<th width="10%"align="left"><input  class="check-all check"  type="checkbox"/>&nbsp;全选</th>
				<th width="30%" align="center">商品</th>
				<th width="15%" align="center">单价</th>
				<th width="15%" align="center">数量</th>
				<th width="15%" align="center">小计</th>
				<th width="15%" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="cartList" var="cartOfProd" value="#request.cartList" status="st" >
			<tr>
				<td width="10%" align="left"><input type="hidden" name="id" value="<s:property value='#cartOfProd.prod.id'/>"/><input class="" type="checkbox"/></td>
				<td width="30%" align="center"><s:property value="#cartOfProd.prod.name"/></td>
				<td width="15%" align="center"><s:property value="#cartOfProd.prod.price"/></td>
				<td width="15%" align="center"><span class="reduce" ></span>&nbsp;&nbsp;&nbsp; <input name="count" type="text" style="height: 24px;width: 20" value="<s:property value="#cartOfProd.count"/> "/>&nbsp;&nbsp;&nbsp;<span class="add" onclick="addToCart(this)" style="display:inline-block; ">+</span> </td>
				<td width="15%" align="center"><script>document.write(Number('<s:property value="#cartOfProd.prod.price"/>'*<s:property value="#cartOfProd.count"/>));</script></td>
				<td width="15%" align="center"><span class="delete" onclick="del(this)">删除</span></td>
			</tr>
			</s:iterator>
		</tbody>	
	</table>	
</div>
<div>
	<div style="width: 60%;margin-left: 10%;" align="right">
	
	<a onclick="submit()">结算</a></div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>