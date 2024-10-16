package main.DTOs;

import java.math.BigDecimal;
import java.util.Date;

public class CarreraInscriptosGraduadosDTO {
	private String carrera;
	private Integer año;
	private int inscriptos;
	private int graduados;

	public CarreraInscriptosGraduadosDTO() {}
	
	public CarreraInscriptosGraduadosDTO(String carrera, Integer año, BigDecimal inscriptos, BigDecimal graduados) {
		this.carrera = carrera;
		this.año = año;
		this.inscriptos = inscriptos.intValue();
		this.graduados = graduados.intValue();
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public Integer getAño() {
		return año;
	}

	public void setAño(Integer año) {
		this.año = año;
	}

	public int getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(int inscriptos) {
		this.inscriptos = inscriptos;
	}

	public int getGraduados() {
		return graduados;
	}

	public void setGraduados(int graduados) {
		this.graduados = graduados;
	}
	
	
}
