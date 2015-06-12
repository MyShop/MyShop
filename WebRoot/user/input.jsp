<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page  import="com.ibs.action.user.userInfoAction"%>
<%@page import="com.opensymphony.xwork2.ActionContext" %>
<%@page import="com.ibs.hibernate.bean.place.City" %>
<%@page import="com.ibs.hibernate.bean.place.Province"%>
<%@page import="com.ibs.hibernate.bean.place.Xian"%>
<%@page import="com.ibs.hibernate.bean.place.Zhen"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%

//处理校验不通过数据丢失问题
String proId = ((userInfoAction)ActionContext.getContext().getValueStack().peek()).getProvince();
String cityId = ((userInfoAction)ActionContext.getContext().getValueStack().peek()).getCity();
String xianId = ((userInfoAction)ActionContext.getContext().getValueStack().peek()).getXian();
String zhenId = ((userInfoAction)ActionContext.getContext().getValueStack().peek()).getZhen();
List<City> jspcityList = new ArrayList<City>();
List<Province> jspproList = new ArrayList<Province>();
List<Xian> jspxianList = new ArrayList<Xian>();
List<Zhen> jspzhenList = new ArrayList<Zhen>();
if(request.getAttribute("cityList") == null)
{
	 jspcityList =((userInfoAction)ActionContext.getContext().getValueStack().peek()).ValidateCityList();
}
else
{
	jspcityList = (ArrayList<City>)request.getAttribute("cityList");//如果rquest有数据，就从request获取数据
}
if(request.getAttribute("proList") == null)
{
	jspproList =((userInfoAction)ActionContext.getContext().getValueStack().peek()).ValidateProList();
}
else
{
	jspproList = (ArrayList<Province>)request.getAttribute("proList");//如果rquest有数据，就从request获取数据
}
if(request.getAttribute("xianList") == null)
{
	jspxianList =((userInfoAction)ActionContext.getContext().getValueStack().peek()).ValidateXianList();
}
else
{
	jspxianList = (ArrayList<Xian>)request.getAttribute("xianList");//如果rquest有数据，就从request获取数据
}
if(request.getAttribute("zhenList") == null)
{
	jspzhenList =((userInfoAction)ActionContext.getContext().getValueStack().peek()).ValidateZhenList();
}
else
{
	jspzhenList = (ArrayList<Zhen>)request.getAttribute("zhenList");
}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'input.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.js" ></script>
<script type="text/javascript">
function selectPro()
{
	var proid =$("#province").val();
	$.getJSON("<%=path%>/userInfoGetCityList.xhtml?",{province : proid},function(data)
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
	$.getJSON("<%=path%>/userInfoGetXianList.xhtml?",{city:cityid},function(data)
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
	$.getJSON("<%=path%>/userInfoGetZhenList.xhtml?",{xian:xianid},function(data)
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
</script>
</head>
<body>
<s:form action="userInfoUpdate.xhtml" method="post">
	<table>
		<tr>
			<td><s:textfield name="name" id="name" label="用户名"></s:textfield>
			</td>
		</tr>
		<tr>
			<td><s:textfield name="phone" id="phone" label="电话号码"></s:textfield>
			</td>
		</tr>
		<tr>
				<s:if test="proList !=null">
					<s:select list="proList"  name="province" id="province" label="省"   onchange="selectPro()" listKey="id" listValue="name" headerValue="请选择省份"  cssStyle="width:140px;">
						<%out.println("<option value=''>"+"请选择省份"+"</option>");%>
					
					</s:select>
				</s:if>
				<s:else>
					<td class="tdLabel"><label for="province" class="label">省:</label></td>
					<td>
					<select name="province" id="province" style="width: 140px;">
						<%
						out.println("<option value=''>"+"请选择省份"+"</option>");
						Iterator it =jspproList.iterator();
						 while(it.hasNext())
						 {
						 	Province pro = (Province)it.next();
						 	if(proId.equals(pro.getId().toString()))
						 	{
						 		//确定选项被选中
						 		out.println("<option selected='selected' value='"+pro.getId()+"'>"+pro.getName()+"</option>");
						 	}
						 	else
						 	{
						 		out.println("<option value='"+pro.getId()+"'>"+pro.getName()+"</option>");
						 	}
						 	
						 }
						 %>					
					</select>
					</td>
				</s:else>
		</tr>
		<tr>
				<s:if test="cityList !=null">
				
					<s:select list="cityList" name="city" id="city" label="市" listKey="id" listValue="name" onchange="selectCity()" headerValue="请选择省份" cssStyle="width:140px;" ></s:select>
				</s:if>
				<s:else>
				<td class="tdLabel"><label for="province" class="label">市:</label></td>
				<td>
					<select name="city" id="city" style="width: 140px;">
						<%Iterator it =jspcityList.iterator();
						 while(it.hasNext())
						 {
						 	City city = (City)it.next();
						 	if(cityId.equals(city.getId().toString()))
						 	{
						 		out.println("<option selected='selected' value='"+city.getId()+"'>"+city.getName()+"</option>");
						 	}
						 	else
						 	{
						 		out.println("<option value='"+city.getId()+"'>"+city.getName()+"</option>");
						 	}
						 							 }
						 %>					
					</select>
				</td>
				</s:else>
		</tr>
		<tr>
				<s:if test="xianList !=null">
					<s:select list="xianList" name="xian" id="xian" label="县" listKey="id" listValue="name" onchange="selectXian()" headerValue="请选择省份" cssStyle="width:140px;"></s:select>
				</s:if>
				<s:else>
				<td class="tdLabel"><label for="xian" class="label">县:</label></td>
				<td>
					<select name="xian" id="xian" style="width: 140px;">
						<%Iterator it =jspxianList.iterator();
						 while(it.hasNext())
						 {
						 	Xian xian = (Xian)it.next();
						 	if(xianId.equals(xian.getId().toString()))
						 	{
						 		out.println("<option selected='selected' value='"+xian.getId()+"'>"+xian.getName()+"</option>");
						 	}
						 	else
						 	{
						 		out.println("<option value='"+xian.getId()+"'>"+xian.getName()+"</option>");
						 	}
						 	
						 }
						 %>					
					</select>
				</td>
				</s:else>
		</tr>
		<tr>
				<s:if test="zhenList !=null">
					<s:select list="zhenList" name="zhen" id="zhen" label="乡镇" listKey="id" listValue="name"  headerValue="请选择省份" cssStyle="width:140px;"></s:select>
				</s:if>
				<s:else>
				<td class="tdLabel"><label for="province" class="label">乡镇</label></td>
				<td>
					<select name="zhen" id="zhen" style="width: 140px;">
						<%Iterator it =jspzhenList.iterator();
						 while(it.hasNext())
						 {
						 	Zhen zhen = (Zhen)it.next();
						 	if(zhenId.equals(zhen.getId().toString()))
						 	{
						 		out.println("<option selected ='selected' value='"+zhen.getId()+"'>"+zhen.getName()+"</option>");
						 	}
						 	else
						 	{
						 		out.println("<option value='"+zhen.getId()+"'>"+zhen.getName()+"</option>");
						 	}
						 	
						 }
						 %>					
					</select>
				</td>		
				</s:else>
		</tr>
		<tr>
			<td colspan="2" align="center"><button  type="submit">提交资料</button> </td>
		</tr>
	</table>

</s:form>
</body>
</html>
