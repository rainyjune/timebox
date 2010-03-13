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
    
    <title>My JSP 'addgoods.jsp' starting page</title>
    
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
  <s:fielderror></s:fielderror>
  <table bgcolor="#c0c0c0" width="800">
  	<tr><td>
  	<s:form action="AddAd.action" method="post" enctype="multipart/form-data">
 		<s:textfield name="ad_company" label="赞助商名称"></s:textfield>
 		<s:textarea name="ad_text" label="赞助商说明" rows="5" cols="50"></s:textarea>
 		<s:file name="upload" label="赞助商商标®"></s:file>
 		<s:submit label="确定" align="center"></s:submit>
	</s:form>
	</td></tr>
 </table>
 </body>
</html>
