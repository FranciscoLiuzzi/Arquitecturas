package main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.DTOs.NPatinDTO;
import main.DTOs.ParadaDTO;
import main.DTOs.TarifaDTO;
import main.Services.AdminService;

@RestController
@RequestMapping("/administracion")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
   
    @PostMapping("patines/nuevo")
    public ResponseEntity<?> save(@RequestBody NPatinDTO PatinDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewPatin(PatinDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("patines/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deletePatin(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("informes/informePatinesPor/KmRecorridos/sinTiempoDeUso")
    public ResponseEntity<?> getKm() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getInformePatinesByKms());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("informes/informePatinesPor/KmRecorridos/conTiempoDeUso")
    public ResponseEntity<?> getInformePatinesByKmsAndUso() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getInformePatinesByKmsAndUso());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("informes/informePatinesPor/cantidadDeViajes/{travelsQuantity}/enElAnio/{year}")
    public ResponseEntity<?> getPatinesConMasViajes(@PathVariable Long travelsQuantity, @PathVariable Integer year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getPatinesConMasViajes(travelsQuantity, year));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("informes/informePatinesPor/tiempoTotalDeUso")
    public ResponseEntity<?> getInformePatinesByUso() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getInformePatinesByUso());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("paradas/nueva")
    public ResponseEntity<?> save(@RequestBody ParadaDTO ParadaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewParada(ParadaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("paradas/eliminar/{id}")
    public ResponseEntity<?> deleteParada(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteParada(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("cuentas/suspender/{id}")
    public ResponseEntity<?> suspendCuenta(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.suspendCuenta(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("cuentas/activar/{id}")
    public ResponseEntity<?> activateCuenta(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.activateCuenta(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PostMapping("tarifas/nueva")
    public ResponseEntity<?> saveNewTarifa(@RequestBody TarifaDTO TarifaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewTarifa(TarifaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}