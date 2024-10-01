package main.DTOs;

public class CarreraInscriptosGraduadosDTO {
	private String carrera;
	private Integer año;
	private Long inscriptos;
	private Long graduados;
	
	public CarreraInscriptosGraduadosDTO(String carrera, Integer año, Long inscriptos, Long graduados) {
		this.carrera = carrera;
		this.año = año;
		this.inscriptos = inscriptos;
		this.graduados = graduados;
	}

	public String getNombreCarrera() {
		return carrera;
	}

	public long getCantInscriptos() {
		return inscriptos;
	}

	public long getCantGraduados() {
		return graduados;
	}

	public int getAño() {
		return año;
	}

	@Override
	public String toString() {
		return "Carrera: " + carrera + ", " + año + ", Inscriptos: " + inscriptos + ", Graduados: " + graduados;
	}
}
