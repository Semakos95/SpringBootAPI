<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<title>First Web Application</title>
</head>

<body>
    <p> Welcome, ${user.firstName} </p>

    <form action="logout" method="post">
    <input type="submit" value="Logout">
    </form>

    <form action="showAllPerformances" method="get">
    <input type="submit" value="Show All Performances">
    </form>

    <form action="showAllUsers" method="get">
    <input type="submit" value="Show All Users">
    </form>

    <form action="registerPerformance" method="get">
    <input type="submit" value="Register Performance">
    </form>

    <form action="register" method="get">
    <input type="submit" value="Register User">
    </form>

    <form action="rentRoom" method="get">
    <input type="submit" value="Rent Room">
    </form>

    <form action="createTicket" method="get">
    <input type="submit" value="Buy Ticket">
    </form>

    <form action="editTicket" method="get">
    <input type="submit" value="Edit Existed Ticket">
    </form>
</body>

</html>