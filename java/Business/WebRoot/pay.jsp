<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="bean.Goods;"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pay.jsp' starting page</title>
    
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
  
    	<%
		if(session.getAttribute("id")==null)
		{
	%>
		<jsp:forward page="index.jsp"></jsp:forward>
	<%		
		}
	%>
  
  <center>
    清单结算 <br>
    <hr>
    
          	<table>
  		 <tr>
    		<td>名称</td>
    		<td>价格</td>
    		<td>介绍</td>
    		<td>最后期限</td>
    		<td>购买数量</td>
    		<td></td>
    		<td>删除</td>
    	</tr>
	    	<%
	    	double price=0;
	    	List<Integer> goods_id=(ArrayList)session.getAttribute("basket");
	    	if(goods_id!=null)
	    	{
	    	

	    	List<Goods> goods_list;
	    	int id;
	    	for(int i=0;i<goods_id.size();i++)
	    	{
	    		id=(int)goods_id.get(i);
	    		goods_list=goodsm.getAll(id);
	    		
	    		for(int j=0;j<goods_list.size();j++)
    			{
    				goods=(Goods)goods_list.get(j);
    				price+=goods.getPrice();
    		%>
    				<tr>
    				<td><%=goods.getGoods_name()%></td>
    				<td><%=goods.getPrice()%></td>
    				<td><%=goods.getText()%></td>
    				<td><%=goods.getEndtime()%></td>
    				<td>1</td>
    				<form action="OutBasket.action" method="post">
    				<td><input type="hidden" name="goods_id"  value=<%=goods.getGoods_id()%> ></td>
    				<td><input type="submit" value="踢出购物篮" name="submit"></td>
    				</form>
    				</tr>
    		<%
    			}
	    	}
	    }
    	 %>
    </table>
    <hr>
    总价钱：<%=price %>『<a href="AddResult.action">付款</a>』||『<a href="search.jsp">---继续购物---</a>』||『<a href="main.jsp">返回</a>』
  </body>
</html>
