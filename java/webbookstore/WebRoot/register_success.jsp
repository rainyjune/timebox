<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>注册成功</title>
	</head>
	<body bgcolor="#ccff99">
		<center>
			你好，<s:property value="user.username" />
			， 欢迎你注册成为 web book store 的网络用户！
			<br>
			<a href="login.jsp">登陆吗？</a>
		</center>
	</body>
</html>