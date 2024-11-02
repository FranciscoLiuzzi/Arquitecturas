package main.Objects;

import jakarta.persistence.*;
import main.DTOs.PatinDTO;

@Entity
@Table(name = "patin")
public class Patin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patin_id")
	private long patinId;
	
	@Column(name = "x")
	private String x;
	
	@Column(name = "y")
	private String y;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "km")
	private double km;
	
	@Column(name = "tiempo_uso")
	private int uso;
	
	@Column(name = "tiempo_pausa")
	private int pausa;
	
	//CONSTRUCTORES
	
	public Patin(){
        super();
		this.estado = "Libre";
		this.km = 0;
		this.uso = 0;
		this.pausa = 0;
		this.x = "0";
		this.y = "0";
    }
	
	public Patin(PatinDTO dto){
		super();
		this.x = dto.getX();
		this.y = dto.getY();
		this.estado = dto.getEstado();
		this.km = dto.getKm();
		this.uso = dto.getUso();
		this.pausa = dto.getPausa();
    }
	
	public Patin(String x, String y){
		super();
		this.x = x;
		this.y = y;
		this.estado = "Libre";
		this.km = 0;
		this.uso = 0;
		this.pausa = 0;
	}
	
	//METODOS
	
	public void setFromDTO(PatinDTO dto){
		this.x = dto.getX();
		this.y = dto.getY();
		this.estado = dto.getEstado();
		this.km = dto.getKm();
		this.uso = dto.getUso();
		this.pausa = dto.getPausa();
	}
	
	//GET&SET
	
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

	public long getPatinId() {
		return patinId;
	}
}
