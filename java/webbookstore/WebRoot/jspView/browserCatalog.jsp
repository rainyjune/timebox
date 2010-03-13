<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>查看图书分类列表</title>
	</head>
	<body bgcolor="#ccff99">
		
		<table border="1" align="center" width="30%">
			<tr>
				<td align="center">序号</td>
				<td align="center">图书类名</td>
				<td align="center">查看</td>
			</tr>
			
			<s:iterator value="#request.catalogs" id="catalog">
				<tr>
					<td> <s:property value="#catalog.catalogId"/> </td>
					<td> <s:property value="#catalog.catalogName"/> </td>
					<td> 
						<s:a href="browseBooks.action?catalogId=%{#catalog.catalogId}">查看</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>