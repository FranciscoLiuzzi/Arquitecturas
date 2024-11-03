package main.Repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import main.DTOs.InformeEstadoDTO;
import main.Objects.Patin;

@Repository
public class PatinRepositoryCustomImpl implements PatinRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;

    @Override
	@SuppressWarnings("unchecked")
	public List<Patin> getTiempo() {
		return entityManager.createNativeQuery(
			"SELECT * FROM patin ORDER BY (uso + pausa) DESC",Patin.class).getResultList();
	}
    
    public InformeEstadoDTO getCantidadOperativosMantenimiento(){
		int monopatinesOperativos = ((Number) entityManager.createNativeQuery(
			"Select COUNT(*) From patin WHERE estado LIKE 'Libre' OR estado LIKE 'Ocupado'"
		).getSingleResult()).intValue();
		int monopatinesMantenimiento = ((Number) entityManager.createNativeQuery(
			"Select COUNT(*) From patin WHERE estado LIKE 'Mantenimiento'"
		).getSingleResult()).intValue();
		return new InformeEstadoDTO(monopatinesMantenimiento,monopatinesOperativos);
	}
}
