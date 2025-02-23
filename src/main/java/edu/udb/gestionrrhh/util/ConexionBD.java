package edu.udb.gestionrrhh.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/gestionrrhh?serverTimezone=UTC";
    private static final String USUARIO = "gestionrrhh_user";
    private static final String CONTRASEÑA = "";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }
}