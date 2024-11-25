package main.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;
import main.DTOs.UsuarioDTO;
import main.Services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
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
    
    @Operation(summary = "Obtiene todos los usuarios.", description = "Obtiene todos los usuarios")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Agrega un nuevo usuario.", description = "Crea un usuario")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @RequestBody UsuarioDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(entity, token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene un usuario por su id.", description = "Obtiene un usuario por su userId")
    @GetMapping("/buscar/{usuarioId}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token, @PathVariable long usuarioId) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(usuarioId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Elimina un usuario por su id.", description = "Elimina un usuario por su userId")
    @DeleteMapping("/eliminar/{usuarioId}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable long usuarioId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{            
            usuarioService.delete(usuarioId, token);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al usuario con userId: " + usuarioId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Actualiza los datos de un usuario por su id.", description = "Actualiza un usuario por su userId")
    @PutMapping("/actualizar/{usuarioId}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @PathVariable long usuarioId, @RequestBody UsuarioDTO entity){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            usuarioService.update(usuarioId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente al usuario con userId: " + usuarioId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Vincula una cuenta a un usuario.", description = "Vincula una cuenta a un usuario")
    @PutMapping("/vincular/usuario/{usuarioId}/cuenta/{cuentaId}")
    public ResponseEntity<?> asociarCuenta(@RequestHeader("Authorization") String token, @PathVariable long usuarioId, @PathVariable long cuentaId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            usuarioService.asociarCuenta(usuarioId, cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se asocio correctamente la cuenta con accountId: " + cuentaId + " al usuario con userId: " + usuarioId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo asociar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Desvincula una cuenta de un usuario.", description = "Desvincula una cuenta de un usuario")
    @DeleteMapping("/desvincular/usuario/{usuarioId}/cuenta/{cuentaId}")
    public ResponseEntity<?> desvincularCuenta(@RequestHeader("Authorization") String token, @PathVariable long usuarioId, @PathVariable long cuentaId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            usuarioService.desvincularCuenta(usuarioId, cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se desvinculo correctamente la cuenta con accountId: " + cuentaId + " del usuario con userId: " + usuarioId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desvincular la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}