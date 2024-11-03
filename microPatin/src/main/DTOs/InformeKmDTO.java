package main.DTOs;

import main.Objects.Patin;

public class InformeKmDTO {
    private long patinId;
    private String estado;
    private double km;

    //CONSTRUCTORES
    
    public InformeKmDTO(Patin patin) {
        this.patinId = patin.getPatinId();
        this.estado = patin.getEstado();
        this.km = patin.getKm();
    }

    public InformeKmDTO(String estado, double km) {
        this.estado = estado;
        this.km = km;
    }
    
    //GET&SET

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
}