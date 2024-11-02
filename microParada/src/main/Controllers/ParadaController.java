package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.DTOs.ParadaDTO;
import main.Services.ParadaService;

@RestController
@RequestMapping("/paradas")
public class ParadaController {
	
	 @Autowired
	 private ParadaService paradaService;
	 
	 @GetMapping("")
	 public ResponseEntity<?> getAll(){
        try{
        	return ResponseEntity.status(HttpStatus.OK).body(paradaService.findAll());
	    }catch (Exception e){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	    }
	 }

	 @GetMapping("/buscar/{paradaId}")
	 public ResponseEntity<?> getById(@PathVariable Long paradaId) {
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(paradaService.findById(paradaId));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	     }
	 }

	 @PostMapping("/alta")
	 public ResponseEntity<?> save(@RequestBody ParadaDTO entity){
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(paradaService.save(entity));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }

	 @DeleteMapping("/eliminar/{paradaId}")
	 public ResponseEntity<?> delete(@PathVariable Long paradaId){
	     try{
	    	 paradaService.delete(paradaId);
	         return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la carrera con id: " + paradaId);
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }
	 
	 @PutMapping("/actualizar/{paradaId}")
	 public ResponseEntity<?> update(@PathVariable long stationId, @RequestBody ParadaDTO entity){
       try{
    	   paradaService.update(stationId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos de la parada");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la parada.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 
	 
	 ////////////////////////////////////////////////////
	 
	 @GetMapping("/verificar/X/{x}/Y/{y}")
    public ResponseEntity<?> verify(@PathVariable String x, @PathVariable String y){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.findByXAndY(x, y));
        }catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo verificar la parada.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
