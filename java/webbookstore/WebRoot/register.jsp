<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>注册新用户</title>
	</head>
	<body bgcolor="#44ff99">
		<br>
		<center>
			<s:form action="register" method="post">
				<s:textfield name="user.username" label="用户名" required="true"></s:textfield>
				<s:password name="user.password" label="密码" required="true"></s:password>
				<s:textfield name="user.sex" label="性别"></s:textfield>
				<s:textfield name="user.age" label="年龄"></s:textfield>
				<s:submit value="提交" align="center"></s:submit>
			</s:form>
		</center>
	</body>
</html>