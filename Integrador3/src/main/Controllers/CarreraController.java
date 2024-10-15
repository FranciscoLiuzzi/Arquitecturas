package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.DTOs.CarreraDTO;
import main.Services.CarreraService;

@RestController
@RequestMapping("/carreras")
public class CarreraController {
	
	 @Autowired
	 private CarreraService carreraService;
	 
	 @GetMapping("")
	 public ResponseEntity<?> getAll(){
        try{
        	return ResponseEntity.status(HttpStatus.OK).body(carreraService.findAll());
	    }catch (Exception e){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	    }
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<?> getById(@PathVariable Integer id) {
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(carreraService.findById(id));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	     }
	 }

	 @PostMapping("/alta")
	 public ResponseEntity<?> save(@RequestBody CarreraDTO entity){
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(carreraService.save(entity));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }

	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> delete(@PathVariable Integer id){
	     try{
	         carreraService.delete(id);
	         return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la carrera con id: " + id);
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }
}
