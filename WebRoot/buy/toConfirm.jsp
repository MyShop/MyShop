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
function selectPro()
{
	var proid =$("#province").val();
	$.getJSON("<%=path%>/buy/provinceToCart.xhtml?",{province : proid},function(data)
	{
		$("#city").empty();
		if(data[0].result == true)
		{
			
			
			$("#city").append('<option value="">'+"请选择市区"+'</option>');
			for(var i = 1;i < data.length;i++)
		    {
			   $("#city").append('<option value='+data[i].id+'>'+data[i].name+'</option>');
		    }
		    $("#city")[0].disabled = false;
		}
		else
		{
		   $("#city").append('<option value="">不包含市</option>');
		   $("#city")[0].disabled = true;
		}
		$("#xian").empty();
		$("#zhen").empty();
  	}
	);
	
}

function selectCity()
{
	cityid = $("#city").val();
	$.getJSON("<%=path%>/buy/cityToCart.xhtml?",{city:cityid},function(data)
		{
			$("#xian").empty();
			if(data[0].result == true)
			{
				$("#xian").append('<option value="">'+"请选择县域"+'</option>');
				for(var i = 1;i < data.length;i++)
		    	{
			   		$("#xian").append('<option value='+data[i].id+'>'+data[i].name+'</option>');
		    	}
		    	$("#xian")[0].disabled = false;
		    	
			}
			else
			{
		   		$("#xian").append('<option value="">不包含县</option>');
		   		$("#xian")[0].disabled = true;
			}
			$("#zhen").empty();
		}
	);
}

function selectXian()
{
	xianid = $("#xian").val();
	$.getJSON("<%=path%>/buy/xianToCart.xhtml?",{xian:xianid},function(data)
		{
			$("#zhen").empty();
			if(data[0].result == true)
			{
				$("#zhen").append('<option value="">'+"请选择乡镇"+'</option>');
				for(var i = 1;i < data.length;i++)
		    	{
			   		$("#zhen").append('<option value='+data[i].id+'>'+data[i].name+'</option>');
		    	}
		    	$("#zhen")[0].disabled = false;
			}
			else
			{
		   		$("#zhen").append('<option value="">不包含镇</option>');
		   		$("#zhen")[0].disabled = true;
			}
		}
	);
}

function submit()
{
	var province =$("#province").val();
	var city = $("#city").val();
	var area = $("#area").val();
	
	if(province == "" || province == null)
	{
		alert("没有填写省份信息！");
		return;
	}
	
	if(city == "" || city == null)
	{
		alert("没有填写市区信息！");
		return;
	}
	
	if(xian == "" || xian == null)
	{
		alert("没有填写县域信息！");
		return;
	}
	
	if(area == "" || area == null)
	{
		alert("没有填写街道信息！");
		return;
	}
	var cartform = $("#cart");
	cartform.submit();
}
</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div  style="width:80%;margin-left: 10%">
	<table  id="cartTable" style="width: 80%">
		<thead>
			<tr>
				<th width="30%" align="center">商品</th>
				<th width="15%" align="center">单价</th>
				<th width="15%" align="center">数量</th>
				<th width="15%" align="center">小计</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="cartList" var="cartOfProd" value="#request.cartList" status="st" >
			<tr>
				<td width="30%" align="center"><input type="hidden" name="id" value="<s:property value='#cartOfProd.prod.id'/>"/><s:property value="#cartOfProd.prod.name"/></td>
				<td width="15%" align="center"><s:property value="#cartOfProd.prod.price"/></td>
				<td width="15%" align="center"><s:property value="#cartOfProd.count"/></td>
				<td width="15%" align="center"><script>document.write(Number('<s:property value="#cartOfProd.prod.price"/>'*<s:property value="#cartOfProd.count"/>));</script></td>
			</tr>
			</s:iterator>
		</tbody>	
	</table>	
</div>
<p>
<p>
<div style="width: 80%;margin-left: 20%;">
<form  action="<%=path %>/buy/commitMyCart.xhtml" id="cart" method="post">
<div>请填写收货地址：</div>
<div>
	<table>
		<tr>
			<td>
				<label>省份</label>
				<select name="province" onchange="selectPro()" id="province">
					<option value="">请选择省份</option>
					<s:iterator value="#request.proList" var="province"   status="st">
						<option value="<s:property value="#province.id"/>"><s:property value="#province.name"/></option>
					</s:iterator>
				</select>
				&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<label>市区</label><select name="city" id="city" onchange="selectCity()">
				<option value="">请选择市区</option>
					<s:iterator value="#request.cityList" var="city"   status="st">
						<option value="<s:property value="#city.id"/>"><s:property value="#city.name"/></option>
					</s:iterator>
				
				</select>
				&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<label>县域</label><select name="xian" id="xian" onchange="selectXian()">
					<option value="">请选择县域</option>
					<s:iterator></s:iterator>
					
				</select>
				&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<label>乡镇</label><select name="zhen" id="zhen">
					<option value="">请选择乡镇</option>
					<s:iterator></s:iterator>
					
				</select>
				&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<label>街道：</label>
				<input name="area" type="text" id="area" size="60"/>
			</td>
		</tr>
		<tr>
			<td colspan="1">收获联系电话:</td>
			<td colspan="3"><input type="text" name="phone" id="phone"></td>	
		</tr>
		<tr>
			<td colspan="1">付款方式</td><td colspan="3"><select name="payType"><option value="0">货到付款</option> </select> </td>
		</tr>
	</table></div>
	</form>
</div>

<div style="width: 60%;margin-left: 10%;" align="right">
	<a onclick="submit()">结算</a>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>