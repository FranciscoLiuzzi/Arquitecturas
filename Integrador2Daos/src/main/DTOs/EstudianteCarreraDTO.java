package main.DTOs;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class EstudianteCarreraDTO {
	private String estudiante;
	private String carrera;
	private Timestamp inicio;
	private Timestamp graduacion;
	

	public EstudianteCarreraDTO(String estudiante, String carrera, Timestamp inicio, Timestamp graduacion) {
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.inicio = inicio;
		this.graduacion = graduacion;
	}

	public Integer getAntiguedad(){
		Calendar fechaInscripcion = Calendar.getInstance();
		fechaInscripcion.setTimeInMillis(this.inicio.getTime());
		return Calendar.getInstance().get(Calendar.YEAR) - fechaInscripcion.get(Calendar.YEAR);
	}
	
	public Timestamp getInicio() {
		return inicio;
	}
	
	public Timestamp getGraduacion() {
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
