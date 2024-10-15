package main.Repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import main.DTOs.EstudiantesEnCarreraDTO;
import main.Objects.Carrera;

@Repository("carreraRepository")
public interface CarreraRepositoryImpl extends JpaRepository<Carrera, Integer>{

	Optional<Carrera> findByNombre(String nombre);
	
	@Query("SELECT new main.DTOs.EstudiantesEnCarreraDTO(c.nombre, COUNT(DISTINCT ec.estudiante) AS cantEstudiantes) " +
            "FROM EstudianteCarrera ec " +
            "JOIN ec.carrera c " +
            "GROUP BY ec.carrera " +
            "ORDER BY cantEstudiantes DESC")
	 public List<EstudiantesEnCarreraDTO> carrerasOrdenadas();
	
//	@Override
//	public List<Carrera> findAll() {
//		em.getTransaction().begin();
//		List<Carrera> result;
//		String jpql = "SELECT c FROM Carrera c";
//		TypedQuery<Carrera> res = em.createQuery(jpql, Carrera.class);
//		result = res.getResultList();
//		em.getTransaction().commit();
//		return result;
//	}
}
