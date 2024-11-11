package main.DTOs;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Factura;

@Getter
@RequiredArgsConstructor
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
}