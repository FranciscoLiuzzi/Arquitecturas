package main.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import main.DTOs.NPatinDTO;
import main.DTOs.ParadaDTO;
import main.DTOs.TarifaDTO;
import main.Services.AdminService;

@RestController
@RequestMapping("/administracion")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
	private RestTemplate restTemplate = new RestTemplate();
    
    private static final String TOKEN_VALIDATION_URL = "http://localhost:8082/auth/validar";

    private static final String SCOOTERS_URL = "http://localhost:8002/patines";

    private static final String STATIONS_URL ="http://localhost:8001/paradas";

    private static final String ACCOUNTS_URL = "http://localhost:8004/cuentas";

    private static final String TRAVELS_URL = "http://localhost:8003/viajes";
    
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
   
    @Operation(summary = "Da de alta un nuevo monopatin.", description = "Se comunica con el microservicios de monopatines para dar de alta un nuevo monopatin.")
    @PostMapping("/patines/nuevo")
    public ResponseEntity<?> savePatin(@RequestHeader("Authorization") String token, @RequestBody NPatinDTO patinDTO, HttpServletRequest request) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(SCOOTERS_URL + "/alta", HttpMethod.POST, new HttpEntity<>(patinDTO, headers), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Da de baja un monopatin.", description = "Se comunica con el microservicios de monopatines para dar de baja un monopatin.")
    @DeleteMapping("/patines/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(SCOOTERS_URL + "/eliminar/" + id, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene un informe de lo kilometros recorridos por todos los monopatines", 
            description = "Se comunica con el microservicio de monopatines para obtener un informe de los kilometros recorridos por todos los monopatines.")
    @GetMapping("/informes/km/sinUso")
    public ResponseEntity<?> getKm(HttpServletRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(SCOOTERS_URL + "/informe/km/sinUso", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene un informe con los kilometros recorridos y tiempo de uso de cada monopatin.", 
    		description = "Se comunica con el microservicio de monopatines para obtener un informe con los kilometros recorridos y tiempo de uso de cada monopatin.")   
    @GetMapping("/informes/km/conUso")
    public ResponseEntity<?> getInformePatinesByKmsAndUso(HttpServletRequest request) {
    	try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(SCOOTERS_URL + "/informe/km/conUso", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene un informe de los monopatines que hicieron X cantidad de viajes en determinado año", 
    		description = "Se comunica con el microservicio de monopatines para obtener un informe de los monopatines que hicieron X cantidad de viajes en determinado año.")    
    @GetMapping("/informes/informePatinesPor/cantidadDeViajes/{travelsQuantity}/enElAnio/{year}")
    public ResponseEntity<?> getPatinesConMasViajes(@RequestHeader("Authorization") String token, @PathVariable Long travelsQuantity, @PathVariable Integer year) {
    	ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getPatinesConMasViajes(travelsQuantity, year, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Obtiene un informe de los monopatines ordenasdos por tiempo de uso", description = "Se comunica con el microservicio de monopatines para obtener un informe de los monopatines ordenasdos por tiempo de uso.")
    @GetMapping("/informes/tiempoUso")
    public ResponseEntity<?> getInformePatinesByUso(HttpServletRequest request) {
    	try {
            HttpHeaders headers = new HttpHeaders();
            System.out.println("Request: " );
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            System.out.println("HEADERS: " + headers);
            return restTemplate.exchange(SCOOTERS_URL + "/informe/tiempoUso", HttpMethod.GET, new HttpEntity<>(headers), String.class);           
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    @Operation(summary = "Agrega una nueva parada.", description = "Se comunica con el microservicios de estaciones para dar de alta una nueva parada.")
    @PostMapping("/paradas/nueva")
    public ResponseEntity<?> saveParada(@RequestBody ParadaDTO paradaDTO, HttpServletRequest request)  {
    	try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(STATIONS_URL + "/alta", HttpMethod.POST, new HttpEntity<>(paradaDTO, headers), String.class);           
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Elimina una parada", description = "Se comunica con el microservicios de estaciones para eliminar una parada.")
    @DeleteMapping("/paradas/eliminar/{id}")
    public ResponseEntity<?> deleteParada(@PathVariable Long id, HttpServletRequest request)  {
    	try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(STATIONS_URL + "/eliminar/" + id, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);           
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Suspende temporalmente una cuenta.", description = "Se comunica con el microservicios de cuentas para suspender temporalmente una cuenta.")
    @PutMapping("/cuentas/suspender/{id}")
    public ResponseEntity<?> suspendCuenta(@PathVariable Long id, HttpServletRequest request) {
    	try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(ACCOUNTS_URL + "/suspender/" + id, HttpMethod.PUT, new HttpEntity<>(headers), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Activa una cuenta que estaba previamente desactivada.", description = "Se comunica con el microservicios de cuentas para activar una cuenta que estaba previamente desactivada.")
    @PutMapping("/cuentas/activar/{id}")
    public ResponseEntity<?> activateCuenta(@PathVariable Long id, HttpServletRequest request)  {
    	try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(ACCOUNTS_URL + "/activar/" + id, HttpMethod.PUT, new HttpEntity<>(headers), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @Operation(summary = "Agrega una nueva tarifa a aplicar desde la fecha dada.", description = "Se comunica con el microservicios de tarifas para agregar una nueva tarifa a aplicar desde la fecha dada.")  
    @PostMapping("/tarifas/nueva")
    public ResponseEntity<?> saveNewTarifa(@RequestBody TarifaDTO tarifaDTO, HttpServletRequest request)  {
    	try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            headers.setContentType(MediaType.APPLICATION_JSON);
            return restTemplate.exchange(TRAVELS_URL + "/tarifas/alta", HttpMethod.POST, new HttpEntity<>(tarifaDTO ,headers), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}