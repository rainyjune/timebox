<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="head.jsp"></jsp:include>

<html>
	<head>
		<title>登陆页面</title>
	</head>

	<body bgcolor="#55ff99">
		<br>
		<center>
			<s:form action="login" method="post">
				<s:textfield name="user.username" label="用户名" required="true"></s:textfield>
				<s:password name="user.password" label="密码" required="true"></s:password>
				<s:submit value="提交" align="center"></s:submit>
			</s:form>
		</center>
	</body>

</html>