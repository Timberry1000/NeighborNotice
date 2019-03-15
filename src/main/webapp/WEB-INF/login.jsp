<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello This Is Login</h1>
	<a href= /register >Not a member, create an account</a>
	<h1>Login</h1>
	<form action="/login" method="POST" >
		<c:if test="${loginError != null}">
			<p class="errors">${loginError}</p>
		</c:if>
		<p><input type="text" name="email" placeholder="Email" /></p>
		<p><input type="password" name="password" placeholder="Password" /></p>
		<p><input type="submit" value="login"></p>
	</form>
</body>
</html>