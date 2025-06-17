package controlador;

import modelo.Cliente;
import modelo.ClienteDAO;

public class PruebaCliente {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Juan Pérez", "12345678", "juan@gmail.com");
        ClienteDAO dao = new ClienteDAO();
        dao.insertar(cliente);
    }
}
