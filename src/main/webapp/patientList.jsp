<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Models.Account" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>

<html>
<head>
    <title>Hospital Management System</title>
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

        .add-button {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 20px;
            background-color: #2d6a4f;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .add-button:hover {
            background-color: #1b4332;
        }

        .search-box {
            margin-bottom: 20px;
        }

        .search-box input[type="text"] {
            padding: 10px;
            width: 80%;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .search-box input[type="submit"] {
            padding: 10px 20px;
            background-color: #2d6a4f;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .search-box input[type="submit"]:hover {
            background-color: #1b4332;
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
            cursor: pointer;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .view-button {
            display: inline-block;
            padding: 5px 10px;
            background-color: #40916c;
            color: white;
            text-decoration: none;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }

        .view-button:hover {
            background-color: #1b4332;
        }
    </style>
    <script>
        function sortColumn(column) {
            let currentSort = '${param.sort}';
            let currentOrder = '${param.order}';
            let newOrder = 'asc';

            if (currentSort === column && currentOrder === 'asc') {
                newOrder = 'desc';
            }

            let searchParams = new URLSearchParams(window.location.search);
            searchParams.set('sort', column);
            searchParams.set('order', newOrder);
            window.location.search = searchParams.toString();
        }
    </script>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1>Patient List</h1>
    <div class="search-box">
        <form action="patient-list" method="get">
            <input type="text" name="search" placeholder="Search patients..." value="${param.search}">
            <input type="hidden" name="sort" value="${param.sort}">
            <input type="hidden" name="order" value="${param.order}">
            <input type="submit" value="Search">
        </form>
    </div>
    <a href="addPatient.jsp" class="add-button">Add Patient</a>
    <table>
        <thead>
        <tr>
            <th onclick="sortColumn('id')">
                Id
                <c:if test="${param.sort == 'id'}">
                    <c:choose>
                        <c:when test="${param.order == 'asc'}">▲</c:when>
                        <c:otherwise>▼</c:otherwise>
                    </c:choose>
                </c:if>
            </th>
            <th onclick="sortColumn('name')">
                Name
                <c:if test="${param.sort == 'name'}">
                    <c:choose>
                        <c:when test="${param.order == 'asc'}">▲</c:when>
                        <c:otherwise>▼</c:otherwise>
                    </c:choose>
                </c:if>
            </th>
            <th onclick="sortColumn('phone')">
                Phone
                <c:if test="${param.sort == 'phone'}">
                    <c:choose>
                        <c:when test="${param.order == 'asc'}">▲</c:when>
                        <c:otherwise>▼</c:otherwise>
                    </c:choose>
                </c:if>
            </th>
            <th onclick="sortColumn('email')">
                Email
                <c:if test="${param.sort == 'email'}">
                    <c:choose>
                        <c:when test="${param.order == 'asc'}">▲</c:when>
                        <c:otherwise>▼</c:otherwise>
                    </c:choose>
                </c:if>
            </th>
            <th onclick="sortColumn('address')">
                Address
                <c:if test="${param.sort == 'address'}">
                    <c:choose>
                        <c:when test="${param.order == 'asc'}">▲</c:when>
                        <c:otherwise>▼</c:otherwise>
                    </c:choose>
                </c:if>
            </th>
            <th onclick="sortColumn('emergency')">
                Emergency
                <c:if test="${param.sort == 'emergency'}">
                    <c:choose>
                        <c:when test="${param.order == 'asc'}">▲</c:when>
                        <c:otherwise>▼</c:otherwise>
                    </c:choose>
                </c:if>
            </th>
            <th onclick="sortColumn('status')">
                Status
                <c:if test="${param.sort == 'status'}">
                    <c:choose>
                        <c:when test="${param.order == 'asc'}">▲</c:when>
                        <c:otherwise>▼</c:otherwise>
                    </c:choose>
                </c:if>
            </th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="patient" items="${patients}">
            <tr>
                <td>${patient.patientId}</td>
                <td>${patient.name}</td>
                <td>${patient.phone}</td>
                <td>${patient.email}</td>
                <td>${patient.address}</td>
                <td>${patient.emergency}</td>
                <td><c:choose>
                    <c:when test="${patient.status}">
                        Active
                    </c:when>
                    <c:otherwise>
                        Inactive
                    </c:otherwise>
                </c:choose></td>
                <td>
                    <a href="viewRecord?patientId=${patient.patientId}" class="view-button">View Record</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
