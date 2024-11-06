package main.Objects;

import jakarta.persistence.*;
import main.DTOs.AdministradorDTO;

@Entity
@Data
@Table(name = "staff")
public class Administrador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="admin_id")
	private long adminId;

	@Column(name="rol")
	private String rol;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="nro_celular")
	private long nroCelular;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;

	//CONSTRUCTORES

	public Administrador(){
		super();
	}

	public Administrador(String rol, String nombre, String apellido, long nroCelular, String email, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;	
		this.rol = rol;
		this.password = password;
	}

	public Administrador(AdministradorDTO dto){
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.rol = dto.getRol();
		this.password = dto.getPassword();
	}
	
	//GET&SET

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getNroCelular() {
		return nroCelular;
	}

	public void setNroCelular(long nroCelular) {
		this.nroCelular = nroCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getAdminId() {
		return adminId;
	}
}
