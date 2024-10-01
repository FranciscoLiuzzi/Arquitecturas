package main.DTOs;

import java.util.Calendar;
import java.util.Date;

public class EstudianteCarreraDTO {
	private String estudiante;
	private String carrera;
	private Date inicio;
	private Date graduacion;
	

	public EstudianteCarreraDTO(String estudiante, String carrera, Date inicio, Date graduacion) {
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.inicio = inicio;
		this.graduacion = graduacion;
	}

	public Integer getAntiguedad(){
		return Calendar.getInstance().get(Calendar.YEAR) - inicio.getYear();
	}
	
	public Date getInicio() {
		return inicio;
	}
	
	public Date getGraduacion() {
		return graduacion;
	}

	public boolean isGraduado() {
		return graduacion != null;
	}
	
	public String getEstudiante() {
		return estudiante;
	}

	public String getCarrera() {
		return carrera;
	}

	@Override
	public String toString() {
		return "Estudiante: " + estudiante + ", Carrera: " + carrera +", Fecha de inicio: " + inicio + ", Fecha de graduacion:" + graduacion + ", Antiguedad:" + getAntiguedad();
	}
}
