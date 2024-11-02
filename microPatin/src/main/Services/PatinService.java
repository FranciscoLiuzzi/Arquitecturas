package main.Services;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.DTOs.PatinDTO;
import main.Objects.Patin;
import main.Repositories.PatinRepository;
import java.sql.Timestamp;
import java.time.LocalDate;

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
	
	
}
