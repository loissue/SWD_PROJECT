package servlets;

import DAO.PatientDAO;
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
import java.util.List;

@WebServlet("/viewRecord")
public class ViewRecordServlet extends HttpServlet {

    private PatientRecordDAO patientRecordDAO;

    @Override
    public void init() {
        patientRecordDAO = new PatientRecordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        session.setAttribute("patientId", patientId);
        PatientDAO patientDAO = new PatientDAO();
        Patient patient = patientDAO.getPatientById(patientId);
        session.setAttribute("patient", patient);
        List<PatientRecord> records = patientRecordDAO.getRecordsByPatientId(patientId);
        request.setAttribute("records", records);
        request.getRequestDispatcher("ViewRecord.jsp").forward(request, response);
    }
}
