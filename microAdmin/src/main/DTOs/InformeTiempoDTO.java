package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InformeTiempoDTO {
	private long patinId;
    private int tiempoTotal;
    private String estado;
    
    //CONSTRUCTORES
    
    public InformeTiempoDTO(String estado, int tiempoUso, int tiempoPausa) {
        this.estado = estado;
        this.tiempoTotal = tiempoUso + tiempoPausa;
    }
}
