package main.DTOs;

import lombok.Data;
import main.Objects.Tarifa;
import java.sql.Timestamp;

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

    public TarifaDTO(Tarifa tarifa) {
        this.tarifaId = tarifa.getTarifaId();
        this.fecha = tarifa.getFecha();
        this.fija = tarifa.getFija();
        this.completa = tarifa.getCompleta();
    }
}