package kz.basqaru.service.domain.category.repository;

import kz.basqaru.service.domain.category.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAllByUserId(Long userId);

}
