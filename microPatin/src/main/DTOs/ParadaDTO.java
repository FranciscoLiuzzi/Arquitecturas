package main.DTOs;

//Esto puede estar mal...?

public class ParadaDTO {
	private String x;
	private String y;
	
	//CONSTRUCTORES
	
	public ParadaDTO(){
		super();
	}

	public ParadaDTO(String x, String y) {
		this.x = x;
		this.y = y;
	}

	//GET&SET
	
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
}
