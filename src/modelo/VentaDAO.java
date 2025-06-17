/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import conexion.ConexionBD;
import java.sql.*;
import java.util.*;
/**
 *
 * @author SKAPIR
 */
public class VentaDAO {
     
    public int insertarVenta(Venta venta) {
    int idGenerado = -1;
    String sql = "INSERT INTO venta (cliente_id, fecha, total) VALUES (?, ?, ?)";

    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setInt(1, venta.getClienteId());
        stmt.setString(2, venta.getFecha());
        stmt.setDouble(3, venta.getTotal());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            idGenerado = rs.getInt(1);
        }
    } catch (SQLException e) {
        System.err.println("Error insertando venta: " + e.getMessage());
    }

    return idGenerado;
} 
    public List<Venta> listarTodas() {
    List<Venta> lista = new ArrayList<>();
    String sql = "SELECT v.id, c.nombre AS cliente_nombre, v.fecha, v.total " +
                 "FROM venta v INNER JOIN cliente c ON v.cliente_id = c.id";

    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Venta venta = new Venta();
            venta.setId(rs.getInt("id"));
            venta.setClienteNombre(rs.getString("cliente_nombre")); // nuevo campo
            venta.setFecha(rs.getString("fecha"));
            venta.setTotal(rs.getDouble("total"));
            lista.add(venta);
        }

    } catch (SQLException e) {
        System.err.println("Error al listar ventas: " + e.getMessage());
    }

    return lista;
}
    public Venta obtenerVentaPorId(int idVenta) {
    Venta venta = new Venta();
    String sql = "SELECT v.id, v.fecha, v.total, c.nombre FROM venta v JOIN cliente c ON v.cliente_id = c.id WHERE v.id = ?";

    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idVenta);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            venta.setId(rs.getInt("id"));
            venta.setFecha(rs.getString("fecha"));
            venta.setTotal(rs.getDouble("total"));
            venta.setClienteNombre(rs.getString("nombre"));
        }

    } catch (SQLException e) {
        System.err.println("Error al buscar venta: " + e.getMessage());
    }

    return venta;
}
    public List<Venta> buscarVentasPorDni(String dni) {
    List<Venta> lista = new ArrayList<>();
    String sql = "SELECT v.id, v.fecha, v.total, c.nombre "
               + "FROM venta v JOIN cliente c ON v.cliente_id = c.id "
               + "WHERE c.dni LIKE ?";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + dni + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Venta v = new Venta();
            v.setId(rs.getInt("id"));
            v.setFecha(rs.getString("fecha"));
            v.setTotal(rs.getDouble("total"));
            v.setClienteNombre(rs.getString("nombre"));
            lista.add(v);
        }
    } catch (Exception e) {
        System.err.println("❌ Error al buscar ventas por DNI: " + e.getMessage());
    }
    return lista;
}
}
