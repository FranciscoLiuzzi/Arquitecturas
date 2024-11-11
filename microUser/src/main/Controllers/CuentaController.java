package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.DTOs.CuentaDTO;
import main.Services.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getCuentasByUsuarioId(@PathVariable long usuarioId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.getCuentasByUsuarioId(usuarioId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody CuentaDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @GetMapping("/buscar/{cuentaId}")
    public ResponseEntity<?> getById(@PathVariable long cuentaId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.findById(cuentaId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
   
    @DeleteMapping("/eliminar/{cuentaId}")
    public ResponseEntity<?> delete(@PathVariable long cuentaId){
        try{
            cuentaService.delete(cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el account, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/suspender/{cuentaId}")
    public ResponseEntity<?> suspend(@PathVariable long cuentaId){
        try{
            cuentaService.suspenderCuenta(cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se suspendio correctamente la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo suspender la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/activar/{cuentaId}")
    public ResponseEntity<?> activate(@PathVariable long cuentaId){
        try{
            cuentaService.activarCuenta(cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se activo correctamente la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo activar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/actualizar/{cuentaId}")
    public ResponseEntity<?> update(@PathVariable long cuentaId, @RequestBody CuentaDTO entity){
        try{
            cuentaService.update(cuentaId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/vincular/usuario/{userId}/cuenta/{cuentaId}")
    public ResponseEntity<?> asociarUsuario(@PathVariable long usuarioId, @PathVariable long cuentaId){
        try{
            cuentaService.asociarUsuario(usuarioId, cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se asocio correctamente el usuario con userId: " + usuarioId + " a la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo asociar el usuario a la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("/desvincular/usuario/{userId}/cuenta/{cuentaId}")
    public ResponseEntity<?> desvincularUsuario(@PathVariable long usuarioId, @PathVariable long cuentaId){
        try{
            cuentaService.desvincularUsuario(usuarioId, cuentaId);
            return ResponseEntity.status(HttpStatus.OK).body("Se desvinculo correctamente el usuario con userId: " + usuarioId + " de la cuenta con accountId: " + cuentaId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desvincular el usuario de la cuenta, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/saldo/obtener/{accountId}")
    public ResponseEntity<?> getSaldo(@PathVariable long cuentaId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.getSaldo(cuentaId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/saldo/actualizar/{cuentaId}")
    public void updateSaldo(@PathVariable long cuentaId, @RequestBody Double saldo) {
        cuentaService.updateSaldo(saldo, cuentaId);
    }
}