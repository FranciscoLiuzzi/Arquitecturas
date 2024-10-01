package main.Objects;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(indexes ={@Index(name = "idx_dni", columnList = "dni")})
public class Estudiante {
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="edad")
	private Integer edad;
	
	@Column(name="genero")
	private String genero;
	
	@Column(name="dni")
	private Integer dni;
	
	@Column(name="ciudad")
	private String ciudad;
	
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
	
	public Estudiante(String nombre, String apellido, Integer edad, String genero, Integer dni, String ciudad, Integer libreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		this.libreta = libreta;
		this.carreras = new HashSet<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Integer getLibreta() {
		return libreta;
	}

	public void setLibreta(Integer libreta) {
		this.libreta = libreta;
	}

	public Set<EstudianteCarrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(Set<EstudianteCarrera> carreras) {
		this.carreras = carreras;
	}
}
