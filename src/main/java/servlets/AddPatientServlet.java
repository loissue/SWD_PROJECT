package servlets;

import DAO.PatientDAO;
import Models.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {
    private PatientDAO patientDAO;

    @Override
    public void init() throws ServletException {
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String emergency = request.getParameter("emergency");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        Patient newPatient = new Patient();
        newPatient.setName(name);
        newPatient.setPhone(phone);
        newPatient.setAddress(address);
        newPatient.setEmergency(emergency);
        newPatient.setStatus(status);

        patientDAO.addPatient(newPatient);

        response.sendRedirect("patient-list");
    }
}
