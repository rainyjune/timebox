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
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  		function change()
  		{
  			document.getElementById("img").src="authImg";
  			return false;
  		}
  </script>
  <body>
    <center>
    <font size="+2"><s:text name="login.title"></s:text></font>
    <hr>
      <s:fielderror></s:fielderror>	
    <s:form action="Login.action" method="post">
    	<s:textfield name="username" key="login.username"></s:textfield>
    	<s:password name="password" key="login.password"></s:password>
    	<s:textfield name="rand" key="login.rand"></s:textfield>
		<s:submit name="submit" key="login.submit"/>
		<s:reset name="reset" key="login.reset"/>
    	
    </s:form>
    <img src="authImg" id="img" width="140" height="50">
    <a href="" onClick="return change()">【<s:text name="login.refresh"></s:text>】</a>
  </body>
</html>
