package main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import main.DTOs.ParadaDTO;
import main.DTOs.PatinDTO;
import main.Services.PatinService;

@RestController
@RequestMapping("/patines")
public class PatinController {
	
	 @Autowired
	 private PatinService patinService;
	 
	 private static final String TOKEN_VALIDATION_URL = "http://localhost:8081/auth/validar";
	 
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
	 
	 //CRUD
	 
	 @Operation(summary = "Obtiene todas los monopatines.", description = "Obtiene todos los monopatines")
	 @GetMapping("")
	 public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){ 
	     ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
	     if(response.getStatusCode() != HttpStatus.OK){
	         return response;
	    }
        try{
        	return ResponseEntity.status(HttpStatus.OK).body(patinService.findAll());
	    }catch (Exception e){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	    }
	 }
	 
	 @Operation(summary = "Obtiene un monopatin por su id.", description = "Obtiene un monopatin por su scooterId")
	 @GetMapping("/{patinId}")
	 public ResponseEntity<?> getById(@RequestHeader("Authorization") String token,@PathVariable long patinId) {        
	     ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
	     if(response.getStatusCode() != HttpStatus.OK){
	         return response;
	     }
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(patinService.findById(patinId));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.\"\n\"error\":\"" + e.getMessage()+"\"}");
	     }
	 }
	 
	 @Operation(summary = "Agrega un monopatin.", description = "Agrega un monopatin")
	 @PostMapping("/alta")
	 public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @RequestBody PatinDTO entity){
		 ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
	     if(response.getStatusCode() != HttpStatus.OK){
	         return response;
	     }
	     try{
	         return ResponseEntity.status(HttpStatus.OK).body(patinService.save(entity));
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }
	 
	 @Operation(summary = "Eliminia un monopatin por su id.", description = "Elimina una monopatin por su scooterId")
	 @DeleteMapping("/eliminar/{patinId}")
	 public ResponseEntity<?> delete(@RequestHeader("Authorization") String token,@PathVariable long patinId){       
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
	     try{
	    	 patinService.delete(patinId);
	         return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el patin con id: " + patinId);
	     }catch (Exception e){
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar.\"\n\"error\":\""+e.getMessage()+"\"}");
	     }
	 }
	 
	 @Operation(summary = "Actualiza los datos de un monopatin por su id.", description = "Actualiza un monopatin por su patinId")
	 @PutMapping("/actualizar/{patinId}")
	 public ResponseEntity<?> update(@RequestHeader("Authorization") String token,@PathVariable long patinId, @RequestBody PatinDTO entity){        
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
	        try{
	        	patinService.update(patinId,entity);
	            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del patin con id: " + entity.getPatinId());
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
	        }
	    }
	 
	 ////////////////////////////////////////////////////
	
	@Operation(summary = "Verifica si un monopatin esta en una parada.", description = "Verifica si un monopatin esta en una parada.")
    @GetMapping("/parada/{patinId}")
	public ResponseEntity<?> scooterEnEstacion(@RequestHeader("Authorization") String token,@PathVariable long patinId){        
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
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
	
	@Operation(summary = "Obtengo un reporte de monopatines ordenados por kilometros", description = "Obtengo un reporte de monopatines ordenados por kilometros")
    @GetMapping("/informe/km/sinUso")
	public ResponseEntity<?> getReporteByKilometros(@RequestHeader("Authorization") String token){        
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findByKm());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
	
	 @Operation(summary = "Obtiene un reporte de monopatines por kilometros y tiempo de uso.", description = "Obtiene un reporte de monopatines por kilometros y tiempo de uso")
    @GetMapping("/informe/km/conUso")
	 public ResponseEntity<?> getReporteByKilometrosConTiempoUso(@RequestHeader("Authorization") String token){        
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findByKmConUso());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
	 
	 @Operation(summary = "Obtengo un reporte de monopatines ordenados por tiempo de uso", description = "Obtengo un reporte de monopatines ordenados por tiempo de uso")
    @GetMapping("/informe/tiempoUso")
	 public ResponseEntity<?> getReporteByTiempoUso(@RequestHeader("Authorization") String token){        
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findByTiempoUso());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }   
	 
	 @Operation(summary = "Obtengo un reporte de monopatines ordenados por tiempo total (En uso + En pausa)", description = "Obtengo un reporte de monopatines ordenados por tiempo total (En uso + En pausa)")
    @GetMapping("/informe/tiempoTotal")
	 public ResponseEntity<?> getReporteByTiempoTotal(@RequestHeader("Authorization") String token){        
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findByTiempoTotal());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }  
	 
	 @Operation(summary = "Obtengo un reporte de cantidad de monopatines operativos vs en mantenimiento", description = "Obtengo un reporte de cantidad de monopatines operativos vs en mantenimiento")
    @GetMapping("/informe/cantidadOperativosMantenimiento")
	 public ResponseEntity<?> getReporteOperativosMantenimiento(@RequestHeader("Authorization") String token){        
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.findOperativosMantenimiento());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }  
	 
	 @Operation(summary = "Obtiene todos los monopatines.", 
             description = "Obtiene todos los monopatines cerca de una coordenada (ejemplo que funciona latitud -37.327754, longitud -59.138998)")
    @GetMapping("/X/{x}/Y/{y}")
	 public ResponseEntity<?> getAllCercanos(@RequestHeader("Authorization") String token,@PathVariable Double x, @PathVariable Double y){
	        
	        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
	        if(response.getStatusCode() != HttpStatus.OK){
	            return response;
	        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(patinService.getPatinesCercanos(x,y));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
