<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Main Page</title>
</head>
<body>

	<table>
		<tr>
			<td>Welcome. Main Page.</td>
		</tr>
		<tr>
			<td>�Խ���</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<td>��ȣ</td>
			<td>���̵�</td>
			<td>�̸�</td>
			<td>�̸�����</td>
			<td>��¥</td>
		</tr>
		<core:forEach var="result" items="${jobItems}">
			<tr>
				<td>1</td>
				<td><core:out value="${result.userId}"/></td>
				<td><core:out value="${result.userId}"/></td>
				<td><core:out value="Ŭ��"/></td>
				<td><core:out value="${result.imageUrl}"/></td>
			</tr>
		</core:forEach>
		
	</table>
</body>
</html>