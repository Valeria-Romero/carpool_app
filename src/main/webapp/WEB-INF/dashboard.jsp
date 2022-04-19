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

	<div class="navBar">
		<h1 class="logo"><a href="/dashboard">Ridelux</a></h1>
		<div class="menu">
			<a class="menu_item" href="/rides">Rides</a>
			<a class="menu_item" href="/messages">Messages</a>
			<a class="menu_item" href="/profile">Profile</a>
			<a class="menu_item" href="/logout">Logout</a>
		</div>
	</div>

	<c:if test="${userType.equals(0)}">
		<h1 class="title">Search for a trip</h1>
		<form action="/ride/search" method="post">
		<label for="locationfrom">From</label>
		<input type="text" name="locationfrom">
		<label for="locationto">To</label>
		<input type="text" name="locationto">
		<label for="when">When</label>
		<input type="date" name="date">
		<button type="submit" class="action">Search</button>
		</form>
	</c:if>
		
	<c:if test="${userType.equals(1)}">
		<h1 class="title">Add a new ride</h1>
		<form action="/ride/create" method="post">
		<label for="locationfrom">From</label>
		<input type="text" name="locationfrom">
		<label for="locationto">To</label>
		<input type="text" name="locationto">
		<label for="when">When</label>
		<input type="date" name="date">
		<label for="time">Time</label>
		<input type="time" name="time">
		<label>Seats</label>
		<input type="number" name="seats">
		<input type="hidden" name="user_id" value="${user_id}">
		<button type="submit" class="action">Create</button>
		</form>
	</c:if>
</body>
</html>