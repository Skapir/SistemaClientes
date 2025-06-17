package modelo;

public class Venta {
    private int id;
    private int clienteId;
    private String clienteNombre;
    private String fecha;
    private double total;

    public Venta() {
    }

    public Venta(int id, int clienteId, String fecha, double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public String getClienteNombre() {
    return clienteNombre;
}

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
}
}
