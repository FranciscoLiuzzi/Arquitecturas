package main.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.DTOs.InformeEstadoDTO;
import main.DTOs.InformeKmDTO;
import main.DTOs.InformeKmUsoDTO;
import main.DTOs.InformeTiempoDTO;
import main.DTOs.InformeUsoDTO;
import main.DTOs.ParadaDTO;
import main.DTOs.PatinDTO;
import main.Objects.Patin;
import main.Repositories.PatinRepository;
import java.sql.Timestamp;
import java.time.LocalDate;
import org.springframework.web.client.RestTemplate;

@Service("patinService")
public class PatinService{

	@Autowired
	private PatinRepository patinRepository;
	
	//CRUD
	
	@Transactional(readOnly = true)
	public List<PatinDTO> findAll() {
		return this.patinRepository.findAll().stream().map(PatinDTO::new ).toList();
	}

	@Transactional(readOnly = true)
	public PatinDTO findById(Long id) {
		return this.patinRepository.findById(id).map(PatinDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID patin invalido: " + id));
	}
	
	@Transactional
	public PatinDTO save(PatinDTO entity) {
		return new PatinDTO(this.patinRepository.save(new Patin(entity.getX(), entity.getY())));
	}

	@Transactional
	public void delete(Long id) {
		patinRepository.delete(patinRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID patin invalido: " + id)));
	}

	@Transactional
	public void update(Long id, PatinDTO entity) {
		Patin patin = patinRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID patin invalido: " + id));
		patin.setFromDTO(entity);
		patinRepository.save(patin);
	}
	
	////////////////////////////////////////////////////////////////////////
	
	@Transactional
	public List<InformeKmDTO> findByKilometros(){
		return this.patinRepository.findAllByOrderByKmDesc().stream().map(InformeKmDTO::new ).collect(Collectors.toList());
	}

	@Transactional
	public List<InformeUsoDTO> findByTiempoUso(){
		return this.patinRepository.findAllByOrderByUsoDesc().stream().map(InformeUsoDTO::new).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
    public List<InformeKmUsoDTO> findByKilometrosConTiempoUso() {
        List<PatinDTO> patines = patinRepository.findAll().stream().map(PatinDTO::new).collect(Collectors.toList());
		List<InformeKmUsoDTO> patinesRes = new ArrayList<>();
		for (PatinDTO patin : patines) {
			InformeKmUsoDTO res = new InformeKmUsoDTO(patin);			
			patinesRes.add(res);
		}
		return patinesRes;
	}

	@Transactional(readOnly = true)
	public List<InformeTiempoDTO> findByTiempoTotal() {
		return this.patinRepository.getTiempo().stream().map(InformeTiempoDTO::new).collect(Collectors.toList());
	}
    
	@Transactional(readOnly = true)
	public InformeEstadoDTO findOperativosMantenimiento(){
		return this.patinRepository.getCantidadOperativosMantenimiento();
	}

	@Transactional(readOnly = true)
	public List<Patin> getScootersCercanos(Double latitud, Double longitud){
		List<Patin> patines = this.patinRepository.findAll();
		List<Patin> resultado = new ArrayList<Patin>();
		for(Patin patin : patines){
			if(patin.calcularDistancia(latitud,longitud) <= 1){//mayor a 5 kilometros
				resultado.add(patin);
			}
		}
		return resultado;
	}

	public ParadaDTO scooterEnEstacion(long scooterId) throws Exception {

		PatinDTO patin = this.findById(scooterId);

		String estacionUrl = "http://localhost:8001/estaciones/verificar/latitud/" + patin.getX() +  "/longitud/" + patin.getY();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		HttpEntity<ParadaDTO> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<ParadaDTO> response = restTemplate.exchange(estacionUrl, 
									HttpMethod.GET, 
									requestEntity, 
									ParameterizedTypeReference.forType(ParadaDTO.class));
		switch (response.getStatusCode().value()) {
			case 200:
				return response.getBody();
			case 204:
				return null;
			default:
				throw new Exception("Error al obtener los datos." + response.getStatusCode() + response.getBody());
		}
	}
}
