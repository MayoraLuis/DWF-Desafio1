package edu.udb.gestionrrhh.dao;

import edu.udb.gestionrrhh.model.Contrataciones;
import edu.udb.gestionrrhh.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionesDAO {

    public List<Contrataciones> obtenerContrataciones() {
        List<Contrataciones> contrataciones = new ArrayList<>();
        String sql = "SELECT * FROM Contrataciones";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contrataciones contratacion = new Contrataciones(
                        rs.getInt("idContratacion"),
                        rs.getInt("idDepartamento"),
                        rs.getInt("idEmpleado"),
                        rs.getInt("idCargo"),
                        rs.getInt("idTipoContratacion"),
                        rs.getString("fechaContratacion"),
                        rs.getDouble("salario"),
                        rs.getBoolean("estado")
                );
                contrataciones.add(contratacion);
            }

            System.out.println(" Se obtuvieron " + contrataciones.size() + " contrataciones de la BD.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al obtener contrataciones: " + e.getMessage());
        }
        return contrataciones;
    }

    // Insertar una nueva contratación
    public boolean insertarContratacion(Contrataciones contratacion) {
        String sql = "INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contratacion.getIdDepartamento());
            stmt.setInt(2, contratacion.getIdEmpleado());
            stmt.setInt(3, contratacion.getIdCargo());
            stmt.setInt(4, contratacion.getIdTipoContratacion());
            stmt.setString(5, contratacion.getFechaContratacion());
            stmt.setDouble(6, contratacion.getSalario());
            stmt.setBoolean(7, contratacion.isEstado());

            int filasAfectadas = stmt.executeUpdate(); // Devuelve el número de filas afectadas
            if (filasAfectadas > 0) {
                System.out.println(" Contratación insertada correctamente en la base de datos.");
                return true;
            } else {
                System.out.println(" No se insertó la contratación.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error SQL al insertar contratación: " + e.getMessage());
            return false;
        }
    }

    //  Obtener contratación por ID (para edición)
    public Contrataciones obtenerContratacionPorId(int id) {
        Contrataciones contratacion = null;
        String sql = "SELECT idContratacion, idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado FROM Contrataciones WHERE idContratacion = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contratacion = new Contrataciones(
                        rs.getInt("idContratacion"),
                        rs.getInt("idDepartamento"),
                        rs.getInt("idEmpleado"),
                        rs.getInt("idCargo"),
                        rs.getInt("idTipoContratacion"),
                        rs.getString("fechaContratacion"),
                        rs.getDouble("salario"),
                        rs.getBoolean("estado")
                );
                System.out.println(" Contratación encontrada: " + contratacion.getIdContratacion());
            } else {
                System.out.println(" No se encontró contratación con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al obtener contratación por ID: " + e.getMessage());
        }
        return contratacion;
    }

    //  Actualizar contratación
    public boolean actualizarContratacion(Contrataciones contratacion) {
        String sql = "UPDATE Contrataciones SET idDepartamento=?, idEmpleado=?, idCargo=?, idTipoContratacion=?, fechaContratacion=?, salario=?, estado=? WHERE idContratacion=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contratacion.getIdDepartamento());
            stmt.setInt(2, contratacion.getIdEmpleado());
            stmt.setInt(3, contratacion.getIdCargo());
            stmt.setInt(4, contratacion.getIdTipoContratacion());
            stmt.setString(5, contratacion.getFechaContratacion());
            stmt.setDouble(6, contratacion.getSalario());
            stmt.setBoolean(7, contratacion.isEstado());
            stmt.setInt(8, contratacion.getIdContratacion());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println(" Contratación actualizada correctamente.");
            } else {
                System.out.println(" No se encontró contratación para actualizar.");
            }
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al actualizar contratación: " + e.getMessage());
            return false;
        }
    }

    //  Eliminar contratación
    public boolean eliminarContratacion(int idContratacion) {
        String sql = "DELETE FROM Contrataciones WHERE idContratacion=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idContratacion);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println(" Contratación eliminada correctamente.");
            } else {
                System.out.println(" No se encontró contratación para eliminar.");
            }
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al eliminar contratación: " + e.getMessage());
            return false;
        }
    }
}