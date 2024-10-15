package main.Services;

import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import main.DTOs.EstudianteDTO;
import main.Objects.Estudiante;
import main.Repositories.EstudianteRepositoryImpl;

@Service("estudianteService")
public class EstudianteService implements CRUDService<Estudiante>  {
	
	@Autowired
	private EstudianteRepositoryImpl estudianteRepository;
	
	@Transactional
	public EstudianteDTO buscarEstudianteByLibreta(int libreta) {
		this.em.getTransaction().begin();
		String jpql = "SELECT NEW main.DTOs.EstudianteDTO(p.nombre, p.apellido, p.edad, p.ciudadResidencia, p.genero, p.dni, p.libreta) " +
						"FROM Estudiante p WHERE p.libreta = ?1";
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		query.setParameter(1, libreta);
		EstudianteDTO res = query.getSingleResult();
		this.em.getTransaction().commit();
		return res;
	}

	@Transactional
	public List<EstudianteDTO> buscarAllEstudiantesOrderByApellido() {
		this.em.getTransaction().begin();
		String jpql = "SELECT NEW main.DTOs.EstudianteDTO(p.nombre, p.apellido, p.edad, p.ciudadResidencia, p.genero, p.dni, p.libreta) " +
				  "FROM Estudiante p ORDER BY p.apellido, p.nombre";
	
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		List<EstudianteDTO> res = query.getResultList();
		this.em.getTransaction().commit();        
		return res;
	}
	
	@Transactional
	public List<String> buscarGeneros() {
		em.getTransaction().begin();
		String jpql = "SELECT DISTINCT e.genero FROM Estudiante e";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		List<String> res = query.getResultList();
		em.getTransaction().commit();
		return res;
	}
	
	@Transactional
	public List<EstudianteDTO> buscarEstudiantesPorGenero(String genero) {
		em.getTransaction().begin();
		String jpql = "SELECT NEW main.DTOs.EstudianteDTO(e.nombre,e.apellido,e.edad,e.ciudadResidencia,e.genero,e.dni,e.libreta) " +
						"FROM Estudiante e " +
						"WHERE (e.genero = :genero)" ;
		TypedQuery<EstudianteDTO> query = em.createQuery(jpql, EstudianteDTO.class);
		query.setParameter("genero", genero);
		List<EstudianteDTO> res = query.setMaxResults(30).getResultList();
		em.getTransaction().commit();
		return res;
	}

	@Override
	@Transactional
	public List<Estudiante> findAll() throws Exception {
		return estudianteRepository.findAll();
	}

	@Override
	@Transactional
	public Estudiante findById(Integer id) throws Exception {
		try{
            Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(id);
            return estudianteBuscado.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	@Transactional
	public Estudiante save(Estudiante entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Estudiante update(Integer id, Estudiante entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
