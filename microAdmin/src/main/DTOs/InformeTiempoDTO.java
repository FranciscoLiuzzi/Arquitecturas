package main.DTOs;

public class InformeTiempoDTO {
	private long patinId;
    private int tiempoTotal;
    private String estado;
    
    //CONSTRUCTORES
    
    public InformeTiempoDTO(String estado, int tiempoUso, int tiempoPausa) {
        this.estado = estado;
        this.tiempoTotal = tiempoUso + tiempoPausa;
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
