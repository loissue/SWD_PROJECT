package servlets;

import DAO.PatientRecordDAO;
import Models.PatientRecord;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editRecord")
public class EditRecordServlet extends HttpServlet {

    private PatientRecordDAO patientRecordDAO;

    @Override
    public void init() {
        patientRecordDAO = new PatientRecordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recordIdStr = request.getParameter("recordId");
        if (recordIdStr == null || recordIdStr.trim().isEmpty()) {
            response.sendRedirect("errorPage.jsp");
            return;
        }

        int recordId;
        try {
            recordId = Integer.parseInt(recordIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp");
            return;
        }

        PatientRecord record = patientRecordDAO.getPatientRecordById(recordId);
        if (record != null) {
            request.setAttribute("record", record);
            request.getRequestDispatcher("/editRecord.jsp").forward(request, response);
        } else {
            response.sendRedirect("errorPage.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int recordId = Integer.parseInt(request.getParameter("recordId"));
        String familyHistory = request.getParameter("familyHistory");
        String allergies = request.getParameter("allergies");
        String surgicalProcedures = request.getParameter("surgicalProcedures");
        String condition = request.getParameter("condition");

        PatientRecord record = new PatientRecord();
        record.setRecordId(recordId);
        record.setFamilyHistory(familyHistory);
        record.setAllergies(allergies);
        record.setSurgicalProcedures(surgicalProcedures);
        record.setCondition(condition);

        patientRecordDAO.editRecord(record);
        response.sendRedirect("viewRecord?patientId=" + request.getSession().getAttribute("patientId")); // Chuyển hướng đến trang viewRecord với patientId
    }
}
