<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Record</title>
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
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1>Edit Record</h1>
    <form action="editRecord" method="post">
        <input type="hidden" name="recordId" value="${record.recordId}">
        <div class="form-group">
            <label for="familyHistory">Family History:</label>
            <textarea id="familyHistory" name="familyHistory" required>${record.familyHistory}</textarea>
        </div>
        <div class="form-group">
            <label for="allergies">Allergies:</label>
            <textarea id="allergies" name="allergies" required>${record.allergies}</textarea>
        </div>
        <div class="form-group">
            <label for="surgicalProcedures">Surgical Procedures:</label>
            <textarea id="surgicalProcedures" name="surgicalProcedures" required>${record.surgicalProcedures}</textarea>
        </div>
        <div class="form-group">
            <label for="condition">Condition:</label>
            <textarea id="condition" name="condition" required>${record.condition}</textarea>
        </div>
        <div class="form-group">
            <input type="submit" value="Save Changes">
        </div>
    </form>
</div>
</body>
</html>
