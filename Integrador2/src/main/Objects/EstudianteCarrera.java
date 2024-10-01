package main.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="estudiante_carrera")
public class EstudianteCarrera implements Serializable{
	
	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;
	
	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carrera_id")
	private Carrera carrera;
	
	@Column(name="inscripcion")
	private LocalDate inicio;
	
	@Column(name="graduacion")
	private LocalDate graduacion;
	
	public EstudianteCarrera() {
		super();
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, LocalDate inicio, LocalDate graduacion) {
		this.estudiante = Objects.requireNonNull(estudiante, "Estudiante must not be null");
		this.carrera = Objects.requireNonNull(carrera, "Carrera must not be null");
		this.inicio = inicio;
		this.graduacion = graduacion;
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, LocalDate inicio) {
		this(estudiante, carrera, inicio, null);
	}

	public Integer getLibreta() {
		return estudiante.getLibreta();
	}

	public Integer getCarreraId() {
		return carrera.getId();
	}

	public void setGraduado(LocalDate fechaGraduado) {
		this.graduacion = fechaGraduado;
	}

	public Integer getAntiguedad(){
		return Calendar.getInstance().get(Calendar.YEAR) - inicio.getYear();
	}
}
