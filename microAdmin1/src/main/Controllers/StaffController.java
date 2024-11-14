package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.DTOs.AdministradorDTO;
import main.Services.StaffService;

@RestController
@RequestMapping("/administracion/staff")
public class StaffController {
    
    @Autowired
    private StaffService staffService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(staffService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/buscar/rol/{rol}")
    public ResponseEntity<?> getByRol(@PathVariable String rol){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(staffService.findByRol(rol));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody AdministradorDTO entity){
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(staffService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @GetMapping("/buscar/id/{staffId}")
    public ResponseEntity<?> getById(@PathVariable long staffId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(staffService.findById(staffId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("/eliminar/{staffId}")
    public ResponseEntity<?> delete(@PathVariable long staffId){
        try{
            staffService.delete(staffId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente al integrante con Id: " + staffId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el integrante, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/actualizar/{staffId}")
    public ResponseEntity<?> update(@PathVariable long staffId, @RequestBody AdministradorDTO entity){
        try{
            staffService.update(staffId, entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente al usuario con Id: " + staffId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el usuario, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}