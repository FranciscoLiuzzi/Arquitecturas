package main.DTOs;

public class ProductoMasRecauda {
	private String nombre;
    private int cantidad;
    private Float recaudacion;

    public ProductoMasRecauda() {
        super();
    }

    public ProductoMasRecauda(String nombre, int cantidad, Float recaudacion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.recaudacion = recaudacion;
    }

    @Override
    public String toString() {
        return "Producto que más recauda | Nombre: " + nombre + ", Ventas totales: = " + cantidad + ", Recaudacion: $" + recaudacion;
    }    
    
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Float getRecaudacion() {
        return recaudacion;
    }

}