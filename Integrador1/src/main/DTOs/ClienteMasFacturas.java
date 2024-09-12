package main.DTOs;

public class ClienteMasFacturas {
	private String nombre;
    private String email;
    private Float facturacion;    

    public ClienteMasFacturas() {
        super();
    }

    public ClienteMasFacturas(String nombre, String email, Float facturacion) {
        this.nombre = nombre;
        this.email = email;
        this.facturacion = facturacion;
    }

    @Override
    public String toString() {
        return "Cliente | Nombre: " + nombre + ", Email = " + email + ", Facturacion = $" + facturacion;
    }    

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Float getFacturacion() {
        return facturacion;
    }
}
