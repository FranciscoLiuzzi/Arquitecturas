package main.DTOs;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class EstudianteCarreraDTO {
	private Date fechaInscripcion;
	private Date fechaGraduacion;
	private String e;
	private String c;

	public EstudianteCarreraDTO(String e, String c, Date fechaInscripcion, Date fechaGaduacion) {
		this.fechaInscripcion = fechaInscripcion;
		this.fechaGraduacion = fechaGaduacion;
		this.e = e;
		this.c = c;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public boolean isGraduado() {
		return fechaGraduacion != null;
	}

	public Integer getAntiguedad(){
		Calendar fechaInscripcion = Calendar.getInstance();
		fechaInscripcion.setTimeInMillis(this.fechaInscripcion.getTime());
		return Calendar.getInstance().get(Calendar.YEAR) - fechaInscripcion.get(Calendar.YEAR);
	}
	
	public Date getFechaGraduacion() {
		return fechaGraduacion;
	}

	public String getE() {
		return e;
	}

	public String getC() {
		return c;
	}

	@Override
	public String toString() {
		return "Estudiante: " + e + ", Carrera: " + c +", fecha de inscripcion: " + fechaInscripcion + ", fecha de graduacion:" + fechaGraduacion + ", antiguedad:" + getAntiguedad();
	}
}
