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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        ContratacionesDAO dao = new ContratacionesDAO();

        if ("insertar".equals(action)) {
            int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            int idCargo = Integer.parseInt(request.getParameter("idCargo"));
            int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
            String fechaContratacion = request.getParameter("fechaContratacion");
            double salario = Double.parseDouble(request.getParameter("salario"));
            boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

            Contrataciones nueva = new Contrataciones(0, idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado);
            dao.insertarContratacion(nueva);
        } else if ("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("idContratacion"));
            dao.eliminarContratacion(id);
        }

        response.sendRedirect("contrataciones");
    }
}

