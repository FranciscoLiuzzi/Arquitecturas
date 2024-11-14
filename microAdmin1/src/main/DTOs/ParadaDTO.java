package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ParadaDTO {
	private String x;
	private String y;
	
	//CONSTRUCTORES
	
	public ParadaDTO(String x, String y) {
		this.x = x;
		this.y = y;
	}
}
