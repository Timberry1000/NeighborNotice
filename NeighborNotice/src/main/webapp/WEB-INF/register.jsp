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
	<h1>Register Here</h1>
		<form:form action="/register" method="POST" modelAttribute="user">
			<h1 class="blue">Register</h1>
			<c:if test="${userError != null}">
				<p class="errors">${userError}</p>
			</c:if>
			
			
			<p class="error"><form:errors path="firstName"></form:errors></p>
			<p><form:input placeholder="First Name" path="firstName" type="text"></form:input></p>
			
			<p class="error"><form:errors path="lastName"></form:errors></p>
			<p><form:input placeholder="Last Name" path="lastName"></form:input></p>
			
			<p class="error"><form:errors path="email"></form:errors></p>
			<p><form:input placeholder="Email" path="email"></form:input></p>
			
			<p class="error"><form:errors path="password"></form:errors></p>
			<p><form:input placeholder="Password" path="password"></form:input></p>
			
			<p class="error"><form:errors path="confirmPass"></form:errors></p>
			<p><form:input placeholder="Confirm Password" path="confirmPass"></form:input></p>
			
			<p>
				<select name="host">
					<option value="true">Host</option>
					<option value="false">Guest</option>			
				</select>
			</p>
			
			<input type="submit" value="Register">
		</form:form>
</body>
</html>