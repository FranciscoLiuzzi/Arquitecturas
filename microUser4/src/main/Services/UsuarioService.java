package main.Services;

import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import main.DTOs.UsuarioDTO;
import main.Objects.Cuenta;
import main.Objects.CuentaUsuario;
import main.Objects.Usuario;
import main.Repositories.CuentaRepository;
import main.Repositories.CuentaUsuarioRepository;
import main.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service("usuarioService")
public class UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private CuentaUsuarioRepository cuentausuarioRepository;
	
	RestTemplate restTemplate = new RestTemplate();

	private static final String AUTH_VALIDATION_URL = "http://localhost:8081/auth";
		
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
	public UsuarioDTO save(UsuarioDTO usuario, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		HttpEntity<UsuarioDTO> entity = new HttpEntity<>(usuario, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(AUTH_VALIDATION_URL + "/registrar", entity, String.class);
		if (response.getStatusCode().isError()) {
			throw new IllegalArgumentException("Error al registrar usuario");
		}
		return new UsuarioDTO(this.usuarioRepository.save(new Usuario(usuario)));
	}

	@Transactional
	public void delete(Long id, String token) {
		UsuarioDTO user = findById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		restTemplate.exchange(AUTH_VALIDATION_URL + "/eliminar/" + user.getEmail(), HttpMethod.DELETE, entity, String.class);
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