package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//DTO Contiene los mismos atributos que la clase usuario

@Getter
@RequiredArgsConstructor
public class UsuarioDTO {
	private long usuarioId;
	private String nombre;
	private String apellido;
	private long nroCelular;
	private String email;

	public UsuarioDTO(long usuarioId, String nombre, String apellido, long nroCelular, String email) {
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
	}
}