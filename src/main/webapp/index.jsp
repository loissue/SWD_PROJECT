<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Models.User" %>
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

        .header {
            text-align: center;
            padding: 50px 0;
            background-color: #2d6a4f;
            color: white;
        }

        .header h1 {
            margin: 0;
            font-size: 2.5em;
        }

        .header p {
            margin: 10px 0 0;
            font-size: 1.2em;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .content {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-around;
        }

        .card {
            background-color: #40916c;
            color: white;
            padding: 20px;
            width: 45%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-decoration: none;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        .card h2 {
            margin-top: 0;
        }

        .card p {
            color: white;
        }
    </style>
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="header">
    <h1>Hospital Management System</h1>
    <p>Welcome to the hospital management system. Here, doctors can manage patients effectively and efficiently.</p>
</div>

<div class="container">
    <div class="content">
        <a href="patient-list" class="card">
            <h2>Patient List</h2>
            <p>Manage the list of patients registered in the hospital.</p>
        </a>
        <a href="receipt-list.jsp" class="card">
            <h2>Receipt List</h2>
            <p>View and manage receipts issued to patients.</p>
        </a>
        <a href="appointment.jsp" class="card">
            <h2>Appointment</h2>
            <p>Schedule and manage appointments with patients.</p>
        </a>
        <a href="patient-discharge-admission.jsp" class="card">
            <h2>Patient Discharge/Admission</h2>
            <p>Manage the admission and discharge process of patients.</p>
        </a>
        <a href="room-allocation.jsp" class="card">
            <h2>Room Allocation</h2>
            <p>Allocate rooms to patients as per their needs.</p>
        </a>
    </div>
</div>
</body>
</html>
