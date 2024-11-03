package main.DTOs;

import main.Objects.Patin;

public class InformeUsoDTO {
	 private long patinId;
    private int uso;
    private int pausa;
    private String estado;
    
    //CONSTRUCTORES
    
    public InformeUsoDTO(Patin patin) {
        this.patinId = patin.getPatinId();
        this.estado = patin.getEstado();
        this.uso = patin.getUso();
        this.pausa = patin.getPausa();
    }

    public InformeUsoDTO(String estado, int tiempoDeUso, int tiempoEnPausa) {
        this.estado = estado;
        this.uso = tiempoDeUso;
        this.pausa = tiempoEnPausa;
    }

    //GET&SET
    
	public long getPatinId() {
		return patinId;
	}

	public void setPatinId(long patinId) {
		this.patinId = patinId;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}