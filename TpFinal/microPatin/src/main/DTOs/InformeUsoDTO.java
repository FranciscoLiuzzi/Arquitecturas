package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Patin;

@Getter
@RequiredArgsConstructor
public class InformeUsoDTO {
	 private long patinId;
    private int uso;
    private int pausa;
    private String estado;
    
    //CONSTRUCTORES
    
    public InformeUsoDTO(Patin patin) {
        this.patinId = patin.getPatinId();
        this.estado = patin.getEstado();
        this.uso = patin.getUso();
        this.pausa = patin.getPausa();
    }

    public InformeUsoDTO(String estado, int tiempoDeUso, int tiempoEnPausa) {
        this.estado = estado;
        this.uso = tiempoDeUso;
        this.pausa = tiempoEnPausa;
    }
}