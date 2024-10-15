package main.Services;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import main.DTOs.CarreraDTO;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Objects.EstudianteCarrera;
import main.Repositories.CarreraRepositoryImpl;
import main.Repositories.EstudianteCarreraRepositoryImpl;

@Service("carreraService")
public class CarreraService{

	@Autowired
	private CarreraRepositoryImpl carreraRepository;
	
	@Transactional (readOnly = true)
	public CarreraDTO findById(Integer id) {
		return carreraRepository.findById(id).map(CarreraDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de carrera invalido:" + id));
	}
	
	@Transactional
	public CarreraDTO save(CarreraDTO entity) {
		return new CarreraDTO(this.carreraRepository.save(new Carrera(entity)));
	}

	@Transactional (readOnly = true)
	public List<CarreraDTO> findAll() {
		return this.carreraRepository.findAll().stream().map(CarreraDTO::new ).toList();
	}

	@Transactional
	public void delete(Integer id) {
		carreraRepository.delete(carreraRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de Carrera invalido:" + id)));
	}
}
