package modelo;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;



public class ClienteDAO {

    public boolean insertar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.conectar();
            String sql = "INSERT INTO cliente (nombre, dni, correo) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDni());
            stmt.setString(3, cliente.getCorreo());

            stmt.executeUpdate();
            System.out.println("✅ Cliente registrado con éxito.");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cliente: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.err.println("⚠️ Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    
    public List<Cliente> listar() {
            List<Cliente> lista = new ArrayList<>();
            String sql = "SELECT * FROM cliente";
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = ConexionBD.conectar();
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setDni(rs.getString("dni"));
                    c.setCorreo(rs.getString("correo"));
                    lista.add(c);
                }

            } catch (SQLException e) {
                System.err.println("❌ Error al listar clientes: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.err.println("⚠️ Error al cerrar conexión: " + ex.getMessage());
                }
        }

        return lista;
    }
        
    public boolean actualizar(Cliente cliente) {
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = ConexionBD.conectar();
                String sql = "UPDATE cliente SET nombre=?, dni=?, correo=? WHERE id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, cliente.getNombre());
                stmt.setString(2, cliente.getDni());
                stmt.setString(3, cliente.getCorreo());
                stmt.setInt(4, cliente.getId());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("❌ Error al actualizar cliente: " + e.getMessage());
                return false;
            } finally {
                try {
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.err.println("⚠ Error al cerrar conexión: " + ex.getMessage());
                }
            }
        }
    
    public boolean eliminar(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "DELETE FROM cliente WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.err.println("⚠ Error al cerrar conexión: " + ex.getMessage());
            }
        }
    }
    
    public Cliente buscarPorDni(String dni) {
        Cliente c = null;
        String sql = "SELECT * FROM cliente WHERE dni = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                // Agrega otros campos si deseas
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar cliente: " + e.getMessage());
        }

        return c;
    }
}
