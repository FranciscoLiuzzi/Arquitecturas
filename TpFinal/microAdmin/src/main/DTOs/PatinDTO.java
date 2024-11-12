package main.DTOs;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PatinDTO {
    private long patinId;
	private String x;
	private String y;
    private String estado;
    private double km;
    private int uso;
    private int pausa;

	public PatinDTO(String x, String y, String estado, double km, int uso, int pausa) {
		this.x = x;
		this.y = y;
		this.estado = estado;
		this.km = km;
		this.uso = uso;
		this.pausa = pausa;		
	}

	public PatinDTO(PatinDTO dto, String estado){
		this.x = dto.getX();
		this.y = dto.getY();
		this.estado = estado;
		this.km = dto.getKm();
		this.uso = dto.getUso();
		this.pausa = dto.getPausa();
	}
}