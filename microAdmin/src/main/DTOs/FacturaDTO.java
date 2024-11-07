package main.DTOs;

import java.sql.Timestamp;

import main.Objects.Factura;

@Getter
@RequiredArgsConstructor
public class FacturaDTO {
	private long facturaId;
	private Timestamp facturaFecha;
	private Double monto;
	private String descripcion;
	
	//CONSTRUCTORES
	
	public FacturaDTO(long facturaId, Timestamp facturaFecha, Double monto, String descripcion) {
		this.facturaFecha = facturaFecha;
		this.monto = monto;
		this.descripcion = descripcion;
		this.facturaId = facturaId;
	}

	public FacturaDTO(Factura factura) {
		this.facturaId = factura.getFacturaId();
		this.facturaFecha = factura.getFacturaFecha();
		this.monto = factura.getMonto();
		this.descripcion = factura.getDescripcion();
	}

	//GET&SET
	
	public long getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(long facturaId) {
		this.facturaId = facturaId;
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