package kz.basqaru.service.ui.controller;

import kz.basqaru.service.config.jwtToken.AuthorizationStructure;
import kz.basqaru.service.domain.category.service.CategoryService;
import kz.basqaru.service.ui.dto.category.request.CategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@CrossOrigin("*")
public class CategoryController {

    private final AuthorizationStructure authorization;

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<?> findAll(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkUser(token, service::findAll);
    }

    @PostMapping
    public ResponseEntity<?> add(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token,
        @RequestBody CategoryRequest categoryRequest
    ) throws Exception {
        return authorization.checkUser(token, user -> service.addCategory(user, categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token,
        @PathVariable Long id
    ) throws Exception {
        return authorization.checkUser(token, user -> service.remove(user, id));
    }

}
