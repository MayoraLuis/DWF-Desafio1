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

        if (action == null) {
            processRequest(request, response);
            return;
        }

        TipoContratacionDAO dao = new TipoContratacionDAO();

        if ("eliminar".equals(action)) {
            String idStr = request.getParameter("idTipoContratacion");

            // 🚨 Depuración en consola
            System.out.println("📌 Acción Eliminar - ID recibido: " + idStr);

            if (idStr == null || idStr.isEmpty()) {
                System.out.println("❌ Error: idTipoContratacion es null o vacío");
                response.sendRedirect("tiposContratacion");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: idTipoContratacion no es un número válido -> " + idStr);
                response.sendRedirect("tiposContratacion");
                return;
            }

            boolean eliminado = dao.eliminarTipoContratacion(id);

            if (eliminado) {
                System.out.println("✅ Tipo de contratación eliminado correctamente.");
            } else {
                System.out.println("❌ Error al eliminar tipo de contratación. Puede estar relacionado con restricciones de clave foránea.");
            }

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
        }



if ("actualizar".equals(action)) {
        String idStr = request.getParameter("idTipoContratacion");

        if (idStr == null || idStr.isEmpty()) {
            System.out.println("❌ Error: idTipoContratacion es nulo o vacío");
            response.sendRedirect("contrataciones");
            return;
        }

        int idTipoContratacion;
        try {
            idTipoContratacion = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: idTipoContratacion no es un número válido -> " + idStr);
            response.sendRedirect("contrataciones");
            return;
        }

        String tipoContratacion = request.getParameter("tipoContratacion");

        // Crear objeto actualizado
        TipoContratacion tipoActualizado = new TipoContratacion(idTipoContratacion, tipoContratacion);

        // Ejecutar la actualización en la BD
        boolean actualizado = dao.actualizarTipoContratacion(tipoActualizado);

        if (actualizado) {
            System.out.println("✅ Tipo de contratación actualizado con éxito.");
        } else {
            System.out.println("❌ Error al actualizar el tipo de contratación.");
        }
    }

    // Redirigir a la lista de contrataciones después de actualizar
    response.sendRedirect("contrataciones");
}
}

