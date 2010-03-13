<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<body>
		<center>
			<h2>
				<font color="blue">web book store</font>
			</h2>
			<a href="index.jsp">首页</a>
			<s:if test="#session.user == null">
				<a href="login.jsp">登陆</a>
			</s:if>
			<s:if test="#session.user != null">
				<a href="logout.action">注销</a>
			</s:if>
			<s:if test="#session.user == null">
				<a href="register.jsp">注册</a>
			</s:if>
			<a href="browseCatalog.action">查询图书分类</a>
			<a href="viewBooksQuery.action">搜索图书</a>
			<a href="viewCart.action">显示购物车</a>
		</center>
		<hr/>
	</body>
</html>
