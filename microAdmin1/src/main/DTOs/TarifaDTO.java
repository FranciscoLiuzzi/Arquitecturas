package main.DTOs;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class TarifaDTO {
	private long tarifaId;
    private Timestamp fecha;
    private double fija;
    private double completa;
    
    //CONSTRUCTORES
    
    public TarifaDTO() {
        super();
    }

    public TarifaDTO(double fija, double completa) {
        this(fija, completa,  new Timestamp(System.currentTimeMillis()));        
    }

    public TarifaDTO(double fija, double completa, Timestamp fecha) {
        this.fija = fija;
        this.completa = completa;
        this.fecha = fecha;
    }
}