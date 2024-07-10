<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Room Allocation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 0;
        }

        .container {
            display: flex;
            width: 90%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .room-list {
            width: 30%;
            margin-right: 20px;
        }

        .room-list ul {
            list-style-type: none;
            padding: 0;
        }

        .room-list li {
            margin-bottom: 10px;
        }

        .room-list a {
            display: block;
            padding: 10px;
            background-color: #2d6a4f;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .room-list a:hover {
            background-color: #1b4332;
        }

        .bed-map {
            width: 70%;
        }

        .bed {
            display: inline-block;
            width: 100px;
            height: 100px;
            margin: 10px;
            background-color: #40916c;
            color: white;
            text-align: center;
            line-height: 100px;
            border-radius: 5px;
            cursor: pointer;
        }

        .bed.occupied {
            background-color: #e63946;
        }

        .bed:hover {
            background-color: #1b4332;
        }

        .bed.occupied:hover {
            background-color: #d62828;
        }
    </style>
    <script>
        function allocateBed(bedId) {
            // Open modal for allocating a patient to the bed
            alert('Allocate patient to bed ' + bedId);
        }

        function dischargePatient(bedId) {
            // Open modal for discharging a patient from the bed
            alert('Discharge patient from bed ' + bedId);
        }

        function changePatient(bedId) {
            // Open modal for changing patient in the bed
            alert('Change patient in bed ' + bedId);
        }
    </script>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <div class="room-list">
        <h2>Rooms</h2>
        <ul>
            <c:forEach var="room" items="${rooms}">
                <li>
                    <a href="roomAllocation?roomId=${room.id}">${room.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="bed-map">
        <h2>Beds</h2>
        <c:if test="${not empty beds}">
            <c:forEach var="bed" items="${beds}">
                <div class="bed ${bed.status ? 'occupied' : ''}" onclick="${bed.status ? 'dischargePatient(' + bed.bedId + ')' : 'allocateBed(' + bed.bedId + ')'}">
                        ${bed.name}
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>
