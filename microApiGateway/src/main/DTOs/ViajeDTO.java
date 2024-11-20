package main.DTOs;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Viaje;

@Getter
@RequiredArgsConstructor
public class ViajeDTO {
	private long viajeId;
	private long usuarioId;
	private long patinId;
	private Timestamp tiempoFin;
	private double tiempoUso;
	private double tiempoPausa;
	private double km;
	private Double tarifa;

	public ViajeDTO(Viaje viaje) {
		this.viajeId = viaje.getViajeId();
		this.usuarioId = viaje.getUsuarioId();
		this.patinId = viaje.getPatinId();
		this.tiempoUso = viaje.getTiempoUso();
		this.tiempoPausa = viaje.getTiempoPausa();
		this.tarifa = viaje.getTarifa();
		this.tiempoFin = viaje.getTiempoFin();
		this.km = viaje.getKm();
	}

	public ViajeDTO(long usuarioId, long patinId, double tiempoUso, double tiempoPausa, Double tarifa, Timestamp tiempoFin, double km) {
		this.usuarioId = usuarioId;
		this.patinId = patinId;
		this.tiempoUso = tiempoUso;
		this.tiempoPausa = tiempoPausa;
		this.tarifa = tarifa;
		this.tiempoFin = tiempoFin;
		this.km = km;
	}
}