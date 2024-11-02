package main.Objects;

import jakarta.persistence.*;
import main.DTOs.ParadaDTO;

@Entity
public class Parada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="parada_id")
	private long stationId;
	
	@Column(name = "latitud")
	private String x;
	
	@Column(name = "longitud")
	private String y;

	//CONSTRUCTORES
	
	public Parada(){
		super();
	}

	public Parada(String x, String y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Parada(ParadaDTO dto){
		this.x = dto.getX();
		this.y = dto.getY();
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
}
