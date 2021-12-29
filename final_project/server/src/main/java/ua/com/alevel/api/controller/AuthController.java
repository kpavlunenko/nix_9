package ua.com.alevel.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.dto.request.auth.LoginRequest;
import ua.com.alevel.api.dto.request.auth.SignUpRequest;
import ua.com.alevel.api.dto.response.auth.MessageResponse;
import ua.com.alevel.facade.AuthenticationFacade;
import ua.com.alevel.facade.RegistrationFacade;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistrationFacade registrationFacade;
    private final AuthenticationFacade authenticationFacade;

    public AuthController(RegistrationFacade registrationFacade, AuthenticationFacade authenticationFacade) {
        this.registrationFacade = registrationFacade;
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationFacade.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        registrationFacade.registration(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
