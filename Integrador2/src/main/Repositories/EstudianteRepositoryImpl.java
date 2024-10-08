package main.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import main.Objects.Estudiante;

public class EstudianteRepositoryImpl implements dbRepository<Estudiante> {
	protected EntityManager em;

	public EstudianteRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Estudiante save(Estudiante entity) {
		em.getTransaction().begin();
		if (entity.getLibreta() == null) {
			em.persist(entity);
		} else {
			entity = em.merge(entity);
		}
		em.getTransaction().commit();
		return entity;
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
	public void delete(Estudiante entity) {
		em.getTransaction().begin();
		if (em.contains(entity))
			em.remove(entity);
		else        
			em.merge(entity);
		em.getTransaction().commit();
	}
}
