package main.Repositories;

import java.util.List;
import main.DTOs.EstudiantesEnCarreraDTO;

public interface CarreraRepositoryCustom {
	List<EstudiantesEnCarreraDTO> carrerasOrdenadas();
}