<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Tickets</title>
</head>
<body>
    <h1>All Rented Tickets of Theater</h1>

    <form action="logout" method="post">
    <input type="submit" value="Logout">
    </form>
    <form action="admin" method="get">
    <input type="submit" value="Back to Menu">
    </form>

    <table>
         <tr>
            <th>Date</th>
            <th>Price</th>
            <th>Row</th>
            <th>Seat</th>
            <th>Time</th>
         </tr>
         <c:forEach items="${allTickets}" var="ticketDTO">
         <tr>
            <td>${ticketDTO.date}</td>
            <td>${ticketDTO.price}</td>
            <td>${ticketDTO.rowss}</td>
            <td>${ticketDTO.seat}</td>
            <td>${ticketDTO.time}</td>
                </tr>
            </c:forEach>
         </table>
    </body>
</html>