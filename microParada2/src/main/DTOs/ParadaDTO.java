package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.ParadaMongo;

@Getter
@RequiredArgsConstructor
public class ParadaDTO {
	private String x;
	private String y;
	private String nombre;
	private String id;
	
	public ParadaDTO(ParadaMongo parada) {
		this.id = parada.getId();
		this.nombre = parada.getNombre();
		this.x = parada.getX();
		this.y = parada.getY();
	}

	public ParadaDTO(String id, String name, String x, String y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.nombre = nombre;
	}
}