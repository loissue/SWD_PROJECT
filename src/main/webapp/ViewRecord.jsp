<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Patient Record</title>
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

        .view-button {
            display: inline-block;
            padding: 5px 10px;
            background-color: #b0e57c; /* pastel green */
            color: white;
            text-decoration: none;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }

        .view-button:hover {
            background-color: #86af49;
        }

        .edit-button {
            display: inline-block;
            padding: 5px 10px;
            background-color: #fdd835; /* pastel yellow */
            color: white;
            text-decoration: none;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }

        .edit-button:hover {
            background-color: #fbc02d;
        }

        .delete-button {
            display: inline-block;
            padding: 5px 10px;
            background-color: #ef5350; /* pastel red */
            color: white;
            text-decoration: none;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }

        .delete-button:hover {
            background-color: #e53935;
        }

        /* Modal styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 500px;
            border-radius: 5px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .modal-footer {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }

        .modal-footer button {
            padding: 10px 20px;
            margin-left: 10px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        .modal-footer .cancel-button {
            background-color: #b0bec5; /* pastel grey */
            color: white;
        }

        .modal-footer .confirm-button {
            background-color: #ef5350; /* pastel red */
            color: white;
        }
    </style>
    <script>
        function confirmDelete(recordId) {
            var modal = document.getElementById("deleteModal");
            var confirmButton = document.getElementById("confirmDeleteButton");

            confirmButton.onclick = function() {
                window.location.href = "deleteRecord?recordId=" + recordId;
            }

            modal.style.display = "block";
        }

        function closeModal() {
            var modal = document.getElementById("deleteModal");
            modal.style.display = "none";
        }
    </script>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1>Patient Record</h1>
    <a href="addRecord.jsp?patientId=${param.patientId}" class="add-button">Add Record</a>
    <table>
        <thead>
        <tr>
            <th>Record ID</th>
            <th>Family History</th>
            <th>Allergies</th>
            <th>Surgical Procedures</th>
            <th>Condition</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${records}">
            <tr>
                <td>${record.recordId}</td>
                <td>${record.familyHistory}</td>
                <td>${record.allergies}</td>
                <td>${record.surgicalProcedures}</td>
                <td>${record.condition}</td>
                <td>
                    <a href="viewDailyReport?recordId=${record.recordId}" class="view-button">View Daily Report</a>
                    <a href="editRecord?recordId=${record.recordId}" class="edit-button">Edit</a>

                    <a href="javascript:void(0)" onclick="confirmDelete(${record.recordId})" class="delete-button">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- The Modal -->
<div id="deleteModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <p>Are you sure you want to delete this record?</p>
        <div class="modal-footer">
            <button class="cancel-button" onclick="closeModal()">Cancel</button>
            <button id="confirmDeleteButton" class="confirm-button">Delete</button>
        </div>
    </div>
</div>
</body>
</html>
