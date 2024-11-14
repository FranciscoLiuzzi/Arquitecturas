package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.DTOs.ParadaDTO;
import main.DTOs.PatinDTO;
import main.Services.PatinService;

@RestController
@RequestMapping("/patines")
public class PatinController {
	
	 @Autowired
	 private PatinService patinService;
	 
	 //CRUD
	 
	 @GetMapping("")
	 public ResponseEntity<?> getAll(){
        try{
        	return ResponseEntity.status(HttpStatus.OK).body(patinService.findAll());
	    }catch (Exception e){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	    }
	 }

	 @GetMapping("/{patinId}")
	 public ResponseEntity<?> getById(@PathVariable long patinId) {
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(patinService.findById(patinId));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	     }
	 }

	 @PostMapping("/alta")
	 public ResponseEntity<?> save(@RequestBody PatinDTO entity){
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(patinService.save(entity));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }

	 @DeleteMapping("/eliminar/{patinId}")
	 public ResponseEntity<?> delete(@PathVariable long patinId){
	     try{
	    	 patinService.delete(patinId);
	         return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el patin con id: " + patinId);
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }
	 
	 @PutMapping("/actualizar/{patinId}")
	    public ResponseEntity<?> update(@PathVariable long scooterId, @RequestBody PatinDTO entity){
	        try{
	        	patinService.update(scooterId,entity);
	            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del patin con id: " + entity.getPatinId());
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
	        }
	    }
	 
	 ////////////////////////////////////////////////////
	 
    @GetMapping("/parada/{patinId}")
    public ResponseEntity<?> patinEnParada(@PathVariable long patinId){
        try{
            ParadaDTO parada = patinService.patinEnParada(patinId);
            if (parada != null) {
                return ResponseEntity.status(HttpStatus.OK).body("El monopatin se encuentra en la estacion ubicada en : " + parada.getX() + ", " + parada.getY());
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body("El monopatin no se encuentra en ninguna estacion.");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/informe/km/sinUso")
    public ResponseEntity<?> getInformeByKm(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findByKm());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/informe/km/conUso")
    public ResponseEntity<?> getInformeByKmConUso(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findByKmConUso());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/informe/tiempoUso")
    public ResponseEntity<?> getInformeByUso(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findByTiempoUso());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }   

    @GetMapping("/informe/tiempoTotal")
    public ResponseEntity<?> getInformeByTiempoTotal(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findByTiempoTotal());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }  

    @GetMapping("/informe/cantidadOperativosMantenimiento")
    public ResponseEntity<?> getInformeOperativosMantenimiento(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findOperativosMantenimiento());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }  

    @GetMapping("/X/{x}/Y/{y}")
    public ResponseEntity<?> getAllCercanos(@PathVariable Double x, @PathVariable Double y){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.getPatinesCercanos(x,y));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
