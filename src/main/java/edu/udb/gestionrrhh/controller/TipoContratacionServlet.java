package edu.udb.gestionrrhh.controller;

import edu.udb.gestionrrhh.dao.TipoContratacionDAO;
import edu.udb.gestionrrhh.model.TipoContratacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tiposContratacion")
public class TipoContratacionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TipoContratacionDAO dao = new TipoContratacionDAO();
        List<TipoContratacion> lista = dao.obtenerTiposContratacion();
        request.setAttribute("tiposContratacion", lista);
        request.getRequestDispatcher("tiposContratacion.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        TipoContratacionDAO dao = new TipoContratacionDAO();

        if (action == null) {
            processRequest(request, response);
            return;
        }

        if ("editar".equals(action)) {
            String idStr = request.getParameter("idTipoContratacion");
            if (idStr == null || idStr.isEmpty()) {
                System.out.println(" Error: idTipoContratacion es nulo o vacío.");
                response.sendRedirect("tiposContratacion");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println(" Error: idTipoContratacion no es un número válido.");
                response.sendRedirect("tiposContratacion");
                return;
            }

            TipoContratacion tipo = dao.obtenerTipoContratacionPorId(id);
            if (tipo != null) {
                request.setAttribute("tipoContratacion", tipo);
                request.getRequestDispatcher("editarTipoContratacion.jsp").forward(request, response);
            } else {
                System.out.println(" No se encontró el tipo de contratación con ID: " + id);
                response.sendRedirect("tiposContratacion");
            }
        } else if ("eliminar".equals(action)) {
            String idStr = request.getParameter("idTipoContratacion");

            if (idStr == null || idStr.isEmpty()) {
                System.out.println(" Error: idTipoContratacion es nulo o vacío para eliminación.");
                response.sendRedirect("tiposContratacion");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println(" Error: idTipoContratacion no es un número válido para eliminación.");
                response.sendRedirect("tiposContratacion");
                return;
            }

            boolean eliminado = dao.eliminarTipoContratacion(id);

            if (eliminado) {
                System.out.println(" Tipo de contratación eliminado correctamente.");
            } else {
                System.out.println(" No se pudo eliminar el tipo de contratación con ID: " + id);
            }

            // Redirigir a la lista de tipos de contratación
            response.sendRedirect("tiposContratacion");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        TipoContratacionDAO dao = new TipoContratacionDAO();

        if ("insertar".equals(action)) {
            String tipo = request.getParameter("tipoContratacion");

            TipoContratacion nuevo = new TipoContratacion(0, tipo);
            dao.insertarTipoContratacion(nuevo);
        } else if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("idTipoContratacion"));
            String tipo = request.getParameter("tipoContratacion");

            TipoContratacion tipoActualizado = new TipoContratacion(id, tipo);
            dao.actualizarTipoContratacion(tipoActualizado);
        }

        response.sendRedirect("tiposContratacion");
    }
}

