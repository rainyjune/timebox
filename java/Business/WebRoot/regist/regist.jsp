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
    
    <title>My JSP 'regist.jsp' starting page</title>
    
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
    <center>
    <font size="+2">注册</font><br>
    <hr>
    <s:fielderror></s:fielderror>
    <s:form action="Regist.action" method="post">
    	<s:textfield name="user.name"label="用户名："></s:textfield>
    	<s:password name="user.password"label="密码："></s:password>
    	<s:textfield name="user.email"label="邮箱："></s:textfield>
    	<s:submit name="submit" label="确定"></s:submit>
    	<s:reset name="reset" label="重填"></s:reset>
    </s:form>
    </center>
  </body>
</html>
