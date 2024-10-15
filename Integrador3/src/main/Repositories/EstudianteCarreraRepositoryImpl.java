package main.Repositories;

import java.util.List;
import java.util.Optional;

import main.DTOs.CarreraInscriptosGraduadosDTO;
import main.DTOs.EstudianteDTO;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Objects.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCarreraRepositoryImpl extends JpaRepository<Carrera, Integer> {
	
	Optional<EstudianteCarrera> findByEstudianteAndCarrera(Estudiante estudiante, Carrera carrera);
	
	void deleteByEstudianteAndCarrera(Estudiante estudiante, Carrera carrera);
	
    @SuppressWarnings("unchecked")
    @Query("SELECT nombre AS carrera, Año AS año, SUM(Inscriptos) AS inscriptos, SUM(Graduados) AS graduados " +
            "FROM " +
            "((SELECT id, nombre, YEAR(fecha_insc) AS Año, COUNT(*) AS Inscriptos, 0 AS Graduados " +
            "FROM carrera JOIN estudiante_carrera ON carrera.id = estudiante_carrera.carrera_id " +
            "GROUP BY carrera.id, YEAR(estudiante_carrera.fecha_insc)) " +
            "UNION " +
            "(SELECT id, nombre, YEAR(fecha_grad) AS Año, 0 AS Inscriptos, COUNT(*) AS Graduados " +
            "FROM carrera JOIN estudiante_carrera ON carrera.id = estudiante_carrera.carrera_id " +
            "WHERE fecha_grad IS NOT NULL " +
            "GROUP BY id, YEAR(fecha_grad))) u " +
            "GROUP BY nombre, Año " +
            "ORDER BY nombre, Año")
    public List<CarreraInscriptosGraduadosDTO> informeCarreras();
	
	@Query("SELECT NEW main.DTOs.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.id) " +
			"FROM Estudiante e " +
			"WHERE e.ciudadResidencia = :ciudad " +
			"AND e.id IN (SELECT ec.estudiante.id FROM Carrera c JOIN c.estudiantes ec WHERE c.nombre = :carrera)")
    public List<EstudianteDTO> buscarPorCarrerasYCiudadResidencia(String carrera, String ciudad);
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
