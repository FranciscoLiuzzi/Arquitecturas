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
	
//	@Transactional
//	public EstudianteDTO buscarEstudianteByLibreta(int libreta) {
//		this.em.getTransaction().begin();
//		String jpql = "SELECT NEW main.DTOs.EstudianteDTO(p.nombre, p.apellido, p.edad, p.ciudadResidencia, p.genero, p.dni, p.libreta) " +
//						"FROM Estudiante p WHERE p.libreta = ?1";
//		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
//		query.setParameter(1, libreta);
//		EstudianteDTO res = query.getSingleResult();
//		this.em.getTransaction().commit();
//		return res;
//	}  Esto deberia ser trivial con el find by id si la libreta ya es el id

	@Transactional
	public List<EstudianteDTO> buscarAllEstudiantesOrderByApellido() throws Exception {
        try{
        	var resultado = estudianteRepository.getAllEstudiantesOrderByApellido();
        	return resultado;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}
	
	@Transactional
	public List<String> buscarGeneros() throws Exception{
        try{
        	var resultado = estudianteRepository.getGeneros();
            return resultado;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}
	
	@Transactional
	public List<EstudianteDTO> buscarEstudiantesPorGenero(String genero) throws Exception{
        try{
        	var resultado = estudianteRepository.getEstudiantesPorGenero(genero);
        	return resultado;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Transactional
	public List<Estudiante> findAll() throws Exception {
		return estudianteRepository.findAll();
	}

	@Transactional
	public Estudiante findById(Integer id) throws Exception {
		try{
            Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(id);
            return estudianteBuscado.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
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
	public boolean delete(Integer id) throws Exception {
		try{
            if(estudianteRepository.existsById(id)){
            	estudianteRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}
}
