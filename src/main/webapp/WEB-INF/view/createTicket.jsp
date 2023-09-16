<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buy Ticket</title>
</head>
<body>
    <h1>Buy a Ticket of Theater</h1>
</head>
<body>
    <form action="logout" method="post">
    <input type="submit" value="Logout">
    </form>
    <form action="admin" method="get">
    <input type="submit" value="Back to Menu">
    </form>
    <h1>Fill the Ticket Form</h1>

    <form action="${pageContext.request.contextPath}/createTicket" method="post" modelAttribute="ticket">
        <label for="date">Date:</label>
        <input type="text" id="date" name="date" value="${ticketDTO.date}" required><br>

        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="${ticketDTO.price}" required><br>

        <label for="rowss">Row:</label>
        <input type="text" id="rowss" name="rowss" value="${ticketDTO.rowss}" required><br>

        <label for="seat">Seat:</label>
        <input type="text" id="seat" name="seat" value="${ticketDTO.seat}" required><br>

        <label for="time">Time:</label>
        <input type="text" id="time" name="time" value="${ticketDTO.time}" required><br>

        <label for="performanceDTO.id">Performance ID:</label>
        <input type="int" id="performanceDTO.id" name="performanceDTO.id" value="${ticketDTO.performanceDTO.id}" required><br>

        <label for="roomDTO.id">Room ID:</label>
        <input type="int" id="roomDTO.id" name="roomDTO.id" value="${ticketDTO.roomDTO.id}" required><br>

        <label for="sectionDTO.id">Section ID:</label>
        <input type="int" id="sectionDTO.id" name="sectionDTO.id" value="${ticketDTO.sectionDTO.id}" required><br>

        <input type="submit" value="Get Ticket now">
    </form>
</body>
</html>