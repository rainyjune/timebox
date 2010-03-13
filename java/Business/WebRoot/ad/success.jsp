<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="dbManage.AdDBManager"%>
<%@page import="bean.Ad"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 	<jsp:useBean id="adm" class="dbManage.AdDBManager"></jsp:useBean>
	<jsp:useBean id="ad" class="bean.Ad"></jsp:useBean> 
  <body>
    <center>
    	添加成功 <br>
    	<hr>


  	<table>
  		<tr>
    	<%
    		List<Ad> l=adm.getAll();
    		for(int i=0;i<l.size();i++)
    		{
    			if((i)%5==0)
    			{
    				out.print("</tr><tr>");
    			}
    			ad=(Ad)l.get(i);
    			%>
    			<td>
    				<table>
    					<tr><td><img src=<%=ad.getImg()%> height=100 width=180 alt=<%=ad.getText()%>></td></tr>
    					<tr><td align=center><%=ad.getCompany() %></td></tr>
    				</table>
    			</td>
    			<%
    		}
    	 %>
    	 </tr>
    </table> 
    <a href="javascript:window.close()">【关闭】</a>	  
  </body>
</html>
