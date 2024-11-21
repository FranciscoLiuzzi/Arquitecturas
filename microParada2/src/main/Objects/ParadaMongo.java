package main.Objects;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import main.DTOs.ParadaDTO;

import org.springframework.data.annotation.Id;

@Document(collection = "paradas")
@Data
public class ParadaMongo {
    @Id
    private String id; // El campo "_id" de MongoDB
    private String nombre;
    private String x;
    private String y;

    public ParadaMongo(){
        super();
    }

    public ParadaMongo(String nombre, String x, String y) {
		super();
        this.nombre = nombre;
		this.x = x;
		this.y = y;
	}

    public ParadaMongo(ParadaDTO dto){
		this.x = dto.getX();
		this.y = dto.getY();
        this.nombre = dto.getNombre();
	}
}