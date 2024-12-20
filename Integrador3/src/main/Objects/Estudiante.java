package main.Objects;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;
import main.DTOs.EstudianteDTO;


@Entity
@Table(indexes ={@Index(name = "idx_dni", columnList = "dni")})
public class Estudiante{
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="edad")
	private int edad;
	@Column(name="ciudad_residencia")
	private String ciudadResidencia;
	@Column(name="genero")
	private String genero;
	@Column(name="dni")
	private Integer dni;
	
	@Id
	@Column(name="libreta")
	private Integer libreta;
	@Column(name="carrera")
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EstudianteCarrera> carreras;

	public Estudiante(){
		super();
		this.carreras = new HashSet<>();
	}

	public Estudiante(String nombre, String apellido, int edad, String ciudad_residencia, String genero, Integer dni, Integer libreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.ciudadResidencia = ciudad_residencia;
		this.genero = genero;
		this.dni = dni;
		this.libreta = libreta;
		this.edad = edad;
		this.carreras = new HashSet<>();
	}

	public Estudiante(EstudianteDTO dto) {
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.ciudadResidencia = dto.getCiudadResidencia();
		this.genero = dto.getGenero();
		this.dni = dto.getDni();
		this.libreta = dto.getLibreta();
		this.edad = dto.getEdad();
		this.carreras = new HashSet<>();
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Integer getLibreta() {
		return libreta;
	}

	public void setLibreta(Integer libreta) {
		this.libreta = libreta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCiudad_residencia() {
		return ciudadResidencia;
	}

	public String getGenero() {
		return genero;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCiudad_residencia(String ciudad_residencia) {
		this.ciudadResidencia = ciudad_residencia;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<EstudianteCarrera> getCarreras() {
		return new LinkedList<>(carreras);
	}

	public void setCarreras(EstudianteCarrera carrera) {
		this.carreras.add(carrera);
	}

	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", ciudadResidencia="
				+ ciudadResidencia + ", genero=" + genero + ", dni=" + dni + ", libreta=" + libreta + ", carreras="
				+ carreras + "]";
	}
	
	
}
