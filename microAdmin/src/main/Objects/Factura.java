package main.Objects;

import java.sql.Timestamp;
import jakarta.persistence.*;
import main.DTOs.FacturaDTO;
import main.DTOs.NFacturaDTO;

@Entity
@Table(name = "factura")
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="factura_id")
	private long facturaId;

	@Column(name="fecha_factura")
	private Timestamp facturaFecha;
	@Column(name="monto")
	private Double monto;
	@Column(name="descripcion")
	private String descripcion;
	
	//CONSTRUCTORES
	
	public Factura(){
		super();
	}

	public Factura(Timestamp facturaFecha, Double monto, String descripcion) {
		this.facturaFecha = facturaFecha;
		this.monto = monto;
		this.descripcion = descripcion;
	}

	public Factura(FacturaDTO dto){
		this.facturaFecha = dto.getFacturaFecha();
		this.monto = dto.getMonto();
		this.descripcion = dto.getDescripcion();
	}

	public Factura(NFacturaDTO dto){
		this.facturaFecha = dto.getFacturaFecha();
		this.monto = dto.getMonto();
		this.descripcion = dto.getDescripcion();
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

	public long getFacturaId() {
		return facturaId;
	}
}