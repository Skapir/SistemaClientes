package modelo;

import conexion.ConexionBD;
import java.sql.*;
import java.util.*;

public class ProveedorDAO {
    
    public boolean insertar(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO proveedor (nombre, telefono, direccion, razon_social) VALUES (?, ?, ?, ?)";

        try {
            conn = ConexionBD.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getTelefono());
            stmt.setString(3, proveedor.getDireccion());
            stmt.setString(4, proveedor.getRazonSocial());

            stmt.executeUpdate();
            System.out.println("✅ Proveedor registrado con éxito.");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar proveedor: " + e.getMessage());
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

    public List<Proveedor> listar() {
           List<Proveedor> lista = new ArrayList<>();
           Connection conn = null;
           PreparedStatement stmt = null;
           ResultSet rs = null;

           try {
               conn = ConexionBD.conectar();
               String sql = "SELECT * FROM proveedor";
               stmt = conn.prepareStatement(sql);
               rs = stmt.executeQuery();

               while (rs.next()) {
                   Proveedor proveedor = new Proveedor();
                   proveedor.setId(rs.getInt("id"));
                   proveedor.setNombre(rs.getString("nombre"));
                   proveedor.setTelefono(rs.getString("telefono"));
                   proveedor.setDireccion(rs.getString("direccion"));
                   proveedor.setRazonSocial(rs.getString("razon_social"));

                   lista.add(proveedor);
               }
           } catch (SQLException e) {
               System.err.println("❌ Error al listar proveedores: " + e.getMessage());
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
    public boolean actualizar(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.conectar();
            String sql = "UPDATE proveedor SET nombre=?, telefono=?, direccion=?, razon_social=? WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getTelefono());
            stmt.setString(3, proveedor.getDireccion());
            stmt.setString(4, proveedor.getRazonSocial());
            stmt.setInt(5, proveedor.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar proveedor: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.err.println("⚠️ Error al cerrar conexión: " + ex.getMessage());
            }
        }
    }
    public boolean eliminar(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.conectar();
            String sql = "DELETE FROM proveedor WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar proveedor: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.err.println("⚠️ Error al cerrar conexión: " + ex.getMessage());
            }
        }
    }


 }
