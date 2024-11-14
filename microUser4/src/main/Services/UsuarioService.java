package main.Services;

import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.DTOs.UsuarioDTO;
import main.Objects.Cuenta;
import main.Objects.CuentaUsuario;
import main.Objects.Usuario;
import main.Repositories.CuentaRepository;
import main.Repositories.CuentaUsuarioRepository;
import main.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service("usuarioService")
public class UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private CuentaUsuarioRepository cuentausuarioRepository;
		
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		return this.usuarioRepository.findAll().stream().map(UsuarioDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		return this.usuarioRepository.findById(id).map(UsuarioDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de Usuario invalido: " + id));
	}
	
	@Transactional
	public UsuarioDTO save(UsuarioDTO entity) {
		return new UsuarioDTO(this.usuarioRepository.save(new Usuario(entity)));
	}

	@Transactional
	public void delete(Long id) {
		usuarioRepository.delete(usuarioRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Usuario invalido: " + id)));
	}

	@Transactional
	public void update(Long id, UsuarioDTO entity) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Usuario invalido: " + id));
		usuario.setNombre(entity.getNombre());
		usuario.setApellido(entity.getApellido());
		usuario.setNroCelular(entity.getNroCelular());
		usuario.setEmail(entity.getEmail());
		usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void asociarCuenta(long usuarioId, long cuentaId) {
		Objects.requireNonNull(usuarioId);
		Objects.requireNonNull(cuentaId);

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new IllegalArgumentException("ID de usuario invalido: " + usuarioId));

		Cuenta cuenta = cuentaRepository.findById(cuentaId)
				.orElseThrow(() -> new IllegalArgumentException("ID de cuenta invalido: " + cuentaId));

		if (cuentausuarioRepository.findByUsuarioAndCuenta(usuario, cuenta).isPresent()) {
			throw new IllegalArgumentException("La cuenta ya se encuentra asociada al usuario");
		}

		CuentaUsuario nuevo = new CuentaUsuario(usuario, cuenta);
		cuentausuarioRepository.save(nuevo);
	}
 
	@Transactional
	public void desvincularCuenta(long usuarioId, long cuentaId) {
		Objects.requireNonNull(usuarioId);
		Objects.requireNonNull(cuentaId);

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new IllegalArgumentException("ID de usuario invalido: " + usuarioId));

		Cuenta cuenta = cuentaRepository.findById(cuentaId)
				.orElseThrow(() -> new IllegalArgumentException("ID de cuenta invalido: " + cuentaId));

		cuentausuarioRepository.deleteByUsuarioAndCuenta(usuario, cuenta);
	}
}