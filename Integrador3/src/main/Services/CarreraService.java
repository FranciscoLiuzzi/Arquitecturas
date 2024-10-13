package main.Services;

import java.util.Date;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.sql.Timestamp;
import java.time.LocalDate;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Objects.EstudianteCarrera;
import main.Repositories.CarreraRepositoryImpl;
import main.Repositories.EstudianteCarreraRepositoryImpl;

public class CarreraService extends CarreraRepositoryImpl{
	private EstudianteCarreraRepositoryImpl inscriptos;
	private EntityManager em;

	public CarreraService(EntityManager em) {
		super(em);
		this.em = em;
		this.inscriptos = new EstudianteCarreraRepositoryImpl(em);
	}

	public Integer getCarreraIdByName(String name) {
		try {
			String jpqlCarrera = "SELECT c FROM Carrera c WHERE c.nombre = :nombre";
			TypedQuery<Carrera> query = em.createQuery(jpqlCarrera, Carrera.class);
			query.setParameter("nombre", name); 
			return (query.getSingleResult().getId());
		} catch (Exception e) {
			throw new NullPointerException("Carrera " + name + " not exist."); 
		}
	}
	
	public void matricular(Estudiante e, Carrera c) {
		Objects.requireNonNull(e);
		Objects.requireNonNull(c);	
		Date hoy = new Date();
		Timestamp ts = new Timestamp(hoy.getTime());
		EstudianteCarrera nuevo = new EstudianteCarrera(e, c, ts);       
		this.inscriptos.save(nuevo);
	}
}
