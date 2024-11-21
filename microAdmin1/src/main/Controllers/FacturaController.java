package main.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;
import main.DTOs.NFacturaDTO;
import main.Services.FacturaService;

@RestController
@RequestMapping("administracion/facturacion")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;
    
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
    
    @Operation(summary = "Obtiene todas las facturas.", description = "Obtiene todas las facturas.")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene una factura por id.", description = "Obtiene una factura por id.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@RequestHeader("Authorization") String token, @PathVariable Long id){
    	ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Carga una nueva factura.", description = "Carga una nueva factura.")
    @PostMapping("/nueva")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @RequestBody NFacturaDTO nfacturaDTO) {
    	ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.save(nfacturaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtener facturacion en un rango de fechas.", description = "Obtener facturacion en un rango de fechas. Formato de fecha: yyyy-mm-dd")
    @GetMapping("/fechaDesde/{fechaDesde}/fechaHasta/{fechaHasta}")   
    public ResponseEntity<?> getFacturacion(@RequestHeader("Authorization") String token, @PathVariable String fechaDesde, @PathVariable String fechaHasta) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.getFacturacion(fechaDesde, fechaHasta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}