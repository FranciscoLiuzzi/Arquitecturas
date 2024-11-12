package main.DTOs;

import lombok.Getter;

@Getter
public class ParadaDTO {
	private String x;
	private String y;
	
	//CONSTRUCTORES
	
	public ParadaDTO(){
		super();
	}

	public ParadaDTO(String x, String y) {
		this.x = x;
		this.y = y;
	}
}
