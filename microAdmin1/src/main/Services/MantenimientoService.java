package main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import main.DTOs.PatinDTO;

@Service("MantenimientoService")
public class MantenimientoService{
	
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final String SCOOTERS_URL = "http://localhost:8002/patines";

	@Transactional
	public void updatepatinState(long idpatin, String estado) {
		ResponseEntity<PatinDTO> patin = restTemplate.getForEntity(SCOOTERS_URL + idpatin, PatinDTO.class);
		if (patin.getStatusCode() != HttpStatus.OK) {
			throw new IllegalArgumentException("ID de patin invalido: " + idpatin);
		}
		
		HttpEntity<PatinDTO> requestEntity;
		PatinDTO patinBody = patin.getBody();
		if (patinBody != null) {
			System.out.println(patinBody); 
			patinBody.setEstado(estado);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			requestEntity = new HttpEntity<>(patinBody, headers);
		} else {
			throw new RuntimeException("Error al actualizar el estado del monopatin. El cuerpo de la respuesta es nulo.");
		}

		try {
			ResponseEntity<Void> response = restTemplate.exchange("http://localhost:8002/patines/actualizar/" + idpatin, HttpMethod.PUT, requestEntity, Void.class);
			if (response.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error al actualizar el estado del monopatin" + idpatin);
			}
		} catch (Exception e) {
			throw new RuntimeException("Super Error al actualizar el estado del monopatin. ", e);
		}
	}
}