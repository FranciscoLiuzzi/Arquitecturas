package main.DTOs;

import main.Objects.Carrera;

public class CarreraDTO {
	private String nombre;
	
	public CarreraDTO() {
		super();
	}

	public CarreraDTO(String nombre) {
		this.nombre = nombre;
	}
	
	public CarreraDTO(Carrera carrera) {
		this.nombre = carrera.getNombre();
	}
	
	public CarreraDTO(CarreraDTO carreraDTO) {
		this.nombre = carreraDTO.getNombre();
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Carrera: " + nombre;
	}
}
