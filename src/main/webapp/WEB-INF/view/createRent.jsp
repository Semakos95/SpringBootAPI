<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rent Room</title>
</head>
<body>
    <h1>Rent a Room of Theater</h1>
</head>
<body>
    <form action="logout" method="post">
    <input type="submit" value="Logout">
    </form>
    <form action="admin" method="get">
    <input type="submit" value="Back to Menu">
    </form>
    <h1>Rent a Room</h1>

    <form action="${pageContext.request.contextPath}/rentRoom" method="post" modelAttribute="rent">
        <label for="date">Date:</label>
        <input type="text" id="date" name="date" value="${rentDTO.date}" required><br>

        <label for="period">Period:</label>
        <input type="text" id="period" name="period" value="${rentDTO.period}" required><br>

        <label for="userDTO.id">User ID:</label>
        <input type="int" id="userDTO.id" name="userDTO.id" value="${rentDTO.userDTO.id}" required><br>

        <label for="roomDTO.id">Room ID:</label>
        <input type="int" id="roomDTO.id" name="roomDTO.id" value="${rentDTO.roomDTO.id}" required><br>

        <input type="submit" value="Rent now">
    </form>
</body>
</html>