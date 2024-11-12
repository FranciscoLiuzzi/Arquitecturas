package main.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.Objects.Patin;

@Repository ("patinRepository")
public interface PatinRepository extends PatinRepositoryCustom, JpaRepository<Patin, Long> {
	List<Patin> findAllByOrderByKmDesc();
	
	List<Patin> findAllByOrderByUsoDesc();
}