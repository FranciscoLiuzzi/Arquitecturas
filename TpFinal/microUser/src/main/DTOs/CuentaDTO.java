package main.DTOs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.Objects.Cuenta;
import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class CuentaDTO {
	private long cuentaId;
    private Timestamp fechaAlta;
	private boolean habilitada;
	private String idMPago;
	private Double saldo;

	public CuentaDTO(Cuenta cuenta) {
		this.cuentaId = cuenta.getCuentaId();
		this.fechaAlta = cuenta.getFechaAlta();
		this.habilitada = cuenta.isHabilitada();
		this.saldo = cuenta.getSaldo();
		this.idMPago = cuenta.getIdMPago();
	}

	public CuentaDTO(Timestamp fechaAlta, boolean habilitada, String idMPago, Double saldo) {
		this.fechaAlta = fechaAlta;
		this.habilitada = habilitada;
		this.idMPago = idMPago;
		this.saldo = saldo;
	}
}