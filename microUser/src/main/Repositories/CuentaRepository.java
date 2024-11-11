package main.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import main.DTOs.CuentaDTO;
import main.Objects.Cuenta;

@Repository ("cuentaRepository")
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	
	@Query("SELECT NEW com.microuseraccount.dto.AccountDTO(a) FROM Account a JOIN a.usuarios ua JOIN ua.user u WHERE u.userId = ?1")
	public List<CuentaDTO> findByUsuarioId(Long id);
}