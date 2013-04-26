<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<div style="pomargin-top:0px;margin-left: 20%;float: left;width: 80%">
	<s:iterator id="productList" var="product" value="#request.productList" status="st" >
	    <s:div cssStyle="margin-left:4%;width:20%;float:left;">
	    	<img src="" width="90px" height="120" />
	    <s:property value="#product.name"/></s:div>
	</s:iterator>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>