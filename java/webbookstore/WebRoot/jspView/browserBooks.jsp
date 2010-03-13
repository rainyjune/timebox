<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="head.jsp"></jsp:include>
<html>
	<head>
		<title>查看图书分类列表</title>
	</head>
	<body bgcolor="#ccff99">
		
		<s:set name="page" value="#request.pagingBean"/>
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
		
		<center>
			
			<s:if test="#page.hasFirst">
				<a href="browseBooks.action?catalogId=<s:property value="#book.catalog.catalogId"/>&currentPag=1">首页</a>
			</s:if>
			
			<s:if test="#page.hasPrevious">
				<a href="browseBooks.action?catalogId=<s:property value="#book.catalog.catalogId"/>&currentPag=<s:property value="#page.currentPage-1"/>">上一页</a>
			</s:if>
			
			<s:if test="#page.hasNext">
				<a href="browseBooks.action?catalogId=<s:property value="#book.catalog.catalogId"/>&currentPag=<s:property value="#page.currentPage+1"/>">下一页</a>
			</s:if>
			
			<s:if test="#page.hasLast">
				<a href="browseBooks.action?catalogId=<s:property value="#book.catalog.catalogId"/>&currentPag=<s:property value="#page.totalPage"/>">尾页</a>
			</s:if>
			<br>
			当前第<s:property value="#page.currentPage"/>页,总共<s:property value="#page.totalPage"/>页
		</center>
		
	</body>
</html>