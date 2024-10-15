package main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import main.DTOs.EstudianteDTO;
import main.Services.EstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
		@Autowired
		private EstudianteService estudianteService;
		
		@GetMapping("/all")
	    public List<EstudianteDTO> getAllEstudiantes() {
	        return estudianteService.getAllEstudiantesOrderByApellido();
	    }
}
