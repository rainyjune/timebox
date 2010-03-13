<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>查看图书分类列表</title>
	</head>
	<body bgcolor="#66ff99">
		
		<table  align="center" width="50%">
			<tr align="center">
				<td>编号</td>
				<td>图书类名</td>
				<td>书名</td>
				<td>价格</td>
				<td>图片</td>
				<td>操作</td>
			</tr>
			
			<s:iterator value="#request.books" id="book">
				<tr align="center">
					<td> 
						<s:property value="#book.bookId"/>
					</td>
					<td> 
						<s:property value="#book.catalog.catalogName"/>
					</td>
					<td> 
						<s:property value="#book.bookName"/>
					</td>
					<td> 
						<s:property value="#book.price"/>
					</td>
					<td> 
						<img src="picture/<s:property value="#book.picture"/>" alt="aa"/>
					</td>
					<td>
						<s:form action="addToCart" method="post">
							数量:<input type="text" name="quantity" value="0" size="4"/>
							<input type="hidden" name="bookId" value="<s:property value="#book.bookId"/>">
							<input type="submit" value="放入购物车"/>
						</s:form>
					</td>
				</tr>
			</s:iterator>
		</table>
		
	</body>
</html>