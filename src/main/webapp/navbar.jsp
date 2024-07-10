<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>

<div class="navbar">

    <a href="index.jsp"><img src="Image/icon.png"  alt="Medical Icon" style="height:24px; vertical-align:middle;"> Home</a>
    <c:choose>
        <c:when test="${sessionScope.role == 'Doctor' || sessionScope.role == 'Nurse'}">
            <a href="patient-list" class="${pageContext.request.servletPath eq 'patient-list' ? 'active' : ''}">Patient List</a>
        </c:when>
        <c:otherwise>
            <a href="#" onclick="alert('You do not have access to view the Patient List.')" class="${pageContext.request.servletPath eq '/patient-list' ? 'active' : ''}">Patient List</a>
        </c:otherwise>
    </c:choose>

    <a href="reiceptList.jsp">Receipt List</a>
    <a href="appoinment.jsp">Appointment</a>
    <a href="patientDischargeAdmission.jsp">Patient Discharge/Admission</a>
    <a href="roomAllocation">Room Allocation</a>

    <c:choose>
        <c:when test="${not empty sessionScope.user.email}">
            <div class="dropdown right">
                <button class="dropbtn">${sessionScope.role}</button>
                <div class="dropdown-content">
                    <a href="user-information.jsp">User Information</a>
                    <a href="logout">Logout</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <a href="login.jsp" class="right">Login</a>
        </c:otherwise>
    </c:choose>
</div>

<style>
    .navbar {
        background-color: #2d6a4f; /* Dark green */
        overflow: hidden;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .navbar a {
        float: left;
        display: block;
        color: white;
        text-align: center;
        padding: 14px 20px;
        text-decoration: none;
        font-size: 17px;
    }

    .navbar img {
        margin-right: 10px;
    }

    .navbar a:hover {
        background-color: #1b4332;
        color: white;
    }

    .navbar a.active {
        background-color: #40916c;
        color: white;
    }

    .navbar .right {
        float: right;
    }

    .dropdown {
        float: right;
        overflow: hidden;
    }

    .dropbtn {
        font-size: 17px;
        border: none;
        outline: none;
        color: white;
        padding: 14px 20px;
        background-color: inherit;
        font-family: inherit;
        margin: 0;
        cursor: pointer;
    }

    .navbar a:hover, .dropdown:hover .dropbtn {
        background-color: #1b4332;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .dropdown-content a {
        float: none;
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
        text-align: left;
    }

    .dropdown-content a:hover {
        background-color: #ddd;
    }

    .dropdown:hover .dropdown-content {
        display: block;
    }
</style>
