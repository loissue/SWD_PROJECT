package servlets;

import DAO.BedDAO;
import DAO.RoomDAO;
import Models.Bed;
import Models.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/beds")
public class BedServlet extends HttpServlet {
    private BedDAO bedDAO;
    private RoomDAO roomDAO;

    @Override
    public void init() {
        bedDAO = new BedDAO();
        roomDAO = new RoomDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listBeds(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertBed(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateBed(request, response);
                break;
            case "delete":
                deleteBed(request, response);
                break;
            default:
                listBeds(request, response);
                break;
        }
    }

    private void listBeds(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        List<Bed> beds = bedDAO.getBedsByRoomId(roomId);
        request.setAttribute("beds", beds);
        request.getRequestDispatcher("bedList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Room> rooms = roomDAO.getAllRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("bedForm.jsp").forward(request, response);
    }

    private void insertBed(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        Room room = new Room();
        room.setId(roomId);

        Bed newBed = new Bed();
        newBed.setName(name);
        newBed.setStatus(status);
        newBed.setRoom(room);

        bedDAO.addBed(newBed);
        response.sendRedirect("beds?action=list&roomId=" + roomId);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bedId = Integer.parseInt(request.getParameter("bedId"));
        Bed existingBed = bedDAO.getBedById(bedId);
        List<Room> rooms = roomDAO.getAllRooms();
        request.setAttribute("bed", existingBed);
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("bedForm.jsp").forward(request, response);
    }

    private void updateBed(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int bedId = Integer.parseInt(request.getParameter("bedId"));
        String name = request.getParameter("name");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));

        Room room = new Room();
        room.setId(roomId);

        Bed bed = new Bed();
        bed.setBedId(bedId);
        bed.setName(name);
        bed.setStatus(status);
        bed.setRoom(room);

        bedDAO.updateBed(bed);
        response.sendRedirect("beds?action=list&roomId=" + roomId);
    }

    private void deleteBed(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int bedId = Integer.parseInt(request.getParameter("bedId"));
        Bed bed = bedDAO.getBedById(bedId);
        int roomId = bed.getRoom().getId();

        bedDAO.deleteBed(bedId);
        response.sendRedirect("beds?action=list&roomId=" + roomId);
    }
}
