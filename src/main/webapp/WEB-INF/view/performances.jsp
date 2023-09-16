<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Performances</title>
    </head>
    <body>
        <h1>Registered Performances</h1>

        <form action="logout" method="post">
        <input type="submit" value="Logout">
        </form>
        <form action="admin" method="get">
        <input type="submit" value="Back to Menu">
        </form>

        <table>
            <tr>
                <th>Actors</th>
                <th>Category</th>
                <th>Costumes</th>
                <th>Direction</th>
                <th>Duration</th>
                <th>Title</th>
                <th>Production</th>
                <th>Stars</th>
                <th>Summary</th>
                <th>To Stage</th>
                <th>Trailer</th>
            </tr>

            <c:forEach items="${performances}" var="performanceDTO">
                <tr>
                    <td>${performanceDTO.actors}</td>
                    <td>${performanceDTO.category}</td>
                    <td>${performanceDTO.costumes}</td>
                    <td>${performanceDTO.direction}</td>
                    <td>${performanceDTO.duration}</td>
                    <td>${performanceDTO.title}</td>
                    <td>${performanceDTO.production}</td>
                    <td>${performanceDTO.stars}</td>
                    <td>${performanceDTO.summary}</td>
                    <td>${performanceDTO.toStage}</td>
                    <td>${performanceDTO.trailer}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
