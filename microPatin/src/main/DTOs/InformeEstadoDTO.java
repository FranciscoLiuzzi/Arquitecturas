package main.DTOs;

public class InformeEstadoDTO {
    private int mantenimiento;
	private int operativos;
	
	//CONSTRUCTORES
	
	public InformeEstadoDTO(int mantenimiento, int operativos) {
		this.mantenimiento = mantenimiento;
		this.operativos = operativos;
	}

	//GET&SET
	
	public int getMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(int mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	public int getOperativos() {
		return operativos;
	}

	public void setOperativos(int operativos) {
		this.operativos = operativos;
	}
}