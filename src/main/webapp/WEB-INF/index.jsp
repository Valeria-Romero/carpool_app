<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"  href="/css/styles.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CarpoolApp</title>
</head>
<body>
	<h1 class="logo">Ridelux</h1>
	<div class="log-form">
		<form action="/login" method="post" class="login">
		<h1 class="title-login">Login</h1>
			<label for="userEmail"> Email: </label>
			<input type="text" id="userEmail" name="userEmail" />
			<label for="userPassword"> Password: </label>
			<input type="password" id="userPassword" name="userPassword" />
			<button type="submit" class="action">
				Login
			</button>
			<div>
				<c:out value="${loginErrorMessage}"></c:out>
			</div>
		</form>
		
		<form method="POST" action="/registerUser" class="login">
			<h1 class="title-login">Register</h1>	
				<label for="name"> Name: </label>
				<input type="text" id="name" name="name" />
				<label for="email"> Email: </label>
				<input type="text" id="email" name="email" />
				<label for="password"> Password: </label>
				<input type="password" id="password" name="password" />
				<label for="passwordConfirmation">Confirm password:</label>
				<input type="password" id="passwordConfirmation" name="passwordConfirmation" />

				<label for="typeofuser">Type of user</label>
				<select name="typeofuser">
					<option value=0>Passenger</option>
					<option value=1>Driver</option>
				</select>
				<button type="submit" class="action">
					Register
				</button>
			<div class="emptyspace">
				<c:out value="${emptyspace}"></c:out>
			</div>
			<div class="errorMessage">
				<c:out value="${errorMessage}"></c:out>
			</div>
			<div class="success">
				<c:out value="${success}"></c:out>
				</div>
		</form>
	</div>
	
</body>
</html>