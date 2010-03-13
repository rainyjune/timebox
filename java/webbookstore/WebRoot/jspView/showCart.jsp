<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="head.jsp"></jsp:include>

<html>
	
	<head>
		<title>我的购物车</title>
	</head>
	
	<body bgcolor="#ffff99">
		
		<center>
			<s:form action="updateToCart" method="post" >
				<s:set name="items" value="#session.cart.items"></s:set>
				
				<s:if test="#items.size != 0">
					<font color="blue">以下是您选购的物品信息：</font><br/><br/>
					<s:iterator value="#items">
						<img src="picture/<s:property value="value.book.picture"/>" alt="aa"/><br/>
						书名：<s:property value="value.book.bookName"/><br/>
						价格：<s:property value="value.book.price"/><br/>
						数量：<font color="red"><s:property value="value.quantity"/></font><br/>
						<form action="updateToCart.action" method="post">
							<input type="text" name="quantity"  size="4"/>
							<input type="hidden" name="bookId" value="<s:property value="value.book.bookId"/>"/>
							<input type="submit" value="更新"/>
						</form>
						<hr/>
					</s:iterator>
					消费金额:<font color="red"><s:property value="#session.cart.totalPrice"/></font>&nbsp;&nbsp;&nbsp;
					<a href="checkout.action">结帐</a>
				</s:if>
				
				<s:else>
					对不起,您还没有选购图书!
				</s:else>
			</s:form>
		</center>
	</body>
</html>