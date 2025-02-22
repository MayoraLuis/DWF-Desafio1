package edu.udb.gestionrrhh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/GestionRRHH?serverTimezone=UTC";
    private static final String USUARIO = "gestionrrhh_user"; // Nuevo usuario sin contraseña
    private static final String CONTRASEÑA = ""; // Dejar vacío

    public static Connection obtenerConexion() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
            throw ex;
        }
    }
}