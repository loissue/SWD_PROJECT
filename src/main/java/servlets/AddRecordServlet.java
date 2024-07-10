package servlets;

import Models.Patient;
import Models.PatientRecord;
import DAO.PatientRecordDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import Models.Account;

@WebServlet("/addRecord")
public class AddRecordServlet extends HttpServlet {

    private PatientRecordDAO patientRecordDAO;

    @Override
    public void init() {
        patientRecordDAO = new PatientRecordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        request.setAttribute("patientId", patientId);
        request.getRequestDispatcher("addRecord.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");

        if (account == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if user is not logged in
            return;
        }

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        String familyHistory = request.getParameter("familyHistory");
        String allergies = request.getParameter("allergies");
        String surgicalProcedures = request.getParameter("surgicalProcedures");
        String condition = request.getParameter("condition");

        PatientRecord record = new PatientRecord();
        record.setPatient(new Patient(patientId));
        record.setAccountId(account.getAccountId()); // Set the account for the record
        record.setFamilyHistory(familyHistory);
        record.setAllergies(allergies);
        record.setSurgicalProcedures(surgicalProcedures);
        record.setCondition(condition);

        patientRecordDAO.addPatientRecord(record);

        response.sendRedirect("viewRecord?patientId=" + patientId);
    }
}

