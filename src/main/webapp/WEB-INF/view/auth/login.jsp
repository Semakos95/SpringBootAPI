<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>

</head>
<body class="text-center">
<main class="form-signin">
    <form action="<c:url value="/login" />" method="POST">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput" name="username" placeholder="Username">
            <%--            <label for="floatingInput">Username</label>--%>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
            <%--            <label for="floatingPassword">Password</label>--%>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; CINEMA 2023</p>
    </form>
</main>
</body>
</html>

