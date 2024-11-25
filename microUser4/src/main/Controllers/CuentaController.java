package main.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import main.DTOs.CuentaDTO;
import main.Services.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    
    @Autowired
    private CuentaService cuentaService;
    
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
    
    @Operation(summary = "Obtiene todos las cuentas.", description = "Obtiene todas las cuentas")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene todas las cuentas de un usuario.", description = "Obtiene todas las cuentas de un usuario")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getCuentasByUserId(@RequestHeader("Authorization") String token, @PathVariable long usuarioId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.getCuentasByUsuarioId(usuarioId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Crea una nueva cuenta.", description = "Crea una cuenta")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @RequestBody CuentaDTO entity){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene una cuenta por su id.", description = "Obtiene una cuenta por su accountId")
    @GetMapping("/buscar/{cuentaId}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token, @PathVariable long cuentaId) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.findById(cuentaId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
   
    @Operation(summary = "Elimina una cuenta por su id.", description = "Elimina una cuenta por su accountId")    
    @DeleteMapping("/eliminar/{cuentaId}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable long cuentaId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            cuentaService.delete(cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el account, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Desactiva una cuenta por su id.", description = "Desactiva una cuenta por su accountId")
    @PutMapping("/suspender/{cuentaId}")
    public ResponseEntity<?> suspend(@RequestHeader("Authorization") String token, @PathVariable long cuentaId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            cuentaService.suspenderCuenta(cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se suspendio correctamente la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo suspender la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Activa una cuenta por su id.", description = "Activa una cuenta por su accountId")
    @PutMapping("/activar/{cuentaId}")
    public ResponseEntity<?> activate(@RequestHeader("Authorization") String token, @PathVariable long cuentaId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            cuentaService.activarCuenta(cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se activo correctamente la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo activar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Actualiza una cuenta por su id.", description = "Actualiza una cuenta por su accountId")
    @PutMapping("/actualizar/{cuentaId}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @PathVariable long cuentaId, @RequestBody CuentaDTO entity){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            cuentaService.update(cuentaId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Vincula una cuenta a un usuario.", description = "Vincula un usuario a una cuenta")
    @PutMapping("/vincular/usuario/{userId}/cuenta/{cuentaId}")
    public ResponseEntity<?> asociarUsuario(@RequestHeader("Authorization") String token, @PathVariable long usuarioId, @PathVariable long cuentaId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            cuentaService.asociarUsuario(usuarioId, cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se asocio correctamente el usuario con userId: " + usuarioId + " a la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo asociar el usuario a la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Desvincula una cuenta de un usuario.", description = "Desvincula un usuario de una cuenta")
    @DeleteMapping("/desvincular/usuario/{userId}/cuenta/{cuentaId}")
    public ResponseEntity<?> desvincularUsuario(@RequestHeader("Authorization") String token, @PathVariable long usuarioId, @PathVariable long cuentaId){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            cuentaService.desvincularUsuario(usuarioId, cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se desvinculo correctamente el usuario con userId: " + usuarioId + " de la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desvincular el usuario de la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene el saldo de una cuenta.", description = "Obtiene el saldo de una cuenta")
    @GetMapping("/saldo/obtener/{cuentaId}")
    public ResponseEntity<?> getSaldo(@RequestHeader("Authorization") String token, @PathVariable long cuentaId) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.getSaldo(cuentaId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Actualiza el saldo de una cuenta.", description = "Actualiza el saldo de una cuenta")
    @PutMapping("/saldo/actualizar/{cuentaId}")
    public ResponseEntity<?> updateSaldo(@RequestHeader("Authorization") String token, @PathVariable long cuentaId, @RequestBody Double saldo) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "USER", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            cuentaService.updateSaldo(saldo, cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Saldo Actualizado!");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}