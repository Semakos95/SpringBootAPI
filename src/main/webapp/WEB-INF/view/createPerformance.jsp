<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Performance</title>
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
    <h1>Register New Perfomance</h1>

    <form action="${pageContext.request.contextPath}/registerPerformance" method="post" modelAttribute="performance">
        <label for="actors">Actors:</label>
        <input type="text" id="actors" name="actors" value="${performanceDTO.actors}" required><br>

        <label for="category">Category:</label>
        <input type="text" id="category" name="category" value="${performanceDTO.category}" required><br>

        <label for="costumes">Costumes:</label>
        <input type="text" id="costumes" name="costumes" value="${performanceDTO.costumes}" required><br>

        <label for="direction">Direction:</label>
        <input type="text" id="direction" name="direction" value="${performanceDTO.direction}" required><br>

        <label for="duration">Duration:</label>
        <input type="text" id="duration" name="duration" value="${performanceDTO.duration}" required><br>

        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${performanceDTO.title}" required><br>

        <label for="production">Production:</label>
        <input type="text" id="production" name="production" value="${performanceDTO.production}" required><br>

        <label for="stars">Stars:</label>
        <input type="text" id="stars" name="stars" value="${performanceDTO.stars}" required><br>

        <label for="summary">Summary:</label>
        <input type="text" id="summary" name="summary" value="${performanceDTO.summary}" required><br>

        <label for="toStage">To Stage:</label>
        <input type="text" id="toStage" name="toStage" value="${performanceDTO.toStage}" required><br>

        <label for="trailer">Trailer:</label>
        <input type="text" id="trailer" name="trailer" value="${performanceDTO.trailer}" required><br>

        <label for="userDTO.id">UserDTO ID:</label>
        <input type="int" id="userDTO.id" name="userDTO.id" value="${performanceDTO.userDTO.id}" required><br>


        <input type="submit" value="Register">
    </form>
</body>
</html>