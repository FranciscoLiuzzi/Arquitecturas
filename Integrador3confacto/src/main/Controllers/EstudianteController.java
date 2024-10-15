package main.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.DTOs.EstudianteDTO;
import main.Services.EstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
		@Autowired
		private EstudianteService estudianteService;
		
		@GetMapping("")
	    public ResponseEntity<?> getAll(){
	        try{
	            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAll());
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
	        }
	    }

	    @GetMapping("genero/{genero}")
	    public ResponseEntity<?> getAllByGenero(@PathVariable String genero){
	        try{
	            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.buscarEstudiantesPorGenero(genero));
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
	        }
	    }
}
