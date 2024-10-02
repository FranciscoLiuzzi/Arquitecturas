package main.DTOs;

public class EstudianteDTO {
	private String nombre;
	private String apellido;
	private Integer edad;
	private String genero;
	private Integer dni;
	private String ciudad;
	private Integer libreta;

	public EstudianteDTO(String nombre, String apellido, int edad, String ciudad, String genero, Integer dni, Integer libreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
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

	public String getCiudad() {
		return ciudad;
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
		return "Nombre: " + getNombreFull() + ", Edad: " + edad	+ ", Genero: " + genero + ", DNI: " + dni + ", Ciudad: " + ciudad + ", Numero de Libreta: " + libreta;
	}    
}
