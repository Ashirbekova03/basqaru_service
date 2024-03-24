package kz.basqaru.service.ui.controller;

import kz.basqaru.service.config.jwtToken.AuthorizationStructure;
import kz.basqaru.service.domain.user.service.UserService;
import kz.basqaru.service.ui.dto.user.request.PasswordChangeRequest;
import kz.basqaru.service.ui.dto.user.request.UserDataRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
@CrossOrigin("*")
public class ProfileController {

    private final AuthorizationStructure authorization;

    private final UserService service;

    @GetMapping
    public ResponseEntity<?> getProfile(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkUser(token, (service::getProfile));
    }

    @PostMapping
    public ResponseEntity<?> changeProfile(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token,
        @RequestBody UserDataRequest userDataRequest
    ) throws Exception {
        return authorization.checkUser(token, user -> service.changeProfile(user, userDataRequest));
    }

    @PostMapping("/password")
    public ResponseEntity<?> changeProfile(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token,
        @RequestBody PasswordChangeRequest passwordChangeRequest
    ) throws Exception {
        return authorization.checkUser(token, user -> service.changePassword(user, passwordChangeRequest));
    }
}
