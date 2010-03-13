<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>结帐成功</title>
	</head>

	<body bgcolor="#ccff99">
		<center>
			<font color="red">成功生成订单！</font><br/><br/>
			<s:property value="#session.user.username"/>，您的订单号是
			<s:property value="#request.order.orderId"/>，您的物品将会在三日内送达！<br/><br/>
			<s:a href="browseCatalog.action">继续购物</s:a>
			<s:a href="logout.action">退出登录</s:a>
		</center>
	</body>
</html>