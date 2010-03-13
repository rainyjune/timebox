<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Love Web</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <hr>
		<center><font size="+2" color="red">||<a href="login/login.jsp">登录</a>|<a href="regist/regist.jsp">注册</a>|<a href="result_goods.jsp">用户消费情况查询</a>||</font>
		<form action="search.jsp" method="post" target="blank">
			<input type="text" name="key">
			<input type="submit" name="submit" value="搜索">
		</form>
	<hr>
	<hr>
		<center>活动<br>
		<s:include value="action.jsp"></s:include>
	<hr>
	<hr>
		<center>广告商<br>
		<s:include value="ad.jsp"></s:include>
	<hr>
	
  </body>
</html>
