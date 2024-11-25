package main.Objects;

import jakarta.persistence.*;
import lombok.Data;
import main.DTOs.UsuarioDTO;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="nro_celular")
	private long nroCelular;
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	@Transient
	private String password;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private long usuarioId;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private Set<CuentaUsuario> cuentas;
	
	//CONSTRUCTORES
	
	public Usuario(){
		super();
		this.cuentas = new HashSet<>();
	}

	public Usuario(String nombre, String apellido, long nroCelular, String email, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.password = password;
		this.cuentas = new HashSet<>();
	}

	public Usuario(UsuarioDTO dto){
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.cuentas = new HashSet<>();
	}
}