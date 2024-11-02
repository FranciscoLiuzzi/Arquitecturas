package main.Repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
}
