package main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.Objects.Viaje;

@Repository ("viajeRepository")
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
}