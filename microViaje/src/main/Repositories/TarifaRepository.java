package main.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.Objects.Tarifa;

@Repository ("tarifaRepository")
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    @Query(value = "SELECT t.fija FROM Tarifa t WHERE t.fecha < CURRENT_DATE ORDER BY t.fecha DESC LIMIT 1", nativeQuery = true)
    Double findFirstFija();

    @Query(value = "SELECT t.completa FROM Tarifa t WHERE t.fecha < CURRENT_DATE ORDER BY t.fecha DESC LIMIT 1", nativeQuery = true)
    Double findFirstCompleta();
}