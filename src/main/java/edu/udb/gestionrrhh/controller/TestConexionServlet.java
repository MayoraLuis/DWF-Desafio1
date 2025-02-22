package edu.udb.gestionrrhh.controller;

import edu.udb.gestionrrhh.util.ConexionBD;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/testConexion")
public class TestConexionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection conn = ConexionBD.obtenerConexion();
            response.getWriter().println(" Conexión a la base de datos establecida correctamente.");
            conn.close();
        } catch (SQLException e) {
            response.getWriter().println(" Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
