package kz.basqaru.service.config.jwtToken;

import kz.basqaru.service.domain.user.model.User;
import org.springframework.http.ResponseEntity;

public interface UserAllowedCallBack {

    ResponseEntity<?> allowed(User user);

}