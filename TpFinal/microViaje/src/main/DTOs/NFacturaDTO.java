package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.sql.Timestamp;

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
}