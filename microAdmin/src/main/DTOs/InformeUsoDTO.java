package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InformeUsoDTO {
	private long patinId;
    private int tiempoUso;
    private int tiempoPausa;
    private String estado;
    
    //CONSTRUCTORES
    
    public InformeUsoDTO(String estado, int tiempoUso, int tiempoPausa) {
        this.estado = estado;
        this.tiempoUso = tiempoUso;
        this.tiempoPausa = tiempoPausa;
    }
}
