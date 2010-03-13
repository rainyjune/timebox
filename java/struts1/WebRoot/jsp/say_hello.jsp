<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for HelloForm form</title>
	</head>
	<body>
		<html:form action="/sayHelloSubmit">
			message : <html:text property="message"/><html:errors property="message"/><br/>
			year : <html:text property="year"/><html:errors property="year"/><br/>
			<html:errors/>
			<html:submit/><html:reset/>
		</html:form>
	</body>
</html>

