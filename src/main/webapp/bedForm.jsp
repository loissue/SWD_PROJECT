<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bed Form</title>
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

        .form-group input[type="text"], .form-group select {
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
        <c:when test="${not empty bed.bedId}">Edit Bed</c:when>
        <c:otherwise>Add Bed</c:otherwise>
    </c:choose></h1>
    <form action="beds?action=<c:choose>
        <c:when test="${not empty bed.bedId}">update</c:when>
        <c:otherwise>insert</c:otherwise>
    </c:choose>" method="post">
        <c:if test="${not empty bed.bedId}">
            <input type="hidden" name="bedId" value="${bed.bedId}">
        </c:if>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${bed.name}" required>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="true" <c:if test="${bed.status}">selected</c:if>>Occupied</option>
                <option value="false" <c:if test="${!bed.status}">selected</c:if>>Available</option>
            </select>
        </div>
        <div class="form-group">
            <label for="roomId">Room:</label>
            <select id="roomId" name="roomId" required>
                <c:forEach var="room" items="${rooms}">
                    <option value="${room.id}" <c:if test="${room.id == bed.room.id}">selected</c:if>>${room.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <input type="submit" value="Save">
        </div>
    </form>
</div>
</body>
</html>
