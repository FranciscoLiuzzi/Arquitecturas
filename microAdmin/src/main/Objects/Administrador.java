package main.Objects;

import jakarta.persistence.*;

@Entity
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

	public Administrador(AdminStaffDTO dto){
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.rol = dto.getRol();
		this.password = dto.getPassword();
	}
}
