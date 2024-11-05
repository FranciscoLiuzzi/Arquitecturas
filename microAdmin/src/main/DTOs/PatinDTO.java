package main.DTOs;

public class PatinDTO {
	private long patinId;
	private String x;
	private String y;
    private String estado;
    private double km;
    private int tiempoUso;
    private int tiempoPausa;
    
    //CONSTRUCTORES
    
	public PatinDTO(long patinId,String x, String y,String estado, double km, int tiempoUso, int tiempoPausa) {
		this.patinId = patinId;
		this.x = x;
		this.y = y;
		this.estado = estado;
		this.km = km;
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
}
