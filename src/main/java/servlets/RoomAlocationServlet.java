package servlets;

import DAO.RoomDAO;
import DAO.BedDAO;
import Models.Room;
import Models.Bed;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/roomAllocation")
public class RoomAlocationServlet extends HttpServlet {
    private RoomDAO roomDAO;
    private BedDAO bedDAO;

    @Override
    public void init() {
        roomDAO = new RoomDAO();
        bedDAO = new BedDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Room> rooms = roomDAO.getAllRooms();
        request.setAttribute("rooms", rooms);

        String roomIdParam = request.getParameter("roomId");
        if (roomIdParam != null && !roomIdParam.isEmpty()) {
            int roomId = Integer.parseInt(roomIdParam);
            List<Bed> beds = bedDAO.getBedsByRoomId(roomId);
            request.setAttribute("beds", beds);
        }

        request.getRequestDispatcher("roomAllocation.jsp").forward(request, response);
    }
}
