package modelo;

import conexion.ConexionBD;
import java.sql.*;
import java.util.*;

public class ProductoDAO {

    public boolean insertar(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO producto (codigo, nombre, id_proveedor, precio, stock) VALUES (?, ?, ?, ?, ?)";


        try {
            conn = ConexionBD.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, producto.getCodigo());
            stmt.setString(2, producto.getNombre());
            stmt.setInt(3, producto.getIdProveedor());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());

            stmt.executeUpdate();
            System.out.println("✅ Producto registrado con éxito.");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar producto: " + e.getMessage());
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

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.conectar();
            String sql = "SELECT * FROM producto";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setIdProveedor(rs.getInt("id_proveedor"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

                lista.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar productos: " + e.getMessage());
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

    public boolean actualizar(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.conectar();
            String sql = "UPDATE producto SET codigo=?, nombre=?, id_proveedor=?, precio=?, stock=? WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, producto.getCodigo());
            stmt.setString(2, producto.getNombre());
            stmt.setInt(3, producto.getIdProveedor()); // <- debe ser INT
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());
            stmt.setInt(6, producto.getId()); // ID del producto que se actualiza

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar producto: " + e.getMessage());
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
            String sql = "DELETE FROM producto WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar producto: " + e.getMessage());
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
    
    public Producto buscarPorCodigo(String codigo) {
    String sql = "SELECT * FROM producto WHERE codigo = ?";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, codigo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Producto p = new Producto();
            p.setId(rs.getInt("id"));
            p.setCodigo(rs.getString("codigo"));
            p.setNombre(rs.getString("nombre"));
            p.setIdProveedor(rs.getInt("id_proveedor"));
            p.setPrecio(rs.getDouble("precio"));
            p.setStock(rs.getInt("stock"));
            return p;
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al buscar producto: " + e.getMessage());
    }

    return null;
}
}
