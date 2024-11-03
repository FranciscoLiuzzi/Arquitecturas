package main.DTOs;

import main.Objects.Patin;

public class InformeTiempoDTO {
	private long patinId;
    private int tiempoTotal;
    private String estado;
    
    //CONSTRUCTORES
    
    public InformeTiempoDTO(Patin patin) {
        this.patinId = patin.getPatinId();
        this.estado = patin.getEstado();
        this.tiempoTotal = patin.getUso() + patin.getPausa();
    }

    public InformeTiempoDTO(String estado, int tiempoDeUso, int tiempoEnPausa) {
        this.estado = estado;
        this.tiempoTotal = tiempoDeUso + tiempoEnPausa;
    }

    //GET&SET
    
	public long getPatinId() {
		return patinId;
	}

	public void setPatinId(long patinId) {
		this.patinId = patinId;
	}

	public int getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(int tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}