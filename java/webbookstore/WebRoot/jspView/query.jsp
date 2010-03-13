<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>搜索图书</title>
	</head>
	
	<body bgcolor="#11ff99">
		<center>
		<s:form action="queryBooks" method="post" >
			<s:textfield name="bookname" label="书名"></s:textfield>
			<s:submit value="搜索"></s:submit>
		</s:form>
		</center>
	</body>
</html>