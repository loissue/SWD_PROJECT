package servlets;


import DAO.PatientReportDailyDAO;
import Models.Patient;
import Models.PatientDailyReport;
import Models.PatientRecord;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/addDailyReport")
public class AddDailyReportServlet extends HttpServlet {

    private PatientReportDailyDAO patientDailyReportDAO;

    @Override
    public void init() {
        patientDailyReportDAO = new PatientReportDailyDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int recordId = Integer.parseInt(request.getParameter("recordId"));
        Patient patient = (Patient)session.getAttribute("patient");

        String symtoms = request.getParameter("symtoms");
        String rfe = request.getParameter("rfe");
        String testAndResult = request.getParameter("testAndResult");
        String treatmentProgress = request.getParameter("treatmentProgress");
        String diagnose = request.getParameter("diagnose");
        String note = request.getParameter("note");

        PatientDailyReport report = new PatientDailyReport();
        report.setSymtoms(symtoms);
        report.setRfe(rfe);
        report.setTestAndResult(testAndResult);
        report.setTreatmentProgress(treatmentProgress);
        report.setDiagnose(diagnose);
        report.setTime(new Date());
        report.setNote(note);
        report.setPatient(patient);
        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setRecordId(recordId);
        report.setPatientRecord(patientRecord);

        patientDailyReportDAO.addPatientReport(report);

        response.sendRedirect("viewDailyReport?recordId=" + recordId);
    }
}


