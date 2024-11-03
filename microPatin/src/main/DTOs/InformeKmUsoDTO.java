package main.DTOs;

import main.Objects.Patin;

public class InformeKmUsoDTO {
    private long patinId;
    private double km;
    private int uso;
    
    //CONSTRUCTORES
    
    public InformeKmUsoDTO(Patin patin) {
        this.patinId = patin.getPatinId();
        this.km = patin.getKm();
        this.uso = patin.getUso();
    }

    public InformeKmUsoDTO(PatinDTO patin) {
        this.patinId = patin.getPatinId();
        this.km = patin.getKm();
        this.uso = patin.getUso();
    }

    public InformeKmUsoDTO(long patinId, double km, int uso) {
        this.patinId = patinId;
        this.km = km;
        this.uso = uso;
    }
    
    //GET&SET
    
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

	public long getPatinId() {
		return patinId;
	}

	public void setPatinId(long patinId) {
		this.patinId = patinId;
	}
}