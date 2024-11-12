package main.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.DTOs.ParadaDTO;
import main.Objects.Parada;
import main.Repositories.ParadaRepository;

@Service("paradaService")
public class ParadaService{

	@Autowired
	private ParadaRepository paradaRepository;
	
	//CRUD
	
	@Transactional (readOnly = true)
	public List<ParadaDTO> findAll() {
		return this.paradaRepository.findAll().stream().map(ParadaDTO::new ).toList();
	}

	
	@Transactional (readOnly = true)
	public ParadaDTO findById(Long id) {
		return paradaRepository.findById(id).map(ParadaDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de parada invalido:" + id));
	}
	
	@Transactional
	public ParadaDTO save(ParadaDTO entity) {
		return new ParadaDTO(this.paradaRepository.save(new Parada(entity)));
	}

	@Transactional
	public void delete(Long id) {
		paradaRepository.delete(paradaRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de parada invalido:" + id)));
	}
	
	@Transactional
	public void update(Long id, ParadaDTO entity) {
		Parada parada = paradaRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de parada invalido: " + id));
		parada.setX(entity.getX());
		parada.setY(entity.getY());
		paradaRepository.save(parada);
	}
	
	////////////////////////////////////////////////////////////////////////
	
	@Transactional(readOnly = true)
	public ParadaDTO findByXAndY(String X, String Y) {
		return this.paradaRepository.findByXAndY(X, Y).map(ParadaDTO::new).orElseThrow(
			() -> new IllegalArgumentException("X e Y invalidos: " + X + " " + Y));
	}
}
