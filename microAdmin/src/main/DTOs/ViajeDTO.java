package main.DTOs;

import java.sql.Timestamp;

public class ViajeDTO {
	private long usuarioId;
	private long patinId;
	private Timestamp tiempoFin;
	private double tiempoUso;
	private double tiempoPausa;
	private double kilometros;
	private Double tarifa;
	
	//CONSTRUCTORES
	
	public ViajeDTO(ViajeDTO viaje) {
		this.usuarioId = viaje.getUsuarioId();
		this.patinId = viaje.getPatinId();
		this.tiempoUso = viaje.getTiempoUso();
		this.tiempoPausa = viaje.getTiempoPausa();
		this.tarifa = viaje.getTarifa();
		this.tiempoFin = viaje.getTiempoFin();
		this.kilometros = viaje.getKilometros();
	}

	public ViajeDTO(long usuarioId, long patinId, double tiempoUso, double tiempoPausa, Double tarifa, Timestamp tiempoFin, double kilometros) {
		this.usuarioId = usuarioId;
		this.patinId = patinId;
		this.tiempoUso = tiempoUso;
		this.tiempoPausa = tiempoPausa;
		this.tarifa = tarifa;
		this.tiempoFin = tiempoFin;
		this.kilometros = kilometros;
	}

	//GET&SET
	
	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public long getPatinId() {
		return patinId;
	}

	public void setPatinId(long patinId) {
		this.patinId = patinId;
	}

	public Timestamp getTiempoFin() {
		return tiempoFin;
	}

	public void setTiempoFin(Timestamp tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public double getTiempoUso() {
		return tiempoUso;
	}

	public void setTiempoUso(double tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public double getTiempoPausa() {
		return tiempoPausa;
	}

	public void setTiempoPausa(double tiempoPausa) {
		this.tiempoPausa = tiempoPausa;
	}

	public double getKilometros() {
		return kilometros;
	}

	public void setKilometros(double kilometros) {
		this.kilometros = kilometros;
	}

	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}
}
