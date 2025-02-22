package edu.udb.gestionrrhh.controller;

import edu.udb.gestionrrhh.dao.DepartamentoDAO;
import edu.udb.gestionrrhh.model.Departamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/departamentos")
public class DepartamentoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        List<Departamento> listaDepartamentos = departamentoDAO.obtenerDepartamentos();
        request.setAttribute("departamentos", listaDepartamentos);
        request.getRequestDispatcher("departamentos.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(" Acción GET recibida: " + action);

        if (action == null) {
            processRequest(request, response);
            return;
        }

        DepartamentoDAO departamentoDAO = new DepartamentoDAO();

        if ("editar".equals(action)) {
            String idStr = request.getParameter("idDepartamento");

            if (idStr == null || idStr.isEmpty()) {
                System.out.println(" Error: idDepartamento es nulo o vacío");
                response.sendRedirect("departamentos");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println(" Error: idDepartamento no es un número válido -> " + idStr);
                response.sendRedirect("departamentos");
                return;
            }

            Departamento departamento = departamentoDAO.obtenerDepartamentoPorId(id);

            if (departamento != null) {
                request.setAttribute("departamento", departamento);
                request.getRequestDispatcher("editarDepartamento.jsp").forward(request, response);
            } else {
                System.out.println(" Error: No se encontró el departamento con ID " + id);
                response.sendRedirect("departamentos");
            }
        } else if ("eliminar".equals(action)) {
            String idStr = request.getParameter("idDepartamento");

            if (idStr == null || idStr.isEmpty()) {
                System.out.println(" Error: idDepartamento es nulo o vacío para eliminación");
                response.sendRedirect("departamentos");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println(" Error: idDepartamento no es un número válido para eliminación");
                response.sendRedirect("departamentos");
                return;
            }

            boolean eliminado = departamentoDAO.eliminarDepartamento(id);

            if (eliminado) {
                System.out.println(" Departamento eliminado correctamente.");
            } else {
                System.out.println(" Error al eliminar departamento. Puede estar relacionado con otras tablas.");
            }

            response.sendRedirect("departamentos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(" Acción POST recibida: " + action);

        if (action == null) {
            response.sendRedirect("departamentos");
            return;
        }

        DepartamentoDAO departamentoDAO = new DepartamentoDAO();

        if ("insertar".equals(action)) {
            String nombre = request.getParameter("nombreDepartamento");
            String descripcion = request.getParameter("descripcionDepartamento");

            if (nombre == null || nombre.isEmpty()) {
                System.out.println(" Error: Nombre de departamento es nulo o vacío");
                response.sendRedirect("departamentos");
                return;
            }

            Departamento nuevoDep = new Departamento(0, nombre, descripcion);
            departamentoDAO.insertarDepartamento(nuevoDep);
        } else if ("actualizar".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("idDepartamento"));
                String nombre = request.getParameter("nombreDepartamento");
                String descripcion = request.getParameter("descripcionDepartamento");

                if (nombre == null || nombre.isEmpty()) {
                    System.out.println(" Error: Nombre de departamento es nulo o vacío");
                    response.sendRedirect("departamentos");
                    return;
                }

                Departamento depActualizado = new Departamento(id, nombre, descripcion);
                boolean actualizado = departamentoDAO.actualizarDepartamento(depActualizado);

                if (actualizado) {
                    System.out.println(" Departamento actualizado con éxito");
                } else {
                    System.out.println(" Error: No se pudo actualizar el departamento");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Error: idDepartamento no es un número válido");
            }
        }

        response.sendRedirect("departamentos");
    }
}