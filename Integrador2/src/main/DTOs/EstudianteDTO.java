package main.DTOs;

public class EstudianteDTO {
	private String nombre;
	private String apellido;
	private int edad;
	private String genero;
	private Integer dni;
	private String ciudadResidencia;
	private Integer libreta;
	
	public EstudianteDTO() {
		super();
	}

	public EstudianteDTO(String nombre, String apellido, int edad, String ciudadResidencia, String genero, Integer dni, Integer libreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudadResidencia = ciudadResidencia;
		this.libreta = libreta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
	
	public int getEdad() {
		return edad;
	}

	public String getCiudadResidencia() {
		return ciudadResidencia;
	}

	public String getGenero() {
		return genero;
	}

	public Integer getDni() {
		return dni;
	}

	public Integer getLibreta() {
		return libreta;
	}

	public String getNombreFull(){
		return this.apellido + ", " + this.nombre;
	}

	@Override
	public String toString() {
		return "Nombre: " + getNombreFull() + ", Edad: " + edad	+ ", Genero: " + genero + ", DNI: " + dni + ", Ciudad: " + ciudadResidencia + ", Numero de Libreta: " + libreta;
	}    
}
