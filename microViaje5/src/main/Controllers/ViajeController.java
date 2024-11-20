package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import main.DTOs.TarifaDTO;
import main.DTOs.ViajeDTO;
import main.Services.ViajeService;

@RestController
@RequestMapping("/viajes")
public class ViajeController {
    
    @Autowired
    private ViajeService viajeService;
    
    @Operation(summary = "Retorna todos los viajes.", description = "Retorna todos los viajes")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Agrega un viaje.", description = "Agrega un viaje, depende de los microservicios de autenticacion, usuario y patin")
    @PostMapping("/alta/usuario/{idUsuario}/patin/{idPatin}")
    public ResponseEntity<?> save(@PathVariable long idUsuario, @PathVariable long idPatin) {
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.save(idUsuario, idPatin));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene un viaje por su id.", description = "Obtiene un viaje por su viajeId")
    @GetMapping("/buscar/{viajeId}")
    public ResponseEntity<?> getById(@PathVariable long viajeId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.findById(viajeId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Eliminia un viaje por su id.", description = "Elimina un viaje por su viajeId")
    @DeleteMapping("/eliminar/{viajeId}")
    public ResponseEntity<?> delete(@PathVariable long viajeId){
        try{
            viajeService.delete(viajeId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el viaje con travelId: " + viajeId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Actualiza los datos de un viaje por su id.", description = "Actualiza los datos de un viaje por su viajeId")
    @PutMapping("/actualizar/{viajeId}")
    public ResponseEntity<?> update(@PathVariable long viajeId, @RequestBody ViajeDTO entity){
        try{
            viajeService.update(viajeId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del viaje con travelId: " + viajeId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    } 
    
    @Operation(summary = "Finaliza un viaje por su id.", description = "Finaliza un viaje por su viajeId")
    @PutMapping("/finalizar/{viajeId}")
    public ResponseEntity<?> travelEnd(@PathVariable long viajeId){
        try{
            viajeService.viajeEnd(viajeId);
            return ResponseEntity.status(HttpStatus.OK).body("Se finalizo correctamente el viaje con travelId: " + viajeId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo finalizar el viaje, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    @Operation(summary = "Aplica una tarifa a partir de la fecha dada.", description = "Aplica una tarifa a partir de la fecha dada")   
    @PostMapping("/tarifas/alta")
    public ResponseEntity<?> saveFare(@RequestBody TarifaDTO entity) {
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.saveTarifa(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}