package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InformeEstadoDTO {
    private int mantenimiento;
	private int operativos;
	
	//CONSTRUCTORES
	
	public InformeEstadoDTO(int mantenimiento, int operativos) {
		this.mantenimiento = mantenimiento;
		this.operativos = operativos;
	}
}