package edu.udb.gestionrrhh.dao;

import edu.udb.gestionrrhh.model.Empleado;
import edu.udb.gestionrrhh.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    // Método para obtener todos los empleados
    public List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM Empleados";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getInt("idEmpleado"),
                        rs.getString("numeroDui"),
                        rs.getString("nombrePersona"),
                        rs.getString("usuario"),
                        rs.getString("numeroTelefono"),
                        rs.getString("correoInstitucional"),
                        rs.getString("fechaNacimiento")
                );
                empleados.add(emp);
            }
            System.out.println("Se obtuvieron " + empleados.size() + " empleados de la BD.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener empleados: " + e.getMessage());
        }
        return empleados;
    }

    // Método para obtener un empleado por ID
    public Empleado obtenerEmpleadoPorId(int idEmpleado) {
        String sql = "SELECT * FROM Empleados WHERE idEmpleado = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmpleado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getInt("idEmpleado"),
                            rs.getString("numeroDui"),
                            rs.getString("nombrePersona"),
                            rs.getString("usuario"),
                            rs.getString("numeroTelefono"),
                            rs.getString("correoInstitucional"),
                            rs.getString("fechaNacimiento")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener empleado por ID: " + e.getMessage());
        }
        return null;
    }

    //  Metodo para insertar un nuevo empleado
    public boolean insertarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO Empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println(" Insertando empleado: " + empleado);

            stmt.setString(1, empleado.getNumeroDui());
            stmt.setString(2, empleado.getNombrePersona());
            stmt.setString(3, empleado.getUsuario());
            stmt.setString(4, empleado.getNumeroTelefono());
            stmt.setString(5, empleado.getCorreoInstitucional());
            stmt.setString(6, empleado.getFechaNacimiento());

            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Empleado insertado en la base de datos. Filas afectadas: " + filasAfectadas);

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error SQL al insertar empleado: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar un empleado
    public boolean actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE Empleados SET numeroDui=?, nombrePersona=?, usuario=?, numeroTelefono=?, correoInstitucional=?, fechaNacimiento=? WHERE idEmpleado=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNumeroDui());
            stmt.setString(2, empleado.getNombrePersona());
            stmt.setString(3, empleado.getUsuario());
            stmt.setString(4, empleado.getNumeroTelefono());
            stmt.setString(5, empleado.getCorreoInstitucional());
            stmt.setString(6, empleado.getFechaNacimiento());
            stmt.setInt(7, empleado.getIdEmpleado());

            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Empleado actualizado correctamente.");
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un empleado
    public boolean eliminarEmpleado(int idEmpleado) {
        String sql = "DELETE FROM Empleados WHERE idEmpleado=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEmpleado);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Empleado eliminado correctamente.");
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }
}