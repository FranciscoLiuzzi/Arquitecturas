package main.Repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.Objects.Cuenta;
import main.Objects.CuentaUsuario;
import main.Objects.CuentaUsuarioID;
import main.Objects.Usuario;

@Repository("cuentaUsuarioRepository")
public interface CuentaUsuarioRepository extends JpaRepository<CuentaUsuario, CuentaUsuarioID> {
    Optional<CuentaUsuario> findByUsuarioAndCuenta(Usuario usuario, Cuenta cuenta);
    void deleteByUsuarioAndCuenta(Usuario usuario, Cuenta cuenta);
    List<CuentaUsuario> findByUsuario(Usuario usuario);    
}