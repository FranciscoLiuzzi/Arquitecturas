package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Patin;

@Getter
@RequiredArgsConstructor
public class PatinDTO {
	private long patinId;
	private String x;
	private String y;
    private String estado;
    private double km;
    private int uso;
    private int pausa;
    
    //CONSTRUCTORES
    
	public PatinDTO(Patin patin) {
		this.patinId = patin.getPatinId();
		this.x = patin.getX();
		this.y = patin.getY();
		this.estado = patin.getEstado();
		this.km = patin.getKm();
		this.uso = patin.getUso();
		this.pausa = patin.getPausa();
	}

	public PatinDTO(String x, String y,String estado, double km, int uso, int pausa) {
		this.x = x;
		this.y = y;
		this.estado = estado;
		this.km = km;
		this.uso = uso;
		this.pausa = pausa;		
	}
}
