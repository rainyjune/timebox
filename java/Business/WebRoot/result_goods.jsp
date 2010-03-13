<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="dbManage.ResultDBManager"%>
<%@page import="bean.ResultGoods"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'action.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:useBean id="rgm" class="dbManage.ResultDBManager"></jsp:useBean>
	<jsp:useBean id="rg" class="bean.ResultGoods"></jsp:useBean>
  </head>
  
  <body>
  <center>
      <font size="+2">你消费的产品</font><br>
      <a href="javascript:window.history.back(-1)">【返回】</a>
  <hr>
  <%
  	String id=(String)request.getParameter("id");
  	double price=0.0;
   %>
  	<table bgcolor="#c0c0c0" width="800">
  		 <tr>
    		<td>用户</td>
    		<td>消费的商品</td>
    		<td>商品价格</td>
    		<td>消费日期</td>
    	</tr>
	    	<%
	    	List<ResultGoods> l;
  			if(id!=null)
  			{
  				l=rgm.getAll(Integer.parseInt(id));
  			}
  			else
  			{
  				l=rgm.getAll(0);
  			}
    		for(int i=0;i<l.size();i++)
    		{
    			rg=(ResultGoods)l.get(i);
    			price+=rg.getPrice();
    			%>
    				<tr>
    				<td><%=rg.getUsername()%></td>
    				<td><%=rg.getGoods_name()%></td>
    				<td><%=rg.getPrice()%></td>
    				<td><%=rg.getCreate_time()%></td>
    				</tr>
    			<%
    		}
    	 %>
    </table>

    <% 
  		if(id!=null)
  		{
  	%>
  	   	 	<img src="Chart.action" width="800" height="900">
   			 <hr>
  	 		<font color="red" size="+1">商品总价：<%=price %></font>
  	<%		
  		} 
    %>
    
  </body>
</html>
