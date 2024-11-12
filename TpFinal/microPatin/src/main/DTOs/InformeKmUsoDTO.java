package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Patin;

@Getter
@RequiredArgsConstructor
public class InformeKmUsoDTO {
    private long patinId;
    private double km;
    private int uso;
    
    //CONSTRUCTORES
    
    public InformeKmUsoDTO(Patin patin) {
        this.patinId = patin.getPatinId();
        this.km = patin.getKm();
        this.uso = patin.getUso();
    }

    public InformeKmUsoDTO(PatinDTO patin) {
        this.patinId = patin.getPatinId();
        this.km = patin.getKm();
        this.uso = patin.getUso();
    }

    public InformeKmUsoDTO(long patinId, double km, int uso) {
        this.patinId = patinId;
        this.km = km;
        this.uso = uso;
    }
}