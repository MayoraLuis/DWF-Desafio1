package edu.udb.gestionrrhh.controller;

import edu.udb.gestionrrhh.dao.EmpleadoDAO;
import edu.udb.gestionrrhh.model.Empleado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> listaEmpleados = empleadoDAO.obtenerEmpleados();

        System.out.println(" Cargando " + listaEmpleados.size() + " empleados en la vista.");

        request.setAttribute("empleados", listaEmpleados);
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            processRequest(request, response);
            return;
        }

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        if ("editar".equals(action)) {
            String idStr = request.getParameter("idEmpleado");

            if (idStr == null || idStr.isEmpty()) {
                System.out.println(" Error: idEmpleado es nulo o vacío para edición");
                response.sendRedirect("empleados");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println(" Error: idEmpleado no es un número válido para edición");
                response.sendRedirect("empleados");
                return;
            }

            Empleado empleado = empleadoDAO.obtenerEmpleadoPorId(id);

            if (empleado != null) {
                request.setAttribute("empleado", empleado);
                request.getRequestDispatcher("editarEmpleado.jsp").forward(request, response);
            } else {
                System.out.println(" Error: No se encontró el empleado con ID " + id);
                response.sendRedirect("empleados");
            }
        } else if ("eliminar".equals(action)) {
            String idStr = request.getParameter("idEmpleado");

            if (idStr == null || idStr.isEmpty()) {
                System.out.println(" Error: idEmpleado es nulo o vacío para eliminación");
                response.sendRedirect("empleados");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println(" Error: idEmpleado no es un número válido para eliminación");
                response.sendRedirect("empleados");
                return;
            }

            boolean eliminado = empleadoDAO.eliminarEmpleado(id);

            if (eliminado) {
                System.out.println(" Empleado eliminado correctamente.");
            } else {
                System.out.println(" Error al eliminar empleado. Puede estar relacionado con otras tablas.");
            }

            response.sendRedirect("empleados");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        if ("insertar".equals(action)) {
            String numeroDui = request.getParameter("numeroDui");
            String nombre = request.getParameter("nombrePersona");
            String usuario = request.getParameter("usuario");
            String telefono = request.getParameter("numeroTelefono");
            String correo = request.getParameter("correoInstitucional");
            String fechaNacimiento = request.getParameter("fechaNacimiento");

            // ✅ Validación para evitar valores nulos o vacíos
            if (numeroDui == null || nombre == null || usuario == null || telefono == null || correo == null || fechaNacimiento == null ||
                    numeroDui.trim().isEmpty() || nombre.trim().isEmpty() || usuario.trim().isEmpty() || telefono.trim().isEmpty() || correo.trim().isEmpty() || fechaNacimiento.trim().isEmpty()) {

                System.out.println(" Error: Uno o más campos son nulos o vacíos en la inserción");
                response.sendRedirect("empleados?error=camposVacios");
                return;
            }

            Empleado nuevoEmp = new Empleado(0, numeroDui, nombre, usuario, telefono, correo, fechaNacimiento);
            boolean insertado = empleadoDAO.insertarEmpleado(nuevoEmp);

            if (insertado) {
                System.out.println(" Empleado insertado correctamente.");
            } else {
                System.out.println(" Error al insertar el empleado.");
            }
        } else if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("idEmpleado"));
            String numeroDui = request.getParameter("numeroDui");
            String nombre = request.getParameter("nombrePersona");
            String usuario = request.getParameter("usuario");
            String telefono = request.getParameter("numeroTelefono");
            String correo = request.getParameter("correoInstitucional");
            String fechaNacimiento = request.getParameter("fechaNacimiento");

            if (numeroDui == null || nombre == null || usuario == null || telefono == null || correo == null || fechaNacimiento == null ||
                    numeroDui.trim().isEmpty() || nombre.trim().isEmpty() || usuario.trim().isEmpty() || telefono.trim().isEmpty() || correo.trim().isEmpty() || fechaNacimiento.trim().isEmpty()) {

                System.out.println(" Error: Uno o más campos son nulos o vacíos en la actualización");
                response.sendRedirect("empleados?error=camposVacios");
                return;
            }

            Empleado empActualizado = new Empleado(id, numeroDui, nombre, usuario, telefono, correo, fechaNacimiento);
            boolean actualizado = empleadoDAO.actualizarEmpleado(empActualizado);

            if (actualizado) {
                System.out.println(" Empleado actualizado correctamente.");
            } else {
                System.out.println(" Error al actualizar el empleado.");
            }
        }

        response.sendRedirect("empleados");
    }
}