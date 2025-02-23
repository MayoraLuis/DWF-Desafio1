package edu.udb.gestionrrhh.dao;

import edu.udb.gestionrrhh.model.TipoContratacion;
import edu.udb.gestionrrhh.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContratacionDAO {

    // Obtener todos los tipos de contratación
    public List<TipoContratacion> obtenerTiposContratacion() {
        List<TipoContratacion> tipos = new ArrayList<>();
        String sql = "SELECT * FROM TipoContratacion";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TipoContratacion tipo = new TipoContratacion(
                        rs.getInt("idTipoContratacion"),
                        rs.getString("tipoContratacion")
                );
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener tipos de contratación: " + e.getMessage());
        }
        return tipos;
    }

    // Obtener un tipo de contratación por ID
    public TipoContratacion obtenerTipoContratacionPorId(int id) {
        TipoContratacion tipo = null;
        String sql = "SELECT * FROM tiposContratacion WHERE idTipoContratacion = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tipo = new TipoContratacion(
                        rs.getInt("idTipoContratacion"),
                        rs.getString("tipoContratacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al obtener tipo de contratación por ID: " + e.getMessage());
        }
        return tipo;
    }

    // Insertar un nuevo tipo de contratación
    public boolean insertarTipoContratacion(TipoContratacion tipo) {
        String sql = "INSERT INTO TipoContratacion (tipoContratacion) VALUES (?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo.getTipoContratacion());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar tipo de contratación: " + e.getMessage());
            return false;
        }
    }

    // Actualizar un tipo de contratación existente
    public boolean actualizarTipoContratacion(TipoContratacion tipo) {
        String sql = "UPDATE TipoContratacion SET tipoContratacion = ? WHERE idTipoContratacion = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo.getTipoContratacion());
            stmt.setInt(2, tipo.getIdTipoContratacion());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al actualizar tipo de contratación: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un tipo de contratación
    public boolean eliminarTipoContratacion(int idTipoContratacion) {
        String sql = "DELETE FROM tiposContratacion WHERE idTipoContratacion=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTipoContratacion);

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("✅ Tipo de contratación eliminado correctamente.");
                return true;
            } else {
                System.out.println("❌ No se encontró el tipo de contratación con ID " + idTipoContratacion);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error SQL al eliminar tipo de contratación: " + e.getMessage());
            return false;
        }
    }
}