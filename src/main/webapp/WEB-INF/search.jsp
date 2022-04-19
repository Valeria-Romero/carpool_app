<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Rides</h1>
<c:forEach items="${rides}" var="ride">
	<div>
		<h2>From: ${ride.getLocationfrom()}</h2>
		<h2>To: ${ride.getLocationto()}</h2>
		<p>When: ${ride.getDate()}</p>
		<p>Time: ${ride.getTime()}</p>
		<p>Seats: ${ride.getSeats()}</p>
		<form action="ride/join/${ride.getId()}" method="post">
			<input type="hidden" value="${user_id}" name="user_id">
			<button type="submit">Join ride</button>
		</form>
	</div>
</c:forEach>

</body>
</html>