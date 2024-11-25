package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Usuario;

@Getter
@RequiredArgsConstructor
public class UsuarioDTO {
	private long usuarioId;
	private String nombre;
	private String apellido;
	private long nroCelular;
	private String email;
	private String password;

	public UsuarioDTO(Usuario usuario) {
		this.usuarioId = usuario.getUsuarioId();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.nroCelular = usuario.getNroCelular();
		this.email = usuario.getEmail();
		this.password = usuario.getPassword();
	}

	public UsuarioDTO(long usuarioId, String nombre, String apellido, long nroCelular, String email, String password) {
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.password = password;
	}
}