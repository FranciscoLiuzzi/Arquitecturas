package main.DTOs;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class CuentaDTO {
    private long cuentaId;
    private Timestamp fechaAlta;
	private boolean habilitada;
	private String idMPago;
	private Double saldo;
	
	//CONSTRUCTORES
	
	public CuentaDTO() {}

	public CuentaDTO(long cuentaId, Timestamp fechaAlta, boolean habilitada, String idMPago, Double saldo) {
        this.cuentaId = cuentaId;
		this.fechaAlta = fechaAlta;
		this.habilitada = habilitada;
		this.saldo = saldo;
		this.idMPago = idMPago;
	}
}