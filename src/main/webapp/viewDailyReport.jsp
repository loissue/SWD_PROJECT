<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Daily Reports</title>
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

        .view-button, .edit-button, .delete-button {
            display: inline-block;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }

        .view-button {
            background-color: #40916c;
            color: white;
        }

        .view-button:hover {
            background-color: #1b4332;
        }

        .edit-button {
            background-color: #f4a261;
            color: white;
        }

        .edit-button:hover {
            background-color: #e76f51;
        }

        .delete-button {
            background-color: #e63946;
            color: white;
        }

        .delete-button:hover {
            background-color: #d62828;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1>Daily Reports</h1>
    <a href="addDailyReport.jsp?recordId=${param.recordId}" class="add-button">Add Daily Report</a>
    <table>
        <thead>
        <tr>
            <th>Time</th>
            <th>Symtoms</th>
            <th>RFE</th>
            <th>Test and Result</th>
            <th>Treatment Progress</th>
            <th>Diagnose</th>

            <th>Note</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="report" items="${reports}">
            <tr>
                <td>${report.time}</td>
                <td>${report.symtoms}</td>
                <td>${report.rfe}</td>
                <td>${report.testAndResult}</td>
                <td>${report.treatmentProgress}</td>
                <td>${report.diagnose}</td>

                <td>${report.note}</td>
                <td>
                    <a href="editDailyReport?reportId=${report.reportId}" class="edit-button">Edit</a>
                    <a href="deleteDailyReport?reportId=${report.reportId}" class="delete-button" onclick="return confirm('Are you sure you want to delete this report?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
