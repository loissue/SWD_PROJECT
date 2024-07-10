<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Daily Report</title>
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
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input, .form-group textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .form-group input[type="submit"] {
            background-color: #2d6a4f;
            color: white;
            border: none;
            cursor: pointer;
        }

        .form-group input[type="submit"]:hover {
            background-color: #1b4332;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #2d6a4f;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #1b4332;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1>Edit Daily Report</h1>
    <form action="editDailyReport" method="post">
        <input type="hidden" name="reportId" value="${report.reportId}">
        <div class="form-group">
            <label for="symtoms">Symtoms:</label>
            <textarea id="symtoms" name="symtoms" required>${report.symtoms}</textarea>
        </div>
        <div class="form-group">
            <label for="rfe">RFE:</label>
            <textarea id="rfe" name="rfe" required>${report.rfe}</textarea>
        </div>
        <div class="form-group">
            <label for="testAndResult">Test and Result:</label>
            <textarea id="testAndResult" name="testAndResult" required>${report.testAndResult}</textarea>
        </div>
        <div class="form-group">
            <label for="treatmentProgress">Treatment Progress:</label>
            <textarea id="treatmentProgress" name="treatmentProgress" required>${report.treatmentProgress}</textarea>
        </div>
        <div class="form-group">
            <label for="diagnose">Diagnose:</label>
            <textarea id="diagnose" name="diagnose" required>${report.diagnose}</textarea>
        </div>
        <div class="form-group">
            <label for="note">Note:</label>
            <textarea id="note" name="note" required>${report.note}</textarea>
        </div>
        <div class="form-group">
            <input type="submit" value="Save Changes">
        </div>
    </form>
    <a href="viewDailyReport?recordId=${sessionScope.recordId}" class="back-button">Back to Daily Reports</a>
</div>
</body>
</html>
