package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.DTOs.NFacturaDTO;
import main.Services.FacturaService;

@RestController
@RequestMapping("administracion/facturacion")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;
   
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @PostMapping("/nueva")
    public ResponseEntity<?> save(@RequestBody NFacturaDTO NFacturaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.save(NFacturaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/fechaDesde/{fechaDesde}/fechaHasta/{fechaHasta}")   
    public ResponseEntity<?> getFacturacion(@PathVariable String fechaDesde, @PathVariable String fechaHasta) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.getFacturacion(fechaDesde, fechaHasta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}