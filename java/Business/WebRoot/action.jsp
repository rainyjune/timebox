<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="dbManage.ActionDBManager"%>
<%@page import="bean.Action"%>
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
	<jsp:useBean id="actionm" class="dbManage.ActionDBManager"></jsp:useBean>
	<jsp:useBean id="action" class="bean.Action"></jsp:useBean>
  </head>
  
  <body>
  	<table bgcolor="#c0c0c0" width="800">
  		 <tr>
    		<td>活动内容</td>
    		<td>活动最后期限</td>
    	</tr>
	    	<%
    		List<Action> l=actionm.getAll();
    		for(int i=0;i<l.size();i++)
    		{
    			action=(bean.Action)l.get(i);
    			%>
    				<tr>
    				<td><%=action.getText()%></td>
    				<td><%=action.getEndtime()%></td>
    				</tr>
    			<%
    		}
    	 %>
    </table>
  </body>
</html>
