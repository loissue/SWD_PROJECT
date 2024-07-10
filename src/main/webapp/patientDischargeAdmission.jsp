<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Patient Discharge/Admission</title>
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #2d6a4f;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .admit-button, .discharge-button {
            display: inline-block;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }

        .admit-button {
            background-color: #2d6a4f;
            color: white;
        }

        .admit-button:hover {
            background-color: #1b4332;
        }

        .discharge-button {
            background-color: #e74c3c;
            color: white;
        }

        .discharge-button:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1>Patient Discharge/Admission</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="patient" items="${patients}">
            <tr>
                <td>${patient.patientId}</td>
                <td>${patient.name}</td>
                <td>${patient.status ? "Admitted" : "Discharged"}</td>
                <td>
                    <c:choose>
                        <c:when test="${!patient.status}">
                            <a href="patients?action=admit&id=${patient.patientId}" class="admit-button">Admit</a>
                        </c:when>
                        <c:otherwise>
                            <a href="patients?action=discharge&id=${patient.patientId}" class="discharge-button">Discharge</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
