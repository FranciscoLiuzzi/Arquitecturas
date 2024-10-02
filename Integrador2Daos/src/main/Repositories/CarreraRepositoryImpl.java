package main.Repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import main.Objects.Carrera;

public class CarreraRepositoryImpl implements dbRepository<Carrera>{
	private EntityManager em;

	public CarreraRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Carrera save(Carrera object) {
		em.getTransaction().begin();
		if (object.getId() == null) {
			em.persist(object);
		} else {
			object = em.merge(object);
		}
		em.getTransaction().commit();
		return object;
	}

	@Override
	public Carrera findById(int id) {
		em.getTransaction().begin();
		Carrera aux = em.find(Carrera.class, id);
		em.getTransaction().commit();
		return aux;

	}

	@Override
	public List<Carrera> findAll() {
		em.getTransaction().begin();
		List<Carrera> result;
		String jpql = "SELECT c FROM Carrera c";
		TypedQuery<Carrera> res = em.createQuery(jpql, Carrera.class);
		result = res.getResultList();
		em.getTransaction().commit();
		return result;
	}

	@Override
	public void delete(Carrera object) {
		em.getTransaction().begin();
		if (em.contains(object)) {
			em.remove(object);
		} else {        
			em.merge(object);
		}
		em.getTransaction().commit();
	}
}
