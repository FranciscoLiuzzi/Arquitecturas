package main.Objects;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Objects;

@Entity
@Table(name = "cuenta_usuario")
@Data
@IdClass(CuentaUsuarioID.class)
public class CuentaUsuario {	
	@Id
	private Usuario usuario;

	@Id
	private Cuenta cuenta;
	
	//CONSTRUCTORES
	
	public CuentaUsuario(){
		super();
	}

	public CuentaUsuario(Usuario usuario, Cuenta cuenta) {
		this.usuario = Objects.requireNonNull(usuario, "user must not be null");
		this.cuenta = Objects.requireNonNull(cuenta, "account must not be null");
	}
}