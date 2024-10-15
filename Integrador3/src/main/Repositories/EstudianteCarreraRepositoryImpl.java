package main.Repositories;

import java.util.List;

import main.Objects.Carrera;
import main.Objects.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCarreraRepositoryImpl extends JpaRepository<Carrera, Integer> {

//	@Override
//	public List<EstudianteCarrera> findAll() {
//		em.getTransaction().begin();
//		List<EstudianteCarrera> result;
//		String jpql = "SELECT ec FROM EstudianteCarrera ec";
//		TypedQuery<EstudianteCarrera> res = em.createQuery(jpql, EstudianteCarrera.class);
//		result = res.getResultList();
//		em.getTransaction().commit();
//		return result;
//	}
}
