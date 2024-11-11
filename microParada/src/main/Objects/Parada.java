package main.Objects;

import jakarta.persistence.*;
import lombok.Data;
import main.DTOs.ParadaDTO;

@Entity
@Data
@Table(name = "parada")
public class Parada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="parada_id")
	private long paradaId;
	
	@Column(name = "x")
	private String x;
	
	@Column(name = "y")
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
}
