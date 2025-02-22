package edu.udb.gestionrrhh.dao;

import edu.udb.gestionrrhh.model.Cargos;
import edu.udb.gestionrrhh.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargosDAO {

    // Obtener un cargo por su ID
    public Cargos obtenerCargoPorId(int idCargo) {
        String sql = "SELECT * FROM Cargos WHERE idCargo = ?";
        Cargos cargo = null;

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCargo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cargo = new Cargos(
                        rs.getInt("idCargo"),
                        rs.getString("cargo"),
                        rs.getString("descripcionCargo"),
                        rs.getBoolean("jefatura")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al obtener cargo por ID: " + e.getMessage());
        }
        return cargo;
    }

    // Obtener todos los cargos
    public List<Cargos> obtenerCargos() {
        List<Cargos> cargos = new ArrayList<>();
        String sql = "SELECT * FROM Cargos";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cargos cargo = new Cargos(
                        rs.getInt("idCargo"),
                        rs.getString("cargo"),
                        rs.getString("descripcionCargo"),
                        rs.getBoolean("jefatura")
                );
                cargos.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al obtener cargos: " + e.getMessage());
        }
        return cargos;
    }

    // Insertar un nuevo cargo
    public boolean insertarCargo(Cargos cargo) {
        String sql = "INSERT INTO Cargos (cargo, descripcionCargo, jefatura) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getCargo());
            stmt.setString(2, cargo.getDescripcionCargo());
            stmt.setBoolean(3, cargo.isJefatura());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al insertar cargo: " + e.getMessage());
            return false;
        }
    }

    // Actualizar un cargo existente
    public boolean actualizarCargo(Cargos cargo) {
        String sql = "UPDATE Cargos SET cargo=?, descripcionCargo=?, jefatura=? WHERE idCargo=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getCargo());
            stmt.setString(2, cargo.getDescripcionCargo());
            stmt.setBoolean(3, cargo.isJefatura());
            stmt.setInt(4, cargo.getIdCargo());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al actualizar cargo: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un cargo
    public boolean eliminarCargo(int idCargo) {
        String sql = "DELETE FROM Cargos WHERE idCargo=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCargo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Error al eliminar cargo: " + e.getMessage());
            return false;
        }
    }
}

