package kz.basqaru.service.config.jwtToken;

import kz.basqaru.service.domain.user.model.User;
import kz.basqaru.service.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorizationStructure {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final UserRepository userRepository;


    public ResponseEntity<?> checkUser(String token, UserAllowedCallBack userAllowedCallBack) {
        Optional<User> userOptional = userRepository.findByToken(token);
        if (userOptional.isPresent()) {
            return userAllowedCallBack.allowed(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong JWT token");
        }
    }

}