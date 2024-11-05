package main.DTOs;

import java.sql.Timestamp;

import main.Objects.Factura;

public class TarifaDTO {
	private long tarifaId;
	private Timestamp facturaFecha;
	private Double monto;
	private String descripcion;
	
	//CONSTRUCTORES
	
	public TarifaDTO(long tarifaId, Timestamp facturaFecha, Double monto, String descripcion) {
		this.facturaFecha = facturaFecha;
		this.monto = monto;
		this.descripcion = descripcion;
		this.tarifaId = tarifaId;
	}

	public TarifaDTO(Factura factura) {
		this.tarifaId = factura.getFacturaId();
		this.facturaFecha = factura.getFacturaFecha();
		this.monto = factura.getMonto();
		this.descripcion = factura.getDescripcion();
	}
	
	//GET&SET
	
	public long getTarifaId() {
		return tarifaId;
	}

	public void setTarifaId(long tarifaId) {
		this.tarifaId = tarifaId;
	}

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
