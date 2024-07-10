package servlets;

import DAO.PatientRecordDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteRecord")
public class DeleteRecordServlet extends HttpServlet {

    private PatientRecordDAO patientRecordDAO;

    @Override
    public void init() {
        patientRecordDAO = new PatientRecordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int recordId = Integer.parseInt(request.getParameter("recordId"));
        patientRecordDAO.deleteRecord(recordId);
        Integer patientId = (Integer) session.getAttribute("patientId");
        response.sendRedirect("viewRecord?patientId=" + patientId);
    }
}
