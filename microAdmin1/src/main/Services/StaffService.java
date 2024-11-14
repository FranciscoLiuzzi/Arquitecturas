package main.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.DTOs.AdministradorDTO;
import main.Objects.Administrador;
import main.Repositories.AdminRepository;

@Service("StaffService")
public class StaffService{
	@Autowired
	private AdminRepository adminRepository;
		
	@Transactional(readOnly = true)
	public List<AdministradorDTO> findAll() {
		return this.adminRepository.findAll().stream().map(AdministradorDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public AdministradorDTO findById(Long id) {
		return this.adminRepository.findById(id).map(AdministradorDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id));
	}
	
	@Transactional
	public AdministradorDTO save(AdministradorDTO entity) {
		return new AdministradorDTO(this.adminRepository.save(new Administrador(entity)));
	}

	@Transactional
	public void delete(Long id) {
		adminRepository.delete(adminRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id)));
	}

	@Transactional
	public void update(Long id, AdministradorDTO entity) {
		Administrador admin = adminRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de integrante invalido: " + id));
		admin.setNombre(entity.getNombre());
		admin.setApellido(entity.getApellido());
		admin.setNroCelular(entity.getNroCelular());
		admin.setEmail(entity.getEmail());
		admin.setRol(entity.getRol());
		adminRepository.save(admin);
	}

	public List<AdministradorDTO> findByRol(String rol) {
		return this.adminRepository.findByRol(rol).stream().map(AdministradorDTO::new).toList();
	}
	
}