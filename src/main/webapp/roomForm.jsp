<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Room Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #2d6a4f;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .form-group input[type="submit"] {
            padding: 10px 20px;
            background-color: #2d6a4f;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .form-group input[type="submit"]:hover {
            background-color: #1b4332;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1><c:choose>
        <c:when test="${not empty room.id}">Edit Room</c:when>
        <c:otherwise>Add Room</c:otherwise>
    </c:choose></h1>
    <form action="rooms?action=<c:choose>
        <c:when test="${not empty room.id}">update</c:when>
        <c:otherwise>insert</c:otherwise>
    </c:choose>" method="post">
        <c:if test="${not empty room.id}">
            <input type="hidden" name="id" value="${room.id}">
        </c:if>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${room.name}" required>
        </div>
        <div class="form-group">
            <label for="type">Type:</label>
            <input type="text" id="type" name="type" value="${room.type}" required>
        </div>
        <div class="form-group">
            <input type="submit" value="Save">
        </div>
    </form>
</div>
</body>
</html>
