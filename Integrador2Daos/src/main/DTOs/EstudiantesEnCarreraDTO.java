package main.DTOs;

public class EstudiantesEnCarreraDTO {
	private String nombre;
	private Long cantEstudiantes;
	
	public EstudiantesEnCarreraDTO(String nombre, Long cantEstudiantes) {
		this.nombre = nombre;
		this.cantEstudiantes = cantEstudiantes;
	}

	public String getNombreCarrera() {
		return nombre;
	}

	public Long getCantEstudiantes() {
		return cantEstudiantes;
	}

	@Override
	public String toString() {
		return "Carrera: " + nombre + ", Cantidad de estudiantes: " + cantEstudiantes;
	}
}
