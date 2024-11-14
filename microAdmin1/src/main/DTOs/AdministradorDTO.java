package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Administrador;

@Getter
@RequiredArgsConstructor
public class AdministradorDTO {
	private String nombre;
	private String apellido;
	private long nroCelular;
	private String email;
	private String rol;
	private String password;
	
	//CONSTRUCTORES
	
	public AdministradorDTO(Administrador admin) {
		this.nombre = admin.getNombre();
		this.apellido = admin.getApellido();
		this.nroCelular = admin.getNroCelular();
		this.email = admin.getEmail();
		this.rol = admin.getRol();
		this.password = admin.getPassword();
	}

	public AdministradorDTO(String rol, String nombre, String apellido, long nroCelular, String email, String password) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.rol = rol;
		this.password = password;
	}

	public AdministradorDTO(AdministradorDTO dto) {
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.rol = dto.getRol();
		this.password = dto.getPassword();
	}
}
