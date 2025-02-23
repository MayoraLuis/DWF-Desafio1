package edu.udb.gestionrrhh.controller;

import edu.udb.gestionrrhh.dao.ContratacionesDAO;
import edu.udb.gestionrrhh.model.Contrataciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/contrataciones")
public class ContratacionesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ContratacionesDAO dao = new ContratacionesDAO();
        List<Contrataciones> lista = dao.obtenerContrataciones();

        request.setAttribute("contrataciones", lista);
        request.getRequestDispatcher("contrataciones.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            processRequest(request, response);
            return;
        }

        ContratacionesDAO dao = new ContratacionesDAO();

        // 🔹 Acción: Editar contratación
        if ("editar".equals(action)) {
            String idStr = request.getParameter("idContratacion");

            if (idStr == null || idStr.isEmpty()) {
                System.out.println("❌ Error: idContratacion es nulo o vacío");
                response.sendRedirect("contrataciones");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: idContratacion no es un número válido -> " + idStr);
                response.sendRedirect("contrataciones");
                return;
            }

            // Obtener la contratación de la base de datos
            Contrataciones contratacion = dao.obtenerContratacionPorId(id);

            if (contratacion != null) {
                request.setAttribute("contratacion", contratacion);
                request.getRequestDispatcher("editarTipoContratacion.jsp").forward(request, response);
            } else {
                System.out.println("❌ Error: No se encontró la contratación con ID " + id);
                response.sendRedirect("contrataciones");
            }
        }

        // 🔹 Acción: Eliminar contratación
        else if ("eliminar".equals(action)) {
            String idStr = request.getParameter("idContratacion");

            if (idStr == null || idStr.isEmpty()) {
                System.out.println("❌ Error: idContratacion es nulo o vacío para eliminación");
                response.sendRedirect("contrataciones");
                return;
            }

            int idContratacion;
            try {
                idContratacion = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: idContratacion no es un número válido para eliminación");
                response.sendRedirect("contrataciones");
                return;
            }

            boolean eliminado = dao.eliminarContratacion(idContratacion);

            if (eliminado) {
                System.out.println("✅ Contratación eliminada correctamente.");
            } else {
                System.out.println("❌ Error al eliminar contratación.");
            }

            response.sendRedirect("contrataciones");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        ContratacionesDAO dao = new ContratacionesDAO();

        if ("insertar".equals(action)) {
            try {
                int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
                int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                int idCargo = Integer.parseInt(request.getParameter("idCargo"));
                int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
                String fechaContratacion = request.getParameter("fechaContratacion");
                double salario = Double.parseDouble(request.getParameter("salario"));
                boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

                // Validar que ningún campo sea nulo
                if (fechaContratacion == null || fechaContratacion.isEmpty()) {
                    System.out.println("❌ Error: La fecha de contratación es nula o vacía.");
                    response.sendRedirect("contrataciones");
                    return;
                }

                Contrataciones nuevaContratacion = new Contrataciones(0, idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado);
                boolean insertado = dao.insertarContratacion(nuevaContratacion);

                if (insertado) {
                    System.out.println("✅ Contratación agregada correctamente.");
                    processRequest(request, response);
                } else {
                    System.out.println("❌ Error al agregar la contratación.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Formato de número inválido - " + e.getMessage());
            }
        }

        response.sendRedirect("contrataciones");
    }
}