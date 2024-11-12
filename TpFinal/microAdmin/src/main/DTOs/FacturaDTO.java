package main.DTOs;

import java.sql.Timestamp;
import main.Objects.Factura;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
}