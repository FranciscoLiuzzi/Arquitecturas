package main.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import main.Objects.EstudianteCarrera;

public class EstudianteCarreraRepository implements dbRepository<EstudianteCarrera> {
	EntityManager em;

	public EstudianteCarreraRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public EstudianteCarrera save(EstudianteCarrera object) {
		em.getTransaction().begin();
		if (object.getLibreta() == null && object.getCarreraId() == null) {
			em.persist(object);
		} else {
			object = em.merge(object);
		}
		em.getTransaction().commit();
		return object;
	}

	@Override
	public EstudianteCarrera findById(int id) {
		em.getTransaction().begin();
		EstudianteCarrera aux = em.find(EstudianteCarrera.class, id);
		em.getTransaction().commit();
		return aux;
	}

	@Override
	public List<EstudianteCarrera> findAll() {
		em.getTransaction().begin();
		List<EstudianteCarrera> result;
		String jpql = "SELECT ec FROM EstudianteCarrera ec";
		TypedQuery<EstudianteCarrera> res = em.createQuery(jpql, EstudianteCarrera.class);
		result = res.getResultList();
		em.getTransaction().commit();
		return result;
	}

	@Override
	public void delete(EstudianteCarrera object) {
		em.getTransaction().begin();
		if (em.contains(object)) {
			em.remove(object);
		} else {
			em.merge(object);
		}
		em.getTransaction().commit();
	}
}
