package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Patin;

@Getter
@RequiredArgsConstructor
public class InformeKmDTO {
    private long patinId;
    private String estado;
    private double km;

    //CONSTRUCTORES
    
    public InformeKmDTO(Patin patin) {
        this.patinId = patin.getPatinId();
        this.estado = patin.getEstado();
        this.km = patin.getKm();
    }

    public InformeKmDTO(String estado, double km) {
        this.estado = estado;
        this.km = km;
    }
}