<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ibs.hibernate.bean.user.Userinfo"%>
<%@ page import="com.ibs.hibernate.bean.place.Province"%>
<%@ page import="java.util.Map"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
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
<%
	Userinfo userinfo = (Userinfo) session.getAttribute("userInfo");
%>
<title>欢迎您，<%=userinfo.getName()%></title>
<script type="text/javascript" src="/IBS/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/IBS/js/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="/IBS/js/public.js"></script>
<style type="text/css">
#main {
	width: 80%;
	margin-left: 10%;
}

#top {
	weidth: 80%;
	height: 120px;
}

#menu {
	height: 30px;
	display: block;
}

#content {
	width: 100%;
	height: 400px;
	display: block;
	height: 400px;
}

#left-content {
	height: 400px;
	width: 20%;
	float: left;
}

#right-content {
	height: 400px;
	width: 20%;
	float: left;
}
.tdLabel
{
	width:1px;
}
.errorMessage {
	font-family: "宋体";
	font-size: 13px;
	font-style: normal;
	color: #990000;
}
</style>
<script type="text/javascript">
	function over(obj) {

		$(obj).attr("bgcolor", "#C4C4FF");
	}
	function out(obj) {
		$(obj).attr("bgcolor", "#FFFFFF");
	}

	
	function deleteProvince() 
	{
		$.getJSON("<%=basePath%>delPro.xhtml", {
			"pro.id" : $("#provinceId")[0].value,
		}, function(json) {
			if(json[0].message == "success")
			{
				alert("删除省份成功");
				var x = $("#provinceId")[0];
				x.remove(x.selectedIndex);
			}
			else if(json[0].message == "false")
			{
				alert("删除省份失败");
			}

		});
	}
	
	function subCity()
	{
		
	 $.post(action,{proName:$("proName")[0].value,"city.name":$("cityName")[0].value},
	function(json)
	 {
	 	var array = new Array();
       	  if(json != null && json.length>0)
       	  {
       	  	for(var i=0;i<json.length;i++)
          	{
          		var o = new Object();
          		o.filedName = json[i].filedName;
          		o.errorMessage = json[i].errorMessage;
          		array[i] = o;
          	}
       	  }
          //clear errorMessage
          $("span").hide();
         
          if(array.length>0)
          {
          	for(var i=0;i<array.length;i++)
          	{
          		$("#"+array[i].filedName.replace(".","")+"_error")[0].innerHTML=array[i].errorMessage;
          		$("#"+array[i].filedName.replace(".","")+"_error")[0].style.display="block";
          	}
          }
          else
          {
        	  $("#"+formId).submit();
          }
	 },"json");
	}
</script>
</head>

<body>
	<div id="main">
		<div id="top"></div>
		<div id="menu">
			<table>
				<tr>
					<td width="80px" align="center"><a
						href="<%=basePath%>place.xhtml">地区编辑</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="content">
			<div id="left-content" style="width:20%;"></div>
			<div id="right-content" style="width:80%;">
				<table style="width:480px;float: left;">
					<tr>
						<td style="width: 160px;">
							<table style="width: 160px;">
								<tr>
									<td width="1px"></td>
									<td style="width: 160px;"><label style="font-size:16px;font-weight:bold;width: 100px;display:inline-block; ">省</label></td>
								</tr>
								<tr>
									<td width="1px"></td>
									<td style="width: 160px;">
										<struts:select name="provinceId" id="provinceId" size="10"
										list="provinceList" listKey="id" listValue="name"
										cssStyle="width:100px">
										</struts:select>
									</td>
								</tr>
								<tr>
									<td width="1px"></td>
									<td style="width: 160px;">
									<div id="province"
										style="width: 160px;height:20px;display:inline-block;margin-top:5px">
										<table style="border: thin solid #000099;">
											<tr onmouseover="over(this);" onmouseout="out(this);">
												<td align="center" style="width:82px;font-size:10px"><button>增加新省</button>
												</td>
											</tr>
											<tr onmouseover="over(this);" onmouseout="out(this);">
												<td align="center"
												style=" border-top:thin dashed #000099;font-size:10px"><button
												onclick="deleteProvince()">删除该省</button>
												</td>
											</tr>
										</table>
									</div>
									</td>
							</tr>
							</table>
						</td>
						<td style="width: 160px;">
						    <table style="width: 160px;">
						    	<tr>
						    		<td width="1px"></td>
						    		<td style="width: 160px;"><label style="font-size:16px;font-weight:bold;width: 100px;display:inline-block; ">市</label></td>
						    	</tr>
						    	<tr>
						    		<td width="1px"></td>
						    		<td style="width: 160px;"><struts:select name="cityId" id="cityId" size="10"
								list="cityList" listKey="id" listValue="name"
								cssStyle="width:100px"></struts:select></td>
						    	</tr>
						    	<tr>
						    		<td width="1px"></td>
						    		<td style="width: 160px;"><div id="city"
								style="width: 160px;height:20px;display:inline-block;margin-top:5px">
								<table style="border: thin solid #000099;">
									<tr onmouseover="over(this);" onmouseout="out(this);">
										<td align="center" style="width:82px;font-size:10px"><button>增加新市</button>
										</td>
									</tr>
									<tr onmouseover="over(this);" onmouseout="out(this);">
										<td align="center"
										style=" border-top:thin dashed #000099;font-size:10px"><button
											onclick="deleteProvince()">删除该市</button>
										</td>
									</tr>
								</table>
							</div></td>
						    	</tr>
						    </table>
						</td>
						<td style="width: 160px;">
							<table style="width: 160px;">
								<tr>
									<td width="1px"></td>
									<td style="width: 160px;"><label style="font-size:16px;font-weight:bold;width: 100px;display:inline-block; ">县</label></td>
								</tr>
								<tr>
									<td width="1px"></td>
									<td style="width: 160px;"><struts:select name="cityId" id="cityId" size="10"
								list="cityList" listKey="id" listValue="name"
								cssStyle="width:100px"></struts:select></td>
								</tr>
								<tr>
									<td width="1px"></td>
									<td style="width: 160px;">
									<div id="city" style="width: 160px;height:20px;display:inline-block;margin-top:5px">
								<table style="border: thin solid #000099;">
									<tr onmouseover="over(this);" onmouseout="out(this);">
										<td align="center" style="width:82px;font-size:10px"><button>增加新县</button>
										</td>
									</tr>
									<tr onmouseover="over(this);" onmouseout="out(this);">
										<td align="center"
											style=" border-top:thin dashed #000099;font-size:10px"><button
											onclick="deleteProvince()">删除该县</button>
										</td>
									</tr>
								</table>
							</div></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" style="position: relative;top: 50px;">
						<tr>
							<td style="background-color:rgb(194,211,252);height: 28px;"><label style="font-size:16px;font-weight:bold;">添加信息</label></td>
						</tr>
						<tr >
							<td>
								<form action="" name="addPro" id="addPro">
								<table style="width:100%;border:1px solid rgb(117,109,252);">
									<tr style="font-size: 12px;">
										<td width="10%"><label>省名</label></td>
										<td width="50%"><input name="pro.name" id="proName" type="text" />
										<span id="proname_error" class="errorMessage" style="display: none"></span></td>
										<td width="40%"><input type="button" value="提交" onclick="submitForm('addPro');" /></td>
									</tr>
									<tr style="font-size: 12px;">
										<td width="10%"><label>市名</label></td>
										<td width="50%">
										<input name="city.name"  id="cityName"type="text"/>
										<span id="cityname_error" class="errorMessage" style="display: none"></span></td>
										<td width="40%"><input type="button" value="提交" onclick="subCity('addValidateCity.xhml','addCity')" /></td>
									</tr>
									<tr style="font-size: 12px;">
										<td width="10%"><label>县名</label></td>
										<td width="50%"><input name="coutr.name"  type="text"/>
										<span id="cityname_error" class="errorMessage" style="display: none"></span>
										<td width="40%"><input type="button" value="提交"/></td>
									</tr>
								</table>
								</form>
							</td>
						</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
