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

@WebServlet("/reporteEmpleados")
public class ReporteEmpleadosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener lista de empleados desde la BD
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> listaEmpleados = empleadoDAO.obtenerEmpleados();

        // Enviar lista a la vista
        request.setAttribute("empleados", listaEmpleados);
        request.getRequestDispatcher("reporteEmpleados.jsp").forward(request, response);
    }
}
