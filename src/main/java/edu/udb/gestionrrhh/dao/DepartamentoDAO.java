package edu.udb.gestionrrhh.dao;

import edu.udb.gestionrrhh.model.Departamento;
import edu.udb.gestionrrhh.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


    public class DepartamentoDAO {

        // Método para obtener todos los departamentos
        public List<Departamento> obtenerDepartamentos() {
            List<Departamento> departamentos = new ArrayList<>();
            String sql = "SELECT * FROM Departamento";

            try (Connection conn = ConexionBD.obtenerConexion();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Departamento dep = new Departamento(
                            rs.getInt("idDepartamento"),
                            rs.getString("nombreDepartamento"),
                            rs.getString("descripcionDepartamento")
                    );
                    departamentos.add(dep);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al obtener los departamentos: " + e.getMessage());
            }
            return departamentos;
        }

        // Método para obtener un departamento por ID
        public Departamento obtenerDepartamentoPorId(int idDepartamento) {
            String sql = "SELECT * FROM Departamento WHERE idDepartamento = ?";
            try (Connection conn = ConexionBD.obtenerConexion();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idDepartamento);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new Departamento(
                            rs.getInt("idDepartamento"),
                            rs.getString("nombreDepartamento"),
                            rs.getString("descripcionDepartamento")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al obtener departamento por ID: " + e.getMessage());
            }
            return null;
        }


        // Insertar un nuevo departamento
        public boolean insertarDepartamento(Departamento departamento) {
            String sql = "INSERT INTO Departamento (nombreDepartamento, descripcionDepartamento) VALUES (?, ?)";

            try (Connection conn = ConexionBD.obtenerConexion();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, departamento.getNombreDepartamento());
                stmt.setString(2, departamento.getDescripcionDepartamento());

                int filasAfectadas = stmt.executeUpdate();
                System.out.println("Departamento insertado: " + filasAfectadas);
                return filasAfectadas > 0;
            } catch (SQLException e) {
                System.out.println("Error al insertar departamento: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

        // Actualizar un departamento
        public boolean actualizarDepartamento(Departamento departamento) {
            String sql = "UPDATE Departamento SET nombreDepartamento = ?, descripcionDepartamento = ? WHERE idDepartamento = ?";

            try (Connection conn = ConexionBD.obtenerConexion();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, departamento.getNombreDepartamento());
                stmt.setString(2, departamento.getDescripcionDepartamento());
                stmt.setInt(3, departamento.getIdDepartamento());

                int filasAfectadas = stmt.executeUpdate();
                System.out.println("Filas actualizadas: " + filasAfectadas);

                return filasAfectadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(" Error al actualizar departamento: " + e.getMessage());
                return false;
            }
        }

        public boolean eliminarDepartamento(int id) {
            String sql = "DELETE FROM Departamento WHERE idDepartamento = ?";

            try (Connection conn = ConexionBD.obtenerConexion();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);

                int filasAfectadas = stmt.executeUpdate();
                System.out.println(" Filas eliminadas: " + filasAfectadas);

                return filasAfectadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(" Error al eliminar departamento: " + e.getMessage());
                return false;
            }
        }
    }
