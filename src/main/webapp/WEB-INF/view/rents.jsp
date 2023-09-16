<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Rents</title>
</head>
<body>
    <h1>All Room Rents of Theater</h1>

    <form action="logout" method="post">
    <input type="submit" value="Logout">
    </form>
    <form action="admin" method="get">
    <input type="submit" value="Back to Menu">
    </form>

    <table>
         <tr>
            <th>Date</th>
            <th>Period</th>

         </tr>
         <c:forEach items="${allRents}" var="rentDTO">
         <tr>
            <td>${rentDTO.date}</td>
            <td>${rentDTO.period}</td>

                </tr>
            </c:forEach>
         </table>
    </body>
</html>