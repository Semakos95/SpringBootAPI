<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
            color: #333;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
            color: #555;
        }

        form {
            width: 60%;
            margin: 20px auto;
            background-color: white;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 5px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.2s;
        }

        input[type="text"]:focus {
            border-color: #007bff;
        }

        input[type="submit"] {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <form action="logout" method="post">
    <input type="submit" value="Logout">
    </form>
    <form action="admin" method="get">
    <input type="submit" value="Back to Menu">
    </form>
    <h1>Register</h1>

    <form action="${pageContext.request.contextPath}/register" method="post" modelAttribute="user">
        <label for="username">username:</label>
        <input type="text" id="username" name="username" value="${userDTO.username}" required><br>

        <label for="password">password:</label>
        <input type="text" id="password" name="password" value="${userDTO.password}" required><br>

        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="${userDTO.firstName}" required><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${userDTO.lastName}" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${userDTO.email}" required><br>

        <label for="phone">Phone:</label>
        <input type="int" id="phone" name="phone" value="${userDTO.phone}" required><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${userDTO.address}" required><br>

        <label for="postalCode">Postal Code:</label>
        <input type="text" id="postalCode" name="postalCode" value="${userDTO.postalCode}" required><br>

        <input type="submit" value="Register">
    </form>
</body>
</html>