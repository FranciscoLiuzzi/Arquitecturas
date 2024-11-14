package main.Services;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import main.DTOs.InformeKmDTO;
import main.DTOs.InformeTiempoDTO;
import main.DTOs.NPatinDTO;
import main.DTOs.ParadaDTO;
import main.DTOs.PatinDTO;
import main.DTOs.TarifaDTO;
import main.DTOs.ViajeDTO;

@Service("adminService")
public class AdminService{
	
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();	

	@Transactional
	public ResponseEntity<?> saveNewPatin(NPatinDTO patinDTO) throws Exception {
		String paradaUrl = "http://localhost:8002/monopatines/alta";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NPatinDTO> requestEntity = new HttpEntity<>(patinDTO, headers);

        ResponseEntity<Void> response = restTemplate.exchange(paradaUrl, HttpMethod.POST, requestEntity, Void.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Error al guardar el nuevo monopatin");
        }
		return response;
	}

	@Transactional
	public ResponseEntity<?> deletePatin(long patinId) throws Exception {
		String paradaUrl = "http://localhost:8002/monopatines/eliminar/" + patinId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PatinDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Void> response = restTemplate.exchange(paradaUrl, HttpMethod.DELETE, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al borrar el monopatin " + patinId);
		}
		return response;
	}

	@Transactional
	public ResponseEntity<?> saveNewParada(ParadaDTO ParadaDTO) throws Exception {
		String paradaUrl = "http://localhost:8001/estaciones/alta";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<ParadaDTO> requestEntity = new HttpEntity<>(ParadaDTO, headers);

		ResponseEntity<Void> response = restTemplate.exchange(paradaUrl, HttpMethod.POST, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al guardar la nueva estacion");
		}
		return response;
	}

	@Transactional
	public ResponseEntity<?> deleteParada(long paradaId) throws Exception {
		String cuentaUrl = "http://localhost:8001/estaciones/eliminar/" + paradaId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ParadaDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Void> response = restTemplate.exchange(cuentaUrl, HttpMethod.DELETE, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error borrar la estacion " + paradaId);
		}
		return response;
	}

	@Transactional
	public ResponseEntity<?> suspendCuenta(long cuentaId) throws Exception {
		String cuentaUrl = "http://localhost:8004/cuentas/suspender/" + cuentaId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ParadaDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Void> response = restTemplate.exchange(cuentaUrl, HttpMethod.PUT, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al suspender la cuenta " + cuentaId);
		}
		return response;
	}

	@Transactional
	public ResponseEntity<?> activateCuenta(long cuentaId) throws Exception {
		String cuentaUrl = "http://localhost:8004/cuentas/activar/" + cuentaId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ParadaDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<Void> response = restTemplate.exchange(cuentaUrl, HttpMethod.PUT, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al activar la cuenta " + cuentaId);
		}
		return response;
	}

	@Transactional(readOnly = true)
	public List<InformeKmDTO> getInformePatinesByKms() throws Exception {
		String patinUrl = "http://localhost:8002/monopatines/reporte/kilometros/sinTiempoDeUso";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ParadaDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<List<InformeKmDTO>> response = restTemplate.exchange(patinUrl, 
								HttpMethod.GET, 
								requestEntity, 
								ParameterizedTypeReference.forType(List.class));
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al obtener los datos.");
		}
		return response.getBody();		
	}

	@Transactional
    public Object getInformePatinesByKmsAndUso() throws Exception {
		String patinUrl = "http://localhost:8002/monopatines/reporte/kilometros/conTiempoDeUso";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ParadaDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<List<InformeKmDTO>> response = restTemplate.exchange(patinUrl, 
								HttpMethod.GET, 
								requestEntity, 
								ParameterizedTypeReference.forType(List.class));
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al obtener los datos.");
		}
		return response.getBody();	
    }	

	@Transactional(readOnly = true)
	public Object getPatinesConMasViajes(Long travelQuantity, Integer year) throws Exception {
		String viajeUrl = "http://localhost:8003/viajes";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ViajeDTO> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<List<ViajeDTO>> response = restTemplate.exchange(viajeUrl, 
								HttpMethod.GET, 
								requestEntity, 
								new ParameterizedTypeReference<List<ViajeDTO>>() {});
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al obtener los datos.");
		}
		List<ViajeDTO> viajes = response.getBody();
		if (viajes == null) {
			throw new Exception("Error al obtener los datos.");
		}
		List<PatinDTO> filteredScooters = viajes.stream()
		.filter((viaje) -> {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(viaje.getTiempoFin().getTime());
			return calendar.get(Calendar.YEAR) == year;
		})
		.collect(Collectors.groupingBy(ViajeDTO::getPatinId, Collectors.counting()))
		.entrySet().stream()
		.filter(entry -> entry.getValue() > travelQuantity)
		.map(entry -> {
			String patinUrl = "http://localhost:8002/monopatines/" + entry.getKey();
			HttpEntity<PatinDTO> requestEntity2 = new HttpEntity<>(headers);
			ResponseEntity<PatinDTO> response2 = restTemplate.exchange(patinUrl,
					HttpMethod.GET,
					requestEntity2,
					ParameterizedTypeReference.forType(PatinDTO.class));
			if (response2.getStatusCode() != HttpStatus.OK) {
				throw new IllegalArgumentException("Error al obtener los datos.");
			}
			return response2.getBody();
		})
		.filter(Objects::nonNull)  // Remove null responses
		.collect(Collectors.toList());
		return filteredScooters;
	}

	@Transactional
	public Object saveNewTarifa(TarifaDTO TarifaDTO) throws Exception {
		String tarifaUrl = "http://localhost:8003/viajes/tarifas/alta";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<TarifaDTO> requestEntity = new HttpEntity<>(TarifaDTO, headers);

		ResponseEntity<Void> response = restTemplate.exchange(tarifaUrl, HttpMethod.POST, requestEntity, Void.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Error al guardar la nueva tarifa");
		}
		return response;
	}

	@Transactional(readOnly = true)
	public List<InformeTiempoDTO> getInformePatinesByUso() {
		String patinUrl = "http://localhost:8002/monopatines/reporte/tiempoTotal";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<InformeTiempoDTO> requestEntity = new HttpEntity<>(headers);
		
		ResponseEntity<List<InformeTiempoDTO>> response = restTemplate.exchange(patinUrl, 
								HttpMethod.GET, 
								requestEntity, 
								ParameterizedTypeReference.forType(List.class));
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("Error al obtener los datos.");
		}
		return response.getBody();
	}
}