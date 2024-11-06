package main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import main.Objects.Factura;

@Repository ("facturaRepository")
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query(value = "SELECT SUM(monto) FROM factura WHERE fecha_factura BETWEEN ?1 AND ?2", nativeQuery = true)
    Double getFacturacion(String fechaDesde, String fechaHasta);

}