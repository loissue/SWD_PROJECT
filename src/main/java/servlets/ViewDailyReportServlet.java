package servlets;

import Models.Patient;
import Models.PatientDailyReport;
import DAO.PatientReportDailyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewDailyReport")
public class ViewDailyReportServlet extends HttpServlet {

    private PatientReportDailyDAO patientReportDailyDAO;

    @Override
    public void init() {
        patientReportDailyDAO = new PatientReportDailyDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int recordId = Integer.parseInt(request.getParameter("recordId"));
        HttpSession session = request.getSession();

        Patient patient = (Patient) request.getAttribute("patientId");

        List<PatientDailyReport> reports = patientReportDailyDAO.getReportsByRecordId(recordId);
        request.setAttribute("reports", reports);
        session.setAttribute("recordId", recordId);

        request.getRequestDispatcher("viewDailyReport.jsp").forward(request, response);
    }
}

