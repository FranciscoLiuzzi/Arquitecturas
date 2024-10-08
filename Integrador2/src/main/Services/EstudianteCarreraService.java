package main.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import main.DTOs.CarreraInscriptosGraduadosDTO;
import main.DTOs.EstudianteCarreraDTO;
import main.DTOs.EstudianteDTO;
import main.DTOs.EstudiantesEnCarreraDTO;
import main.Objects.Estudiante;
import main.Repositories.EstudianteCarreraRepositoryImpl;

public class EstudianteCarreraService extends EstudianteCarreraRepositoryImpl{
	
	private EntityManager em;

	public EstudianteCarreraService(EntityManager em) {
		super(em);
		this.em = em;
	}     
	
	public List<EstudianteCarreraDTO> getCarrerasOf(Estudiante es) {
		em.getTransaction().begin();
		String jpql = "SELECT NEW main.DTOs.EstudianteCarreraDTO(CONCAT(e.nombre, ', ', e.apellido), c.nombre, ec.fechaInscripcion, ec.fechaGraduacion) " +
		"FROM EstudianteCarrera ec JOIN Estudiante e ON ec.estudiante.libreta = e.libreta " +
		"JOIN Carrera c ON ec.carrera.id = c.id " +
		"WHERE ec.estudiante.libreta = :estudiante";
		TypedQuery<EstudianteCarreraDTO> query = em.createQuery(jpql, EstudianteCarreraDTO.class);
		query.setParameter("estudiante", es.getLibreta());
		List<EstudianteCarreraDTO> res = query.getResultList();
		em.getTransaction().commit();
		return res;
	}

	public List<EstudiantesEnCarreraDTO> getCarrerasPorCantEstudiantes() {
		em.getTransaction().begin();
		String jpql = "SELECT NEW main.DTOs.EstudiantesEnCarreraDTO(c.nombre, COUNT(DISTINCT ec.estudiante) AS cantEstudiantes) " +
						"FROM EstudianteCarrera ec " +
						"JOIN ec.carrera c " +
						"GROUP BY ec.carrera " +
						"ORDER BY cantEstudiantes DESC";
		TypedQuery<EstudiantesEnCarreraDTO> query = em.createQuery(jpql, EstudiantesEnCarreraDTO.class);
		List<EstudiantesEnCarreraDTO> res = query.getResultList();
		em.getTransaction().commit();
		return res;
	} 

	public List<EstudianteDTO> getListEstudiantePorCiudadResidencia(String ciudad, String carrera) {
	    em.getTransaction().begin();
	    String jpqlf = "SELECT NEW main.DTOs.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.libreta) " +
	                     "FROM Estudiante e " +
	                    "WHERE e.ciudadResidencia = :ciudad " +
	                    "AND e.id IN (SELECT ec.estudiante.id FROM Carrera c JOIN c.estudiantes ec WHERE c.nombre = :carrera)";
	    TypedQuery<EstudianteDTO> query = em.createQuery(jpqlf, EstudianteDTO.class);
	    query.setParameter("ciudad", ciudad);
	    query.setParameter("carrera", carrera);
	    List<EstudianteDTO> informe = query.getResultList();
	    em.getTransaction().commit();
		return informe;
	}

	@SuppressWarnings("unchecked")
	public List<CarreraInscriptosGraduadosDTO> getInformePorCarrera() {
		em.getTransaction().begin();
		String SQLquery = "SELECT nombre AS Carrera, Año, SUM(Inscriptos) AS Inscriptos, SUM(Graduados) AS Graduados " +
			"FROM " +
			"((SELECT id, nombre, YEAR(fecha_insc) AS Año, COUNT(*) AS Inscriptos, 0 AS Graduados " +
			"FROM Carrera JOIN estudiante_carrera on Carrera.id = estudiante_carrera.carrera_id " +
			"GROUP BY Carrera.id, YEAR(estudiante_carrera.fecha_insc)) " +
			"UNION " +
			"(SELECT id, nombre, YEAR(fecha_grad) AS Año,0 AS Inscriptos, COUNT(*) AS Graduados " +
			"FROM Carrera JOIN estudiante_carrera on id = Carrera_id WHERE !ISNULL(fecha_grad) " +
			"GROUP BY id, YEAR(fecha_grad))) u " +
			"GROUP BY nombre, Año " +
			"ORDER BY nombre, Año";
		
		Query query = em.createNativeQuery(SQLquery);
		List<Object[]> res = query.getResultList();
		List<CarreraInscriptosGraduadosDTO> informe = new ArrayList<>();
		for (Object[] row : res) {
			Long a = row[2] == null ? null : ((BigDecimal) row[2]).longValue();
			Long b = row[3] == null ? null : ((BigDecimal) row[3]).longValue();
			CarreraInscriptosGraduadosDTO inf = new CarreraInscriptosGraduadosDTO((String) row[0], (Date) row[1], a, b);
			informe.add(inf);
		}
		em.getTransaction().commit();
		return informe;
	}
}
