package main.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Objects.EstudianteCarrera;
import main.Objects.EstudianteCarreraPK;

@Repository("estudianteCarreraRepository")
public interface EstudianteCarreraRepository extends EstudianteCarreraRepositoryCustom, JpaRepository<EstudianteCarrera, EstudianteCarreraPK> {
	Optional<EstudianteCarrera> findByEstudianteAndCarrera(Estudiante estudiante, Carrera carrera);
	void deleteByEstudianteAndCarrera(Estudiante estudiante, Carrera carrera);
}
