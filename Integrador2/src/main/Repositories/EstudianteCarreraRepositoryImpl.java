package main.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import main.Objects.EstudianteCarrera;

public class EstudianteCarreraRepositoryImpl implements dbRepository<EstudianteCarrera> {
	EntityManager em;

	public EstudianteCarreraRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public EstudianteCarrera save(EstudianteCarrera entity) {
	    try {
	        em.getTransaction().begin();
	        em.persist(entity);
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	    return entity;
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
	public void delete(EstudianteCarrera entity) {
		em.getTransaction().begin();
		if (em.contains(entity))
			em.remove(entity);
		else        
			em.merge(entity);
		em.getTransaction().commit();
	}	
}
