package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Patin;

@Getter
@RequiredArgsConstructor
public class InformeTiempoDTO {
	private long patinId;
    private int tiempoTotal;
    private String estado;
    
    //CONSTRUCTORES
    
    public InformeTiempoDTO(Patin patin) {
        this.patinId = patin.getPatinId();
        this.estado = patin.getEstado();
        this.tiempoTotal = patin.getUso() + patin.getPausa();
    }

    public InformeTiempoDTO(String estado, int tiempoDeUso, int tiempoEnPausa) {
        this.estado = estado;
        this.tiempoTotal = tiempoDeUso + tiempoEnPausa;
    }
}