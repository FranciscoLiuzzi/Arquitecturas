package main.DTOs;

public class InformeUsoDTO {
	private long patinId;
    private int tiempoUso;
    private int tiempoPausa;
    private String estado;
    
    //CONSTRUCTORES
    
    public InformeUsoDTO(String estado, int tiempoUso, int tiempoPausa) {
        this.estado = estado;
        this.tiempoUso = tiempoUso;
        this.tiempoPausa = tiempoPausa;
    }
    
    //GET&SET
    
	public long getPatinId() {
		return patinId;
	}

	public void setPatinId(long patinId) {
		this.patinId = patinId;
	}

	public int getTiempoUso() {
		return tiempoUso;
	}

	public void setTiempoUso(int tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public int getTiempoPausa() {
		return tiempoPausa;
	}

	public void setTiempoPausa(int tiempoPausa) {
		this.tiempoPausa = tiempoPausa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
