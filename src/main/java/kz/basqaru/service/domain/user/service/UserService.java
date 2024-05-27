package kz.basqaru.service.domain.user.service;

import kz.basqaru.service.config.jwtToken.TokenUtils;
import kz.basqaru.service.domain.user.model.User;
import kz.basqaru.service.domain.user.repository.UserRepository;
import kz.basqaru.service.ui.dto.user.request.LoginRequest;
import kz.basqaru.service.ui.dto.user.request.PasswordChangeRequest;
import kz.basqaru.service.ui.dto.user.request.RegisterRequest;
import kz.basqaru.service.ui.dto.user.request.UserDataRequest;
import kz.basqaru.service.ui.dto.user.response.ProfileResponse;
import kz.basqaru.service.ui.dto.user.response.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Optional<User> userOptional = repository.findByEmail(loginRequest.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Email not registered!");
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong password");
        }
        String token = TokenUtils.generateUserToken(user.getEmail(), user.getPassword());
        user.setToken(token);
        repository.save(user);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        Optional<User> userOptional = repository.findByEmail(registerRequest.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }
        User user = repository.save(
            new User(
                registerRequest.getFullName(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                0d,
                TokenUtils.generateUserToken(registerRequest.getEmail(), registerRequest.getPassword())
            )
        );
        return ResponseEntity.ok(new TokenResponse(user.getToken()));
    }

    public ResponseEntity<?> getProfile(User user) {
        return ResponseEntity.ok(
            new ProfileResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getBalance(),
                user.getPassword()
            )
        );
    }

    public ResponseEntity<?> changeProfile(User user, UserDataRequest userDataRequest) {
        Optional<User> userOptional = repository.findByEmail(userDataRequest.getEmail());
        if (userOptional.isPresent()) {
            User userNew = userOptional.get();
            if (!Objects.equals(userNew.getId(), user.getId())) {
                return ResponseEntity.badRequest().body("Email already exists!");
            }
        }
        user.setEmail(userDataRequest.getEmail());
        user.setFullName(userDataRequest.getUsername());
        String token = TokenUtils.generateUserToken(user.getEmail(), user.getPassword());
        user.setToken(token);
        repository.save(user);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    public ResponseEntity<?> changePassword(User user, PasswordChangeRequest passwordChangeRequest) {
        if (!user.getPassword().equals(passwordChangeRequest.getOldPassword())) {
            return ResponseEntity.badRequest().body("Wrong old password");
        }
        user.setPassword(passwordChangeRequest.getPassword());
        String token = TokenUtils.generateUserToken(user.getEmail(), user.getPassword());
        user.setToken(token);
        repository.save(user);
        return ResponseEntity.ok(new TokenResponse(token));
    }

}
