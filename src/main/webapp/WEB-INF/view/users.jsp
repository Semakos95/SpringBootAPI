<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Users</title>
</head>
<body>
    <h1>Registered Users</h1>

    <form action="logout" method="post">
    <input type="submit" value="Logout">
    </form>
    <form action="admin" method="get">
    <input type="submit" value="Back to Menu">
    </form>

    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Postal Code</th>
        </tr>

        <c:forEach items="${users}" var="userDTO">
            <tr>
                <td>${userDTO.firstName}</td>
                <td>${userDTO.lastName}</td>
                <td>${userDTO.email}</td>
                <td>${userDTO.phone}</td>
                <td>${userDTO.address}</td>
                <td>${userDTO.postalCode}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>