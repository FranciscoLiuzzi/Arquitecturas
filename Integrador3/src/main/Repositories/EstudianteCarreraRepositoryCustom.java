package main.Repositories;

import java.util.List;
import main.DTOs.CarreraInscriptosGraduadosDTO;
import main.DTOs.EstudianteDTO;

public interface EstudianteCarreraRepositoryCustom {
	List<CarreraInscriptosGraduadosDTO> informeCarreras();
    List<EstudianteDTO> buscarPorCarrerasYCiudadResidencia(String carrera, String ciudad);
}
