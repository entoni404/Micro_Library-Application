package com.restsecurity.authservice.controller;
import javax.validation.Valid;

import com.restsecurity.authservice.payload.request.LoginRequest;
import com.restsecurity.authservice.payload.request.SignupRequest;
import com.restsecurity.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth",
        headers = "Accept=application/json",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @GetMapping("/validate")
    @ResponseBody
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        if(authService.validateJwtToken(token)){
            return ResponseEntity.ok("Valid token!");
        } else {
            return ResponseEntity.status(401).body("Invalid Token!");
        }
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));

    }
    
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            LoginRequest loginRequest = authService.registerUser(signUpRequest);
            return ResponseEntity.ok(authService.authenticateUser(loginRequest));
        } catch (IllegalStateException badRequest) {
            return ResponseEntity.badRequest().body(badRequest.getMessage());
        } catch (Exception serverError) {
            return ResponseEntity.internalServerError()
                    .body("An internal server has occurred, please contact entoni.beluli@gmail.com");
        }
    }


}
