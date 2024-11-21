package main.Services;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import main.DTOs.CuentaDTO;
import main.DTOs.NFacturaDTO;
import main.DTOs.ParadaDTO;
import main.DTOs.PatinDTO;
import main.DTOs.TarifaDTO;
import main.DTOs.UsuarioDTO;
import main.DTOs.ViajeDTO;
import main.Objects.Tarifa;
import main.Objects.Viaje;
import main.Repositories.TarifaRepository;
import main.Repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service("viajeService")
public class ViajeService{
	public static final int TIEMPOLIMITE = 15;

	@Autowired
	private ViajeRepository viajeRepository;
	
	@Autowired
	private TarifaRepository tarifaRepository;

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String LOGIN_URL = "http://localhost:8006/auth/acceder";

	private static final String ACCOUNTS_URL = "http://localhost:8004/cuentas";

	private static final String USERS_URL = "http://localhost:8004/usuarios";

	private static final String SCOOTERS_URL = "http://localhost:8002/monopatines";

	private static final String STATIONS_URL = "http://localhost:8001/estaciones";

	private static final String ADMINISTRATION_URL = "http://localhost:8005/administracion";
	
	private String getSystemToken(){
		Map<String, String> body = new HashMap<>();
		body.put("email", "System@system");
		body.put("password", "System");
		
		ResponseEntity<String> response = restTemplate.postForEntity(LOGIN_URL, body, String.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("Error al obtener el token");
		}
		String res = response.getBody();
		if (res != null) {
			return res.replace("{\"token\":\"", "").replace("\"}", "");
        } else {
			throw new IllegalStateException("Response body is null");
        }
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Transactional(readOnly = true)
	public List<ViajeDTO> findAll() {
		return this.viajeRepository.findAll().stream().map(ViajeDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public ViajeDTO findById(Long id) {
		return this.viajeRepository.findById(id).map(ViajeDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de viaje invalido: " + id));
	}
	
	@Transactional
	public ViajeDTO save(long idUsuario, long idPatin) throws IllegalArgumentException, RestClientException {
		String token = getSystemToken();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<UsuarioDTO> usuario = restTemplate.exchange(USERS_URL + "/buscar/" + idUsuario, HttpMethod.GET, entity, UsuarioDTO.class);
		if (usuario.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de usuario invalido: " + idUsuario);
		}
		ResponseEntity<List<CuentaDTO>> cuentas = restTemplate.exchange("http://localhost:8004/cuentas/usuario/" + idUsuario, 
											HttpMethod.GET, null, new ParameterizedTypeReference<List<CuentaDTO>>() {});
		if (cuentas.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("Error al obtener las cuentas del usuario: " + idUsuario);
		}
		boolean hasCredit = false;
		for(CuentaDTO cuenta : Objects.requireNonNull(cuentas.getBody())) {
			if (cuenta.isHabilitada() && cuenta.getSaldo() > 0)
			hasCredit = true;
		}
		if (!hasCredit) {
			throw new IllegalArgumentException("El usuario no tiene saldo suficiente para realizar un viaje");
		}
		ResponseEntity<PatinDTO> patin = restTemplate.getForEntity("http://localhost:8002/monopatines/" + idPatin, PatinDTO.class);
		if (patin.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de monopatin invalido: " + idPatin);
		}
		PatinDTO patinBody = patin.getBody();
		if (patinBody == null) {
			throw new IllegalArgumentException("El cuerpo de la respuesta del monopatin es nulo");
		}
		if (patinBody.getEstado().equals("ocupado")) {
			throw new IllegalArgumentException("El monopatin ya se encuentra en uso");
		}
		if (!updateEstadoPatin(idPatin, patinBody, "ocupado", token)) {
			throw new IllegalArgumentException("El estado del monopatin no se pudo actualizar");
		}
		Integer patinUso = patinBody.getUso();
		Double patinKm = patinBody.getKm();
		Integer patinPausa = patinBody.getPausa();
		ViajeDTO res = new ViajeDTO(this.viajeRepository.save(new Viaje(idUsuario, idPatin, -patinPausa, getCurrentFija(), -patinUso, -patinKm)));
		
		return res;
	}

	private boolean updateEstadoPatin(long idScooter, PatinDTO scooter, String estado, String token) {
		String estadoPatinUrl = SCOOTERS_URL + "/actualizar/" + idScooter;
		scooter.setEstado(estado);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PatinDTO> requestEntity = new HttpEntity<>(scooter, headers);
		ResponseEntity<?> patinResponse = restTemplate.exchange(
			estadoPatinUrl,
			HttpMethod.PUT,
			requestEntity,
			String.class
			);
		return patinResponse.getStatusCode() == HttpStatus.OK;
	}

	@Transactional(readOnly = true)
	private Double getCurrentFija() {
		return tarifaRepository.findFirstFija();
	}

	@Transactional(readOnly = true)
	private Double getCurrentCompleta() {
		return tarifaRepository.findFirstCompleta();
	}

	@Transactional
	public void viajeEnd(Long id) throws Exception {
		String token = getSystemToken();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		Viaje viaje = viajeRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("No se encuentra el viaje con id: " + id));
		if (viaje.getTiempoFin() != null) {
			throw new IllegalArgumentException("El viaje ya esta finalizado");
		}
		
		ResponseEntity<PatinDTO> patin = restTemplate.exchange(
				SCOOTERS_URL + "/" +  viaje.getPatinId(),
				HttpMethod.GET,
				entity,
				PatinDTO.class
			);
		
		if (patin.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de monopatin invalido: " + viaje.getPatinId());
		}
		PatinDTO patinBody = patin.getBody();
		if (patinBody == null) {
			throw new IllegalArgumentException("El cuerpo de la respuesta del monopatin es nulo");
		}
		addRandomviajeData(patinBody); //simula datos de viaje
		String patinXStr = patinBody.getX();
		String patinYStr = patinBody.getY();
		if (patinXStr == null || patinYStr == null) {
			throw new IllegalArgumentException("La latitud o longitud del monopatin es nula");
		}
		Double patinX = Double.parseDouble(patinXStr);
		Double patinY = Double.parseDouble(patinYStr);
		
		ResponseEntity<ParadaDTO> parada = restTemplate.exchange(
				STATIONS_URL + "/verificar/x/" + patinX + "/y/" + patinY,
				HttpMethod.GET,
				entity,
				ParadaDTO.class
			);
		
		if (parada.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("No se encuentra una estacion valida para el monopatin: " + viaje.getPatinId());
		}
		Integer patinPausa = patinBody.getPausa();
		Integer patinUso = patinBody.getUso();
		Double patinKm = patinBody.getKm();
		if (patinPausa == null || patinUso == null || patinKm == null) {
			throw new IllegalArgumentException("El tiempo en pausa, el tiempo de uso o los kilometros del monopatin son nulos");
		}
		viaje.setTiempoPausa(viaje.getTiempoPausa() + patinPausa);
		viaje.setTiempoUso(viaje.getTiempoUso() + patinUso);
		viaje.setKm(viaje.getKm() + patinKm);
		viaje.setTiempoFin(new Timestamp(System.currentTimeMillis()));
		if (viaje.getTiempoPausa() < TIEMPOLIMITE) {
			viaje.setTarifa(viaje.getTiempoUso() * getCurrentFija());
		}
		else {
			viaje.setTarifa(viaje.getTiempoUso() * getCurrentCompleta());
		}	
			
		viajeRepository.save(viaje);
		updateEstadoPatin(viaje.getPatinId(), patin.getBody(), "disponible", token);
		updateUserAccount(viaje.getUsuarioId(), viaje.getTarifa(), token);
		sendBill(viaje, token);
	}

	//Simula datos de viaje
	private void addRandomviajeData(PatinDTO scooterBody) {
		scooterBody.setUso(scooterBody.getUso() + (int) (Math.random() * 20));
		scooterBody.setPausa(scooterBody.getPausa() + (int) (Math.random() * 15));
		scooterBody.setKm(scooterBody.getKm() + Math.random() * 100);
	}

	@Transactional
	public void sendBill(Viaje viaje, String token) throws Exception {
		String cuentaUrl = ADMINISTRATION_URL + "/facturacion/nueva";
		
		String facturaDescripcion = "Viaje realizado el " + viaje.getTiempoFin() + " en el monopatin " + viaje.getPatinId() + " por el usuario " + viaje.getUsuarioId();
		NFacturaDTO bill = new NFacturaDTO(viaje.getTiempoFin(), viaje.getTarifa(), facturaDescripcion);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        HttpEntity<NFacturaDTO> requestEntity = new HttpEntity<>(bill, headers);

        ResponseEntity<Void> response = restTemplate.exchange(cuentaUrl, HttpMethod.POST, requestEntity, Void.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Error al crear la factura");
        }
	}

	@Transactional
	public void updateUserAccount(long userId, double tarifa, String token) throws Exception {
		List<CuentaDTO> cuentas = getUserAccounts(userId, token);
		CuentaDTO cuenta = cuentas.stream().filter(a -> a.getSaldo() > 0).findFirst().orElse(null);
		if (Objects.isNull(cuenta)) {
			cuenta = cuentas.get(0);
		}
		cuenta.setSaldo(cuenta.getSaldo() - tarifa);
		try {
			updateAccountBalance(cuenta, token);
		} catch (Exception e) {
			throw new IllegalArgumentException("Error al actualizar la cuenta del usuario: " + userId);
		}
	}

	@Transactional(readOnly = true)
	public List<CuentaDTO> getUserAccounts(long userId, String token) throws Exception {
		
		String url = ACCOUNTS_URL + "/usuario/" + userId;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			ResponseEntity<List<CuentaDTO>> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<CuentaDTO>>() {}
			);

			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			} else {
				// Handle non-OK status codes here
				throw new RuntimeException("Error al obtener las cuentas del usuario con ID " + userId + ". El servidor respondió con el código de estado " + response.getStatusCode());
			}
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific exceptions here
			String errorMessage = "Error al obtener las cuentas del usuario con ID " + userId + ". Se produjo una excepción de tipo " + 
			e.getClass().getSimpleName() + ": " + e.getMessage() + "\n" + "Causa: " + e.getResponseBodyAsString();
			throw new RuntimeException(errorMessage, e);
		} catch (Exception e) {
			// Handle all other exceptions here
			String errorMessage = "Error al obtener las cuentas del usuario con ID " + userId + ". Se produjo una excepción de tipo " + 
			e.getClass().getSimpleName() + ": " + e.getMessage() + "\n" + "Causa: " + e.getCause();
			throw new RuntimeException(errorMessage, e);
		}
    }

	@Transactional
	public void updateAccountBalance(CuentaDTO account, String token) throws Exception {
		String accountUrl = USERS_URL + "/actualizar/" + account.getCuentaId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        HttpEntity<CuentaDTO> requestEntity = new HttpEntity<>(account, headers);

        ResponseEntity<Void> response = restTemplate.exchange(accountUrl, HttpMethod.PUT, requestEntity, Void.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Error al actualizar la cuenta del usuario");
        }
    }


	@Transactional
	public void delete(Long id) {
		viajeRepository.delete(viajeRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de viaje invalido: " + id)));
	}

	@Transactional
	public void update(Long id, ViajeDTO entity) {
		Viaje viaje = viajeRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de viaje invalido: " + id));
		viaje.setUsuarioId(entity.getUsuarioId());
		viaje.setPatinId(entity.getPatinId());
		viaje.setTarifa(entity.getTarifa());
		viaje.setTiempoFin(entity.getTiempoFin());		
		viajeRepository.save(viaje);
	}
	
	@Transactional
	public TarifaDTO saveTarifa(TarifaDTO tarifa) {
		return new TarifaDTO(this.tarifaRepository.save(new Tarifa(tarifa)));
	}
		
}