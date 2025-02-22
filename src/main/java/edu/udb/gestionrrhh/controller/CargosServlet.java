package edu.udb.gestionrrhh.controller;

import edu.udb.gestionrrhh.dao.CargosDAO;
import edu.udb.gestionrrhh.model.Cargos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cargos")
public class CargosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CargosDAO dao = new CargosDAO();
        List<Cargos> lista = dao.obtenerCargos();
        request.setAttribute("cargos", lista);
        request.getRequestDispatcher("cargos.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        CargosDAO dao = new CargosDAO();

        if (action == null) {
            processRequest(request, response);
            return;
        }

        if ("editar".equals(action)) {
            int idCargo = Integer.parseInt(request.getParameter("idCargo"));
            Cargos cargo = dao.obtenerCargoPorId(idCargo);

            if (cargo != null) {
                request.setAttribute("cargo", cargo);
                request.getRequestDispatcher("editarCargo.jsp").forward(request, response);
            } else {
                response.sendRedirect("cargos");
            }
        } else if ("eliminar".equals(action)) {
            int idCargo = Integer.parseInt(request.getParameter("idCargo"));
            dao.eliminarCargo(idCargo);
            response.sendRedirect("cargos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        CargosDAO dao = new CargosDAO();

        if ("insertar".equals(action)) {
            String nombre = request.getParameter("cargo");
            String descripcion = request.getParameter("descripcionCargo");
            boolean jefatura = Boolean.parseBoolean(request.getParameter("jefatura"));

            Cargos nuevo = new Cargos(0, nombre, descripcion, jefatura);
            dao.insertarCargo(nuevo);
        } else if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("idCargo"));
            String nombre = request.getParameter("cargo");
            String descripcion = request.getParameter("descripcionCargo");
            boolean jefatura = Boolean.parseBoolean(request.getParameter("jefatura"));

            Cargos cargoActualizado = new Cargos(id, nombre, descripcion, jefatura);
            dao.actualizarCargo(cargoActualizado);
        }

        response.sendRedirect("cargos");
    }
}


