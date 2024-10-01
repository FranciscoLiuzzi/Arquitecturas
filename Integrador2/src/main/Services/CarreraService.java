package main.Services;

import java.util.Date;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Objects.EstudianteCarrera;
import main.Repositories.CarreraRepository;
import main.Repositories.EstudianteCarreraRepository;

public class CarreraService extends CarreraRepository{
	private EstudianteCarreraRepository inscriptos;
	private EntityManager em;

	public CarreraService(EntityManager em) {
		super(em);
		this.em = em;
		this.inscriptos = new EstudianteCarreraRepository(em);
	}

	public Integer getCarreraIdByName(String name) {
		try {
			String jpqlCarrera = "SELECT c FROM Carrera c WHERE c.nombre = :nombre";
			TypedQuery<Carrera> query = em.createQuery(jpqlCarrera, Carrera.class);
			query.setParameter("nombre", name); 
			return (query.getSingleResult().getId());
		} catch (Exception e) {
			throw new NullPointerException("Carrera " + name + " not exists"); 
		}
	}
	
	public void matricular(Estudiante estudiante, Carrera carrera) {	
		Objects.requireNonNull(estudiante);
		Objects.requireNonNull(carrera);	
		Date fecha = new Date();
		EstudianteCarrera n = new EstudianteCarrera(estudiante, carrera, fecha);            
		this.inscriptos.save(n);
	}
}
