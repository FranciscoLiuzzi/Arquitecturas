package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	 @GetMapping("/{scooterId}")
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
	 
	 
}
