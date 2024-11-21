package main.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.DTOs.ParadaDTO;
import main.Objects.ParadaMongo;
import main.Repositories.ParadaMongoRepository;

@Service("paradaService")
public class ParadaService{

	@Autowired
	private ParadaMongoRepository paradaMongoRepository;
	
	//CRUD
	
	@Transactional (readOnly = true)
	public List<ParadaDTO> findAll() {
		return this.paradaMongoRepository.findAll().stream().map(ParadaDTO::new ).toList();
	}

	
	@Transactional (readOnly = true)
	public ParadaDTO findById(String id) {
		return paradaMongoRepository.findById(id).map(ParadaDTO::new).orElseThrow(
			() -> new IllegalArgumentException("ID de parada invalido:" + id));
	}
	
	@Transactional
	public ParadaDTO save(ParadaDTO entity) {
		return new ParadaDTO(this.paradaMongoRepository.save(new ParadaMongo(entity)));
	}

	@Transactional
	public void delete(String id) {
		paradaMongoRepository.delete(paradaMongoRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("ID de parada invalido:" + id)));
	}
	
	@Transactional
	public void update(String id, ParadaDTO entity) throws Exception{
		ParadaMongo parada = paradaMongoRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("ID de estacion invalido: " + id));
			parada.setX(entity.getX());
			parada.setY(entity.getY());
			parada.setNombre(entity.getNombre());
			paradaMongoRepository.save(parada);
		}
	
	////////////////////////////////////////////////////////////////////////
	
	@Transactional(readOnly = true)
	public ParadaDTO findByXAndY(String X, String Y) throws Exception{
		ParadaMongo parada = this.paradaMongoRepository.findByXAndY(X, Y);
		System.out.println("llego aca");
		System.out.println(parada);
		ParadaDTO paradaDTO = new ParadaDTO(parada);
	 // Copia los atributos de Station a StationDTOBeanUtils.copyProperties(station, stationDTO); // Copia los atributos de Station a StationDTO
		return paradaDTO;
	}
}
