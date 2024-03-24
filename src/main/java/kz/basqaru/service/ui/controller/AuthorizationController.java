package kz.basqaru.service.ui.controller;

import kz.basqaru.service.domain.user.service.UserService;
import kz.basqaru.service.ui.dto.user.request.LoginRequest;
import kz.basqaru.service.ui.dto.user.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthorizationController {

    private final UserService userService;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws Exception {
        return userService.register(registerRequest);
    }
}
