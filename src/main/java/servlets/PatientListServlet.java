package servlets;

import DAO.PatientDAO;
import Models.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/patient-list")
public class PatientListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        PatientDAO patientDAO = new PatientDAO();
        List<Patient> patients;

        if (search != null && !search.isEmpty()) {
            patients = patientDAO.searchPatients(search);
        } else {
            patients = patientDAO.getPatients();
        }

        if (sort != null && !sort.isEmpty()) {
            patients = patientDAO.sortPatients(patients, sort, order);
        }

        request.setAttribute("patients", patients);
        request.setAttribute("sort", sort);
        request.setAttribute("order", order);
        request.getRequestDispatcher("patientList.jsp").forward(request, response);
    }
}
