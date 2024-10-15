package main.DTOs;

import java.util.Date;

public class CarreraInscriptosGraduadosDTO {
	private String carrera;
	private Date año;
	private Long inscriptos;
	private Long graduados;
	
	public CarreraInscriptosGraduadosDTO(String carrera, Date año, Long inscriptos, Long graduados) {
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

	public Date getAño() {
		return año;
	}

	@Override
	public String toString() {
		return "Carrera: " + carrera + ", " + año + ", Inscriptos: " + inscriptos + ", Graduados: " + graduados;
	}
}
