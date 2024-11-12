package main.Objects;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "viaje")
public class Viaje {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Viaje_id")
	private long ViajeId;

	@Column(name = "usuario_id")
	private long usuarioId;
	@Column(name = "patin_id")
	private long patinId;
	@Column(name = "tiempo_uso")
	private int tiempoUso;
	@Column(name = "tiempo_fin")
	private Timestamp tiempoFin;
	@Column(name = "km")
	private double km;
	@Column(name = "tiempo_pausa")
	private int tiempoPausa;
	@Column(name = "tarifa")
	private Double tarifa;
	
	//CONSTRUCTORES
	
	public Viaje(){
		super();
	}	

	public Viaje(long usuarioId, long patinId, int tiempoPausa, Double tarifa, int tiempoUso, double patinKms) {
		this.usuarioId = usuarioId;
		this.patinId = patinId;
		this.tiempoUso = tiempoUso;
		this.km = patinKms;	
		this.tiempoFin = null;
		this.tiempoPausa = 0;
		this.tarifa = tarifa;
	}
}