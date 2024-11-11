package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.DTOs.UsuarioDTO;
import main.Services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody UsuarioDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @GetMapping("/buscar/{userId}")
    public ResponseEntity<?> getById(@PathVariable long userId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("/eliminar/{userId}")
    public ResponseEntity<?> delete(@PathVariable long userId){
        try{
            usuarioService.delete(userId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/actualizar/{userId}")
    public ResponseEntity<?> update(@PathVariable long userId, @RequestBody UsuarioDTO entity){
        try{
            usuarioService.update(userId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/vincular/usuario/{userId}/cuenta/{accountId}")
    public ResponseEntity<?> asociarCuenta(@PathVariable long userId, @PathVariable long accountId){
        try{
            usuarioService.asociarCuenta(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se asocio correctamente la cuenta con accountId: " + accountId + " al usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo asociar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("/desvincular/usuario/{userId}/cuenta/{accountId}")
    public ResponseEntity<?> desvincularCuenta(@PathVariable long userId, @PathVariable long accountId){
        try{
            usuarioService.desvincularCuenta(userId, accountId);
            return ResponseEntity.status(HttpStatus.OK).body("Se desvinculo correctamente la cuenta con accountId: " + accountId + " del usuario con userId: " + userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desvincular la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}