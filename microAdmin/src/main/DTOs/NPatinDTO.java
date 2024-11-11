package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NPatinDTO {
	private String x;
	private String y;
    private String estado;
    private double km;
    private int tiempoUso;
    private int tiempoPausa;
    
    //CONSTRUCTORES
    
	public NPatinDTO(String x, String y,String estado, double km, int tiempoUso, int tiempoPausa) {
		this.x = x;
		this.y = y;
		this.estado = estado;
		this.km = km;
		this.tiempoUso = tiempoUso;
		this.tiempoPausa = tiempoPausa;		
	}
}
