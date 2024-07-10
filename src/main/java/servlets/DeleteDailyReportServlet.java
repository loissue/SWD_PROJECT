package servlets;

import DAO.PatientReportDailyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteDailyReport")
public class DeleteDailyReportServlet extends HttpServlet {

    private PatientReportDailyDAO patientReportDailyDAO;

    @Override
    public void init() {
        patientReportDailyDAO = new PatientReportDailyDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        patientReportDailyDAO.deleteDailyReport(reportId);
        response.sendRedirect("viewDailyReport?recordId=" + request.getSession().getAttribute("recordId"));
    }
}
