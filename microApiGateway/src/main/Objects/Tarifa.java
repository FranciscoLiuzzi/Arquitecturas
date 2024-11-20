package main.Objects;

import jakarta.persistence.*;
import lombok.Data;
import main.DTOs.TarifaDTO;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "tarifa")
public class Tarifa {
	
    @Id
    @Column(name = "tarifaId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tarifaId;
    
    @Column(name = "fecha")
    private Timestamp fecha;
    @Column(name = "fija")
    private double fija;
    @Column(name = "completa")
    private double completa;
    
    //CONSTRUCTORES
    
    public Tarifa() {
        super();
    }

    public Tarifa(double fija, double completa) {
        this(fija, completa,  new Timestamp(System.currentTimeMillis()));        
    }

    public Tarifa(double fija, double completa, Timestamp fecha) {
        this.fija = fija;
        this.completa = completa;
        this.fecha = fecha;
    }

    public Tarifa(TarifaDTO tarifaDTO) {
        this(tarifaDTO.getFija(), tarifaDTO.getCompleta(), tarifaDTO.getFecha());
    }
}