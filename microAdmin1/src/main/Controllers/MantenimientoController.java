package main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import main.Services.MantenimientoService;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {
    
    @Autowired
    private MantenimientoService mantenimientoService;
    
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
    
    @PutMapping("/patines/actualizarEstado/{id}/estado/{estado}")
    public ResponseEntity<?> actualizarEstado(@RequestHeader("Authorization") String token, @PathVariable long id, @PathVariable String estado){
    	ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            mantenimientoService.updatepatinState(id, estado);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el estado del scooter: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el estado del scooter, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}