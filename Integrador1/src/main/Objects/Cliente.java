package main.Objects;

public class Cliente {
	private Integer id;
	private String name;
	private String email;
	
	public Cliente(Integer id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + ", Nombre: " + this.getName() + ", Email: " + this.getEmail() + "";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}
}
