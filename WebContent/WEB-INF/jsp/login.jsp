<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
	<h3>로그인</h3>
	<br>
	<form:form method="post" action="login.html" commandName="login">
		<spring:message code="login.label.id"/><form:input path="id"/>
		<font color="red"><form:errors path="id"/></font><br>
		<spring:message code="login.label.password"/><form:password path="password"/>
		<font color="red"><form:errors path="password"/></font><br>
		<input type="submit" value="<spring:message code="login.label.login"/>"/>
	</form:form>
</body>
</html>