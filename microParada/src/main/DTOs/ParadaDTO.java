package main.DTOs;

import main.Objects.Parada;

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
