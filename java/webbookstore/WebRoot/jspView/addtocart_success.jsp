<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>放入购物车成功</title>
	</head>

	<body bgcolor="#ccff99">
		<center>
			<font color="red">添加成功！</font><br/><br/>
			<s:a href="browseBooks.action?catalogId=%{#session.catalogId}">继续购物</s:a>
			<s:a href="viewCart.action">显示购物车</s:a>
			<s:a href="checkout.action">结帐</s:a>
		</center>
	</body>
</html>