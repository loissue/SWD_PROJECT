package servlets;

import DAO.RoomDAO;
import Models.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/rooms")
public class RoomServlet extends HttpServlet {
    private RoomDAO roomDAO;

    @Override
    public void init() {
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
                listRooms(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertRoom(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateRoom(request, response);
                break;
            case "delete":
                deleteRoom(request, response);
                break;
            default:
                listRooms(request, response);
                break;
        }
    }

    private void listRooms(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Room> rooms = roomDAO.getAllRooms();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("roomList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("roomForm.jsp").forward(request, response);
    }

    private void insertRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        Room newRoom = new Room();
        newRoom.setName(name);
        newRoom.setType(type);
        roomDAO.addRoom(newRoom);
        response.sendRedirect("rooms?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Room existingRoom = roomDAO.getRoomById(id);
        request.setAttribute("room", existingRoom);
        request.getRequestDispatcher("roomForm.jsp").forward(request, response);
    }

    private void updateRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        Room room = new Room();
        room.setId(id);
        room.setName(name);
        room.setType(type);
        roomDAO.updateRoom(room);
        response.sendRedirect("rooms?action=list");
    }

    private void deleteRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        roomDAO.deleteRoom(id);
        response.sendRedirect("rooms?action=list");
    }
}
