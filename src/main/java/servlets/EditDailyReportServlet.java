package servlets;

import DAO.PatientReportDailyDAO;
import Models.PatientDailyReport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editDailyReport")
public class EditDailyReportServlet extends HttpServlet {

    private PatientReportDailyDAO patientReportDailyDAO;

    @Override
    public void init() {
        patientReportDailyDAO = new PatientReportDailyDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        PatientDailyReport report = patientReportDailyDAO.getDailyReportById(reportId);
        request.setAttribute("report", report);
        request.setAttribute("recordId", reportId);

        request.getRequestDispatcher("/editDailyReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        String symtoms = request.getParameter("symtoms");
        String rfe = request.getParameter("rfe");
        String testAndResult = request.getParameter("testAndResult");
        String treatmentProgress = request.getParameter("treatmentProgress");
        String diagnose = request.getParameter("diagnose");
        String note = request.getParameter("note");

        PatientDailyReport report = new PatientDailyReport();
        report.setReportId(reportId);
        report.setSymtoms(symtoms);
        report.setRfe(rfe);
        report.setTestAndResult(testAndResult);
        report.setTreatmentProgress(treatmentProgress);
        report.setDiagnose(diagnose);
        report.setNote(note);

        patientReportDailyDAO.updateDailyReport(report);
        response.sendRedirect("viewDailyReport?recordId=" + request.getSession().getAttribute("recordId"));
    }
}
