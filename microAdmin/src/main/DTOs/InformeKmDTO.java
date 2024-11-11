package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InformeKmDTO {
	private long patinId;
    private String estado;
    private double km;
    
    //CONSTRUCTORES
    
    public InformeKmDTO(PatinDTO patin) {
        this.patinId = patin.getPatinId();
        this.estado = patin.getEstado();
        this.km = patin.getKm();
    }

    public InformeKmDTO(String estado, double km) {
        this.estado = estado;
        this.km = km;
    }
}
