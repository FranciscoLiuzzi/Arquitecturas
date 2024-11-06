package main.Repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import main.Objects.Administrador;

@Repository ("adminRepository")
public interface AdminRepository extends JpaRepository<Administrador, Long> {

    @Query("SELECT a FROM Administrador a WHERE a.rol = :rol")
    Collection<Administrador> findByRol(String rol);
}