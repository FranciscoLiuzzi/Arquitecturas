package Unidad1.Objects;

public class Ejemplo {
	private Integer id;
	private String name;
	
	public Ejemplo(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//Entidad de ejemplo, tiene los getters y setters de los atributos (sin poder modificar el ID) y sabe pasarse a String para facilitar impresion
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + ", Nombre: " + this.getName() + "";
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public Integer getId() {
		return id;
	}
}
