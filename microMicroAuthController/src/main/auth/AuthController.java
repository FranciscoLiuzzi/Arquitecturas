package main.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/acceder")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/registrar")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @DeleteMapping(value = "/eliminar/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email, @RequestHeader("Authorization") String token) {
        // Call the service method to delete the user
        authService.deleteUser(email, token);
        return ResponseEntity.ok("User deleted successfully");
    }
    
    @PostMapping(value = "/validar")
    public ResponseEntity<?> validar(@RequestBody String token) {
        String response;
        try {
            response = authService.validar(token);
            if (response == null) {
                return new ResponseEntity<>("Unauthorized user", HttpStatus.UNAUTHORIZED);
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", response);
                return new ResponseEntity<>(response, headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid token", HttpStatus.FORBIDDEN);
        }
    }
}