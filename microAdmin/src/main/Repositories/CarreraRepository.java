package main.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.Objects.Carrera;

@Repository ("carreraRepository")
public interface CarreraRepository extends CarreraRepositoryCustom, JpaRepository<Carrera, Integer> {
	Optional<Carrera> findByNombreIgnoreCase(String nombre);
}