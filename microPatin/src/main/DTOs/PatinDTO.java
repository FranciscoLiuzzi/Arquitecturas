package main.DTOs;

import main.Objects.Patin;

public class PatinDTO {
	private long patinId;
	private String x;
	private String y;
    private String estado;
    private double km;
    private int uso;
    private int pausa;
    
    
    
	public PatinDTO(Patin patin) {
		this.patinId = patin.getPatinId();
		this.x = patin.getX();
		this.y = patin.getY();
		this.estado = patin.getEstado();
		this.km = patin.getKm();
		this.uso = patin.getUso();
		this.pausa = patin.getPausa();
	}

	public PatinDTO(String x, String y,String estado, double km, int uso, int pausa) {
		this.x = x;
		this.y = y;
		this.estado = estado;
		this.km = km;
		this.uso = uso;
		this.pausa = pausa;		
	}

	public long getPatinId() {
		return patinId;
	}

	public void setPatinId(long patinId) {
		this.patinId = patinId;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public int getUso() {
		return uso;
	}

	public void setUso(int uso) {
		this.uso = uso;
	}

	public int getPausa() {
		return pausa;
	}

	public void setPausa(int pausa) {
		this.pausa = pausa;
	}
}
