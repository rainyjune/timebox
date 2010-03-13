<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="dbManage.GoodsDBManager"%>
<%@page import="bean.Goods"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'search.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:useBean id="goodsm" class="dbManage.GoodsDBManager"></jsp:useBean>
	<jsp:useBean id="goods" class="bean.Goods"></jsp:useBean>
  </head>
  
  <body>
         <center>
        		 <%
    			if(session.getAttribute("id")!=null)
    			{
    		%>
    				<a href="main.jsp">【返回】</a>
    		<% 
    		   	}
    		 %>

    <font size="+2">搜索结果</font><br>
    <% 
    	String key=request.getParameter("key");
    	if(key==null)
    	{
    		key="";
    	}
    %>
    <hr>
    
      	<table bgcolor="#c0c0c0" width="800">
  		 <tr>
    		<td>名称</td>
    		<td>价格</td>
    		<td>介绍</td>
    		<td>最后期限</td>
    		<td>购买数量</td>
    	</tr>
	    	<%
    		List<Goods> l=goodsm.getAllLike(key);
    		for(int i=0;i<l.size();i++)
    		{
    			goods=(Goods)l.get(i);
    			%>
    				<tr>
    				<td><%=goods.getGoods_name()%></td>
    				<td><%=goods.getPrice()%></td>
    				<td><%=goods.getText()%></td>
    				<td><%=goods.getEndtime()%></td>
    		<form action="AddBasket.action" method="post">
    				<td><select name="goods_count">
    				  		<option value="1">1</option>
    				  		<option value="2">2</option>
    				 		<option value="3">3</option>
    				  		<option value="4">4</option>
    				 		<option value="5">5</option>
    				 		<option value="6">6</option>
    				  		<option value="7">7</option>
    				  		<option value="8">8</option>
    				  		<option value="9">9</option>
    					</select></td>
    				<td><input type="hidden" name="goods_id"  value=<%=goods.getGoods_id()%> ></td>
    		<%
    			if(session.getAttribute("id")!=null)
    			{
    		%>
    				<td><input type="submit" value="加入购物篮" name="submit"></td>
    		<% 
    		   	}else
    		   	{
    		 %>
    		 	<td>登录系统方可购物</td>
    		 <%
    		   	}
    		%>
			</form>
    				</tr>
    			<%
    		}
    	 %>
    </table>
  </body>
</html>
