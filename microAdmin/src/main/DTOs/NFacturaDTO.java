package main.DTOs;

import java.sql.Timestamp;

import main.Objects.Factura;

public class NFacturaDTO {
	private Timestamp facturaFecha;
	private Double monto;
	private String descripcion;
	
	//CONSTRUCTORES
	
	public NFacturaDTO(Timestamp facturaFecha, Double monto, String descripcion) {
		this.facturaFecha = facturaFecha;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	public NFacturaDTO(Factura factura) {
		this.facturaFecha = factura.getFacturaFecha();
		this.monto = factura.getMonto();
		this.descripcion = factura.getDescripcion();
	}
	
	//GET&SET
	
	public Timestamp getFacturaFecha() {
		return facturaFecha;
	}

	public void setFacturaFecha(Timestamp facturaFecha) {
		this.facturaFecha = facturaFecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}