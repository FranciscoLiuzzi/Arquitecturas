package main.Repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import main.DTOs.EstudiantesEnCarreraDTO;
import main.Objects.Carrera;

@Repository
public class CarreraRepositoryCustomImpl implements CarreraRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;

    public List<EstudiantesEnCarreraDTO> carrerasOrdenadas(){
        return entityManager.createQuery(
            "SELECT NEW main.DTOs.EstudiantesEnCarreraDTO(c.nombre, COUNT(DISTINCT ec.estudiante) AS cantEstudiantes) " +
                        "FROM EstudianteCarrera ec " +
                        "JOIN ec.carrera c " +
                        "GROUP BY ec.carrera " +
                        "ORDER BY cantEstudiantes DESC",
                        EstudiantesEnCarreraDTO.class
        ).getResultList();
    }
}
