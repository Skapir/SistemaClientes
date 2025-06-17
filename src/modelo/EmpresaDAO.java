package modelo;

import java.sql.*;
import conexion.ConexionBD;

public class EmpresaDAO {
    Connection conn = null;
    PreparedStatement ps;
    ResultSet rs;

    public Empresa obtenerEmpresa() {
        String sql = "SELECT * FROM empresa LIMIT 1";
        Empresa e = null;
        try {
            conn = ConexionBD.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                e = new Empresa();
                e.setId(rs.getInt("id"));
                e.setRuc(rs.getString("ruc"));
                e.setNombre(rs.getString("nombre"));
                e.setTelefono(rs.getString("telefono"));
                e.setDireccion(rs.getString("direccion"));
                e.setRazonSocial(rs.getString("razon_social"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener empresa: " + ex);
        }
        return e;
    }

    public boolean guardarEmpresa(Empresa e) {
        try {
            conn = ConexionBD.conectar();

            // Verificar si ya existe una fila
            String check = "SELECT COUNT(*) FROM empresa";
            ps = conn.prepareStatement(check);
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                String insert = "INSERT INTO empresa (ruc, nombre, telefono, direccion, razon_social) VALUES (?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(insert);
            } else {
                String update = "UPDATE empresa SET ruc=?, nombre=?, telefono=?, direccion=?, razon_social=? WHERE id=1";
                ps = conn.prepareStatement(update);
            }

            ps.setString(1, e.getRuc());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getTelefono());
            ps.setString(4, e.getDireccion());
            ps.setString(5, e.getRazonSocial());
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Error al guardar empresa: " + ex);
            return false;
        }
    }
}
