package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//DTO Contiene los mismos atributos que la clase parada

@Getter
@RequiredArgsConstructor
public class ParadaDTO {
	private String x;
	private String y;

	public ParadaDTO(String x, String y) {
		this.x = x;
		this.y = y;
	}
}