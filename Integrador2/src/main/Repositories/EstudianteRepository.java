package main.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import main.Objects.Estudiante;

public class EstudianteRepository implements dbRepository<Estudiante> {
	protected EntityManager em;

	public EstudianteRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Estudiante save(Estudiante object) {
		em.getTransaction().begin();
		if (object.getLibreta() == null) {
			em.persist(object);
		} else {
			object = em.merge(object);
		}
		em.getTransaction().commit();
		return object;
	}

	@Override
	public Estudiante findById(int id) {
		em.getTransaction().begin();
		Estudiante aux = em.find(Estudiante.class, id);
		em.getTransaction().commit();
		return aux;

	}

	@Override
	public List<Estudiante> findAll() {
		em.getTransaction().begin();
		List<Estudiante> result;
		String jpql = "SELECT e FROM Estudiante e";
		TypedQuery<Estudiante> res = em.createQuery(jpql, Estudiante.class);
		result = res.getResultList();
		em.getTransaction().commit();
		return result;
	}

	@Override
	public void delete(Estudiante object) {
		em.getTransaction().begin();
		if (em.contains(object)) {
			em.remove(object);
		} else {        
			em.merge(object);
		}
		em.getTransaction().commit();
	}
}
