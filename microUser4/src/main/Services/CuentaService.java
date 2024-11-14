package main.Services;

import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.DTOs.CuentaDTO;
import main.Objects.Cuenta;
import main.Objects.CuentaUsuario;
import main.Objects.Usuario;
import main.Repositories.CuentaRepository;
import main.Repositories.CuentaUsuarioRepository;
import main.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service("cuentaService")
public class CuentaService{
	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CuentaUsuarioRepository cuentausuarioRepository;
		
	@Transactional(readOnly = true)
	public List<CuentaDTO> findAll() {
		return this.cuentaRepository.findAll().stream().map(CuentaDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public CuentaDTO findById(Long id) {
		return this.cuentaRepository.findById(id).map(CuentaDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id));
	}
	
	@Transactional
	public CuentaDTO save(CuentaDTO entity) {
		return new CuentaDTO(this.cuentaRepository.save(new Cuenta(entity)));
	}

	@Transactional
	public void delete(Long id) {
		cuentaRepository.delete(cuentaRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id)));
	}

	@Transactional
	public void update(Long id, CuentaDTO entity) {
		Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + id));
			cuenta.setFechaAlta(entity.getFechaAlta());
			cuenta.setHabilitada(entity.isHabilitada());
			cuenta.setIdMPago(entity.getIdMPago());
			cuenta.setSaldo(entity.getSaldo());
			cuentaRepository.save(cuenta);
		}
		
	@Transactional
	public void asociarUsuario(long usuarioId, long cuentaId) {
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
	public void desvincularUsuario(long usuarioId, long cuentaId) {
		Objects.requireNonNull(usuarioId);
		Objects.requireNonNull(cuentaId);

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new IllegalArgumentException("ID de usuario invalido: " + usuarioId));

		Cuenta cuenta = cuentaRepository.findById(cuentaId)
				.orElseThrow(() -> new IllegalArgumentException("ID de cuenta invalido: " + cuentaId));

		cuentausuarioRepository.deleteByUsuarioAndCuenta(usuario, cuenta);
	}

	@Transactional
	public void updateSaldo(Double saldo, long cuentaId) {
		Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + cuentaId));
		cuenta.setSaldo(saldo);
		cuentaRepository.save(cuenta);
	}

	@Transactional(readOnly = true)
	public Double getSaldo(long cuentaId) {
		Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + cuentaId));
		return cuentaRepository.findById(cuenta.getCuentaId()).orElseThrow().getSaldo();
	}
	
	@Transactional
	public void suspenderCuenta(long cuentaId) {
		Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + cuentaId));
		if (!cuenta.isHabilitada()) {
			throw new IllegalArgumentException("La cuenta ya se encuentra suspendida");
		}
		cuenta.setHabilitada(false);
		cuentaRepository.save(cuenta);
	}

	@Transactional
	public void activarCuenta(long cuentaId) {
		Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(
			() -> new IllegalArgumentException("ID de Cuenta invalido: " + cuentaId));
		if (cuenta.isHabilitada()) {
			throw new IllegalArgumentException("La cuenta ya se encuentra activa");
		}
		cuenta.setHabilitada(true);
		cuentaRepository.save(cuenta);
	}

	@Transactional(readOnly = true)
    public List<CuentaDTO> getCuentasByUsuarioId(long usuarioId) {
		return this.cuentaRepository.findByUsuarioId(usuarioId);
    }
}