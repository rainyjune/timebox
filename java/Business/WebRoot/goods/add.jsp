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
  
  <body> <center>
      	<font size="+2">欢迎添加商品</font>
    <hr>
  <table bgcolor="#c0c0c0" width="800">
  	<tr><td>
  	<s:form action="AddGoods.action" method="post">
 		<s:textfield name="goods_name" label="商品名称"></s:textfield>
 		<s:textfield name="goods_price" label="商品价格"></s:textfield>
 		<s:textfield name="goods_endtime" label="截止日期【YYYY-MM-DD】"></s:textfield>
 		<s:textarea name="goods_text" label="商品说明" rows="5" cols="50"></s:textarea>
 		<s:submit label="确定" align="center"></s:submit>
	</s:form>
	</td></tr>
 </table>
 </body>
</html>
