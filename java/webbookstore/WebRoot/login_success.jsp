<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>注册成功</title>
	</head>
	<body bgcolor="#33ff99">
		<center>
			<h2>
				<font color="blue">
					<s:property value="user.username" />，您登陆成功了！<br>
				</font>
			</h2>
				<a href="browseCatalog.action">浏览图书分类</a>
		</center>
	</body>
</html>
