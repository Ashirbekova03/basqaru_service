package kz.basqaru.service.domain.category.service;

import kz.basqaru.service.domain.category.model.Category;
import kz.basqaru.service.domain.category.repository.CategoryRepository;
import kz.basqaru.service.domain.user.model.User;
import kz.basqaru.service.ui.dto.category.request.CategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public ResponseEntity<?> addCategory(User user, CategoryRequest categoryRequest) {
        return ResponseEntity.ok(
            repository.save(
                new Category(
                    user.getId(),
                    categoryRequest.getName(),
                    categoryRequest.getImageUrl(),
                    categoryRequest.getLimit()
                )
            )
        );
    }

    public ResponseEntity<?> findAll(User user) {
        return ResponseEntity.ok(repository.findAllByUserId(user.getId()));
    }

    public ResponseEntity<?> remove(User user, Long id) {
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            if (Objects.equals(category.getUserId(), user.getId())) {
                repository.delete(category);
            }
        }
        return ResponseEntity.ok("deleted");
    }

    public Category get(User user, Long id) {
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            if (Objects.equals(category.getUserId(), user.getId())) {
                return category;
            }
        }
        return null;
    }
}
