<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="dbManage.UsersDBManager"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 <jsp:useBean id="userM" class="dbManage.UsersDBManager"></jsp:useBean>
  <body>
  	<%
  		Object id=session.getAttribute("id");
		if(id==null)
		{
	%>
		<jsp:forward page="index.jsp"></jsp:forward>
	<%		
		}
		String username=userM.getUsername((Integer)id);
	%>
        <center>
    	<font size="+2">【<font color="red"><%=username %></font>】欢迎光临</font>
    	<form action="search.jsp" method="post">
			<input type="text" name="key">
			<input type="submit" name="submit" value="搜索">
		</form>
    <hr>
    <a href="result_goods.jsp?id=${sessionScope.id}">消费情况查询</a>|||
    <a href=goods/add.jsp>拍卖商品</a>|||
    <a href="search.jsp">购买商品</a>|||
    <a href="pay.jsp">查看购物篮</a>|||
    <a href="ad/addAd.jsp" target="blank">添加赞助商</a>|||
    <a href="out.jsp">注销</a>
    <hr>
  </body>
</html>
