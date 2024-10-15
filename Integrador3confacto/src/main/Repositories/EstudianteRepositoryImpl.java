package main.Repositories;

import org.springframework.stereotype.Repository;
import main.DTOs.EstudianteDTO;
import main.Objects.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository("estudianteRepository")
public interface EstudianteRepositoryImpl extends JpaRepository<Estudiante, Integer> {

	@Query("SELECT NEW main.DTOs.EstudianteDTO(p.nombre, p.apellido, p.edad, p.ciudadResidencia, p.genero, p.dni, p.libreta) " + "FROM Estudiante p WHERE p.libreta = ?1")
	public EstudianteDTO getEstudianteByLibreta(int libreta);
	
	@Query("SELECT NEW main.DTOs.EstudianteDTO(p.nombre, p.apellido, p.edad, p.ciudadResidencia, p.genero, p.dni, p.libreta) " + "FROM Estudiante p ORDER BY p.apellido, p.nombre")
	public List<EstudianteDTO> getAllEstudiantesOrderByApellido();
	
	@Query("SELECT DISTINCT e.genero FROM Estudiante e")
	public List<String> getGeneros();
	
	@Query("SELECT NEW main.DTOs.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.libreta) " + "FROM Estudiante e " + "WHERE (e.genero = :genero)")
	public List<EstudianteDTO> getEstudiantesPorGenero(String genero);
}
