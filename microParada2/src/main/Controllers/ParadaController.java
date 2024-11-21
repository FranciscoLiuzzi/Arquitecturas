package main.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import main.DTOs.ParadaDTO;
import main.Services.ParadaService;

@RestController
@RequestMapping("/paradas")
public class ParadaController {
	
	 @Autowired
	 private ParadaService paradaService;
	 
	 private static final String TOKEN_VALIDATION_URL = "http://localhost:8082/auth/validar";
	 
	 private ResponseEntity<String> validarToken(String token, List<String> roles) {
	        ResponseEntity<String> response = new RestTemplate().postForEntity(TOKEN_VALIDATION_URL, token, String.class);
	        if (response.getStatusCode() != HttpStatus.OK){
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
	        }
	        if(!(roles.contains(response.getBody()))){
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tipo de usuario no valido");
	        }
	        return response;
	    }
	 
	 @Operation(summary = "Obtiene todas las estaciones.", description = "Obtiene todos las estaciones")
	 @GetMapping("")
	 public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
        try{
        	return ResponseEntity.status(HttpStatus.OK).body(paradaService.findAll());
	    }catch (Exception e){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	    }
	 }
	 
	 @Operation(summary = "Obtiene una estacion por su id.", description = "Obtiene un estacion por su paradaId")
	 @GetMapping("/buscar/{paradaId}")
	 public ResponseEntity<?> getById(@RequestHeader("Authorization") String token,@PathVariable String paradaId) {
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(paradaService.findById(paradaId));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	     }
	 }
	 
	 @Operation(summary = "Agrega una estacion.", description = "Agrega una estacion")
	 @PostMapping("/alta")
	 public ResponseEntity<?> save(@RequestHeader("Authorization") String token,@RequestBody ParadaDTO entity){
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(paradaService.save(entity));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }
	 
	 @Operation(summary = "Eliminia una estacion por su id.", description = "Elimina una estacion por su paradaId")
	 @DeleteMapping("/eliminar/{paradaId}")
	 public ResponseEntity<?> delete(@RequestHeader("Authorization") String token,@PathVariable String paradaId){
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
	     try{
	    	 paradaService.delete(paradaId);
	         return ResponseEntity.status(HttpStatus.OK).body("Se elimino la parada id: " + paradaId);
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }
	 
	 @Operation(summary = "Actualiza los datos de una estacion por su id.", description = "Actualiza una estacion por su paradaId")
	 @PutMapping("/actualizar/{paradaId}")
	 public ResponseEntity<?> update(@RequestHeader("Authorization") String token,@PathVariable String paradaId, @RequestBody ParadaDTO entity){
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
       try{
    	   paradaService.update(paradaId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo la parada");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 
	 
	 ////////////////////////////////////////////////////
	 
	 @Operation(summary = "Verifica si las coordenadas son validas.", description = "Verifica que las coordenadas proveidas coinciden con als coordenadas de una estacion")
	 @GetMapping("/verificar/X/{x}/Y/{y}")
	 public ResponseEntity<?> verify(@RequestHeader("Authorization") String token,@PathVariable String x, @PathVariable String y){
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(paradaService.findByXAndY(x, y));
        }catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo verificar la parada.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
