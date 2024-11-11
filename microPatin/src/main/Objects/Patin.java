package main.Objects;

import jakarta.persistence.*;
import lombok.Data;
import main.DTOs.PatinDTO;

@Entity
@Data
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
	
	public double calcularDistancia(Double xdestino,Double ydestino) {
		int radioTierra = 6371; // Radio de la Tierra en km

        double dLat = Math.toRadians(xdestino - Double.parseDouble(x));
        double dLon = Math.toRadians(ydestino - Double.parseDouble(y));

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(Double.parseDouble(x))) * Math.cos(Math.toRadians(xdestino)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierra * c; // Distancia en km
        
        //Aparentemente asi? veremos...
    }
}
