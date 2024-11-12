package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Parada;

@Getter
@RequiredArgsConstructor
public class ParadaDTO {
	private String x;
	private String y;
	
	//CONSTRUCTORES
	
	public ParadaDTO(Parada parada) {
		this.x = parada.getX();
		this.y = parada.getY();
	}

	public ParadaDTO(String x, String y) {
		this.x = x;
		this.y = y;
	}
}
