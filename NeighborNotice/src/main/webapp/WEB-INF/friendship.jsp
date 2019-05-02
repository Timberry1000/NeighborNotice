<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Friends List</h1>
	<a href="/home">Back</a>
	
	<table>
		<tr>
			<c:forEach items = {user.friends}>
				<td></td>
			 </c:forEach>
		</tr>
	</table>
</body>
</html>