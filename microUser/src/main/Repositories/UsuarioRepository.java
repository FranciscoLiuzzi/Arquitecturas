package main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.Objects.Usuario;

@Repository ("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}