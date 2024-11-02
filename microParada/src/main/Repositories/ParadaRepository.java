package main.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import main.Objects.Parada;

@Repository ("paradaRepository")
public interface ParadaRepository extends JpaRepository<Parada, Long> {
	@Query("SELECT s FROM Station s WHERE x = ?2 AND y = ?1")
    Optional<Parada> findByXAndY(String x, String y);
}