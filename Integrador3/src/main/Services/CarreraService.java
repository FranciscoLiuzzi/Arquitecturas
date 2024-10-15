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
import main.DTOs.CarreraInscriptosGraduadosDTO;
import main.DTOs.EstudiantesEnCarreraDTO;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Objects.EstudianteCarrera;
import main.Repositories.CarreraRepository;
import main.Repositories.EstudianteCarreraRepository;
import main.Repositories.EstudianteRepositoryImpl;

@Service("carreraService")
public class CarreraService{

	@Autowired
	private CarreraRepository carreraRepository;
	
	@Autowired
	private EstudianteRepositoryImpl estudianteRepository;
	 
	@Autowired
	private EstudianteCarreraRepository carreraEstudianteRepository;
	 
	
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
	
	////////////////////////////////////////////////////////////////////////
	
	@Transactional
	public void matricular(Integer e, String c) {
		Objects.requireNonNull(e);
		Objects.requireNonNull(c);

		Estudiante estudiante = estudianteRepository.findById(e)
				.orElseThrow(() -> new IllegalArgumentException("ID de Estudiante invalido:" + e));
		
		System.out.println("Buscando carrera: " + c);
		Carrera carrera = carreraRepository.findByNombreIgnoreCase(c)
				.orElseThrow(() -> new IllegalArgumentException("ID de Carrera invalido:" + c));

		if (carreraEstudianteRepository.findByEstudianteAndCarrera(estudiante, carrera).isPresent()) {
			throw new IllegalArgumentException("El estudiante ya esta inscripto");
		}
		Timestamp currentTime = new Timestamp(new Date().getTime());
		EstudianteCarrera nuevo = new EstudianteCarrera(estudiante, carrera, currentTime, null);
		carreraEstudianteRepository.save(nuevo);
	}

	@Transactional
	public void desmatricular(Integer e, String c) {
		Objects.requireNonNull(e);
		Objects.requireNonNull(c);

		Estudiante estudiante = estudianteRepository.findById(e)
				.orElseThrow(() -> new IllegalArgumentException("ID de Estudiante invalido:" + e));

		Carrera carrera = carreraRepository.findByNombreIgnoreCase(c)
				.orElseThrow(() -> new IllegalArgumentException("Nombre de Carrera invalido:" + c));

		carreraEstudianteRepository.deleteByEstudianteAndCarrera(estudiante, carrera);
	}

	@Transactional(readOnly = true)
	public List<CarreraInscriptosGraduadosDTO> informeCarreras() {
		return this.carreraEstudianteRepository.informeCarreras();
	}

	@Transactional(readOnly = true)
	public List<EstudiantesEnCarreraDTO> carrerasOrdenadas() {
		return this.carreraRepository.carrerasOrdenadas();
	}
}
