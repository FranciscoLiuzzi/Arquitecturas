package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.Services.MantenimientoService;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {
    
    @Autowired
    private MantenimientoService mantenimientoService;
    
    @PutMapping("/patines/actualizarEstado/{id}/estado/{estado}")
    public ResponseEntity<?> actualizarEstado(@PathVariable long id, @PathVariable String estado){
        try{
            mantenimientoService.updatepatinState(id, estado);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el estado del scooter: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el estado del scooter, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}