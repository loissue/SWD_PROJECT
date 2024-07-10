<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Receipt List</title>
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

        .edit-button, .delete-button {
            display: inline-block;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }

        .edit-button {
            background-color: #f1c40f;
            color: white;
        }

        .edit-button:hover {
            background-color: #d4ac0d;
        }

        .delete-button {
            background-color: #e74c3c;
            color: white;
        }

        .delete-button:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1>Receipt List</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Account ID</th>
            <th>Patient ID</th>
            <th>Status</th>
            <th>Medicine ID</th>
            <th>Amount</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="receipt" items="${receipts}">
            <tr>
                <td>${receipt.id}</td>
                <td>${receipt.accountID}</td>
                <td>${receipt.patientID}</td>
                <td>${receipt.status ? "Paid" : "Unpaid"}</td>
                <td>${receipt.medicineID}</td>
                <td>${receipt.amount}</td>
                <td>
                    <a href="receipts?action=edit&id=${receipt.id}" class="edit-button">Edit</a>
                    <a href="receipts?action=delete&id=${receipt.id}" class="delete-button" onclick="return confirm('Are you sure you want to delete this receipt?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

