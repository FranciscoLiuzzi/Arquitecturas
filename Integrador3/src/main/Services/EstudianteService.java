package main.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import main.DTOs.EstudianteDTO;
import main.Objects.Estudiante;
import main.Repositories.EstudianteRepositoryImpl;

@Service("estudianteService")
public class EstudianteService{
	
	@Autowired
	private EstudianteRepositoryImpl estudianteRepository;

	@Transactional (readOnly = true)
	public List<EstudianteDTO> buscarAllEstudiantesOrderByApellido() throws Exception {
		return estudianteRepository.getAllEstudiantesOrderByApellido().stream().map(EstudianteDTO::new ).toList();
	}
	
	@Transactional (readOnly = true)
	public List<EstudianteDTO> buscarEstudiantesPorGenero(String genero) throws Exception{
		 return estudianteRepository.findByGenero(genero).stream().map(EstudianteDTO::new ).toList();
	}

	@Transactional (readOnly = true)
	public List<EstudianteDTO> findAll() throws Exception {
		return this.estudianteRepository.findAll().stream().map(EstudianteDTO::new ).toList();
	}

	@Transactional (readOnly = true)
	public EstudianteDTO findById(Integer id) throws Exception {
		return estudianteRepository.findById(id).map(EstudianteDTO::new).orElseThrow(
	            () -> new IllegalArgumentException("ID de usuario invalido:" + id));
	}

	@Transactional
	public EstudianteDTO save(EstudianteDTO entity) throws Exception {
		try{
            return new EstudianteDTO(this.estudianteRepository.save(new Estudiante(entity)));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Transactional
	public Estudiante update(Integer id, Estudiante entity) throws Exception {
		try{
            Optional<Estudiante> entityOpcional = estudianteRepository.findById(id);
            Estudiante estudiante = entityOpcional.get();
            estudiante = estudianteRepository.save(entity);
            return estudiante;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Transactional
	public void delete(Integer id) throws Exception {
		estudianteRepository.delete(estudianteRepository.findById(id).orElseThrow(
	            () -> new IllegalArgumentException("ID de usuario invalido:" + id)));
	}
	
	//Hay que de alguna manera buscar por ciudad
}
