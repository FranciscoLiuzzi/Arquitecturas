package tp1.personas.Objects;

public class Producto {
	private Integer id;
	private String name;
	private Integer value;
	
	public Producto(Integer id, String name, Integer value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + ", Nombre: " + this.getName() + ", Valor: " + this.getValue() + "";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getId() {
		return id;
	}
}
