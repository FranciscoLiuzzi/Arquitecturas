package main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import main.DTOs.TarifaDTO;
import main.DTOs.ViajeDTO;
import main.Services.ViajeService;

@RestController
@RequestMapping("/viajes")
public class ViajeController {
    
    @Autowired
    private ViajeService viajeService;
    
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
    
    @Operation(summary = "Retorna todos los viajes.", description = "Retorna todos los viajes")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader(value = "Authorization", required = false) String token){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Agrega un viaje.", description = "Agrega un viaje, depende de los microservicios de autenticacion, usuario y patin")
    @PostMapping("/alta/usuario/{idUsuario}/patin/{idPatin}")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @PathVariable long idUsuario, @PathVariable long idPatin) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.save(idUsuario, idPatin));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene un viaje por su id.", description = "Obtiene un viaje por su viajeId")
    @GetMapping("/buscar/{viajeId}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token, @PathVariable long viajeId) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.findById(viajeId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Eliminia un viaje por su id.", description = "Elimina un viaje por su viajeId")
    @DeleteMapping("/eliminar/{viajeId}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable long viajeId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            viajeService.delete(viajeId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el viaje con travelId: " + viajeId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Actualiza los datos de un viaje por su id.", description = "Actualiza los datos de un viaje por su viajeId")
    @PutMapping("/actualizar/{viajeId}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @PathVariable long viajeId, @RequestBody ViajeDTO entity){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            viajeService.update(viajeId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del viaje con travelId: " + viajeId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 
    
    @Operation(summary = "Finaliza un viaje por su id.", description = "Finaliza un viaje por su viajeId")
    @PutMapping("/finalizar/{viajeId}")
    public ResponseEntity<?> travelEnd(@RequestHeader("Authorization") String token, @PathVariable long viajeId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            viajeService.viajeEnd(viajeId);
            return ResponseEntity.status(HttpStatus.OK).body("Se finalizo correctamente el viaje con travelId: " + viajeId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo finalizar el viaje, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    @Operation(summary = "Aplica una tarifa a partir de la fecha dada.", description = "Aplica una tarifa a partir de la fecha dada")   
    @PostMapping("/tarifas/alta")
    public ResponseEntity<?> saveFare(@RequestHeader("Authorization") String token, @RequestBody TarifaDTO entity) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.saveTarifa(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}