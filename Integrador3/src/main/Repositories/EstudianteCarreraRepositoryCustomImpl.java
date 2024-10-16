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

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EstudianteCarreraRepositoryCustomImpl implements EstudianteCarreraRepositoryCustom {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
    @SuppressWarnings("unchecked")
    public List<CarreraInscriptosGraduadosDTO> informeCarreras() {
        return entityManager.createNativeQuery(
            "SELECT nombre AS carrera, Año AS año, SUM(Inscriptos) AS inscriptos, SUM(Graduados) AS graduados \r\n"
            + "FROM \r\n"
            + "((SELECT id, nombre, YEAR(fecha_insc) AS Año, COUNT(*) AS Inscriptos, 0 AS Graduados \r\n"
            + "FROM carrera \r\n"
            + "JOIN estudiante_carrera ON carrera.id = estudiante_carrera.carrera_id \r\n"
            + "GROUP BY carrera.id, YEAR(estudiante_carrera.fecha_insc)) \r\n"
            + "UNION \r\n"
            + "(SELECT id, nombre, YEAR(fecha_grad) AS Año, 0 AS Inscriptos, COUNT(*) AS Graduados \r\n"
            + "FROM carrera \r\n"
            + "JOIN estudiante_carrera ON id = carrera_id \r\n"
            + "WHERE fecha_grad IS NOT NULL \r\n"
            + "GROUP BY id, YEAR(fecha_grad))) u \r\n"
            + "GROUP BY nombre, Año \r\n"
            + "ORDER BY nombre, Año;",
            CarreraInscriptosGraduadosDTO.class
        ).getResultList();
    }

    public List<EstudianteDTO> buscarPorCarrerasYCiudadResidencia(String carrera, String ciudad){
		return entityManager.createQuery(
			"SELECT NEW main.DTOs.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.id) " +
			"FROM Estudiante e " +
			"WHERE e.ciudadResidencia = :ciudad " +
			"AND e.id IN (SELECT ec.estudiante.id FROM Carrera c JOIN c.estudiantes ec WHERE c.nombre = :carrera)",
			EstudianteDTO.class
		).setParameter("ciudad", ciudad)
		 .setParameter("carrera", carrera)
		 .getResultList();
	}
}
