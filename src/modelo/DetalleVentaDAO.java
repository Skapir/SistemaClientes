package modelo;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class DetalleVentaDAO {

    public boolean insertarDetalle(DetalleVenta detalle) {
        String sql = "INSERT INTO detalle_venta (venta_id, producto_id, cantidad, precio, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, detalle.getVentaId());
            stmt.setString(2, detalle.getProductoId());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getPrecio());
            stmt.setDouble(5, detalle.getSubtotal());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar detalle de venta: " + e.getMessage());
            return false;
        }
    }
    public List<DetalleVenta> obtenerDetallesPorVenta(int ventaId) {
    List<DetalleVenta> lista = new ArrayList<>();
    String sql = "SELECT * FROM detalle_venta WHERE venta_id = ?";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, ventaId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            DetalleVenta d = new DetalleVenta(
                rs.getInt("id"),
                rs.getInt("venta_id"),
                rs.getString("producto_id"),
                rs.getInt("cantidad"),
                rs.getDouble("precio"),
                rs.getDouble("subtotal")
            );
            lista.add(d);
        }
    } catch (Exception e) {
        System.err.println("❌ Error al obtener detalles: " + e.getMessage());
    }
    return lista;
}
}
