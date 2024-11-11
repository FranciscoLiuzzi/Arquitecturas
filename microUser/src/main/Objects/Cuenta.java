package main.Objects;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.Data;
import main.DTOs.CuentaDTO;

@Entity
@Data
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cuenta_Id")
    private long cuentaId;
    @Column(name="fecha_alta")
    private Timestamp fechaAlta;
    @Column(name = "habilitada")
	private boolean habilitada;
	@Column(name = "id_mpago")
	private String idMPago;
    @Column(name = "saldo", nullable = false)
	private Double saldo;
	
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<CuentaUsuario> usuarios;
    
    //CONSTRUCTORES
    
    public Cuenta() {
        super();
        this.usuarios = new HashSet<>();
    }

    public Cuenta(boolean habilitada, String idMPago, Double saldo) {
        super();
        this.fechaAlta = new Timestamp(System.currentTimeMillis());
        this.habilitada = habilitada;
		this.idMPago = idMPago;
        this.saldo = saldo;
        this.usuarios = new HashSet<>();
    }

    public Cuenta(CuentaDTO dto) {
        this.fechaAlta = new Timestamp(System.currentTimeMillis());
        this.habilitada = dto.isHabilitada();
        this.usuarios = new HashSet<>();
        this.idMPago = dto.getIdMPago();
        this.saldo = dto.getSaldo();
    }
}