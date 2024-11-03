package main.Repositories;

import java.util.List;
import main.DTOs.InformeEstadoDTO;
import main.Objects.Patin;

public interface PatinRepositoryCustom {
	List<Patin> getTiempo();
	
	InformeEstadoDTO getCantidadOperativosMantenimiento();
}