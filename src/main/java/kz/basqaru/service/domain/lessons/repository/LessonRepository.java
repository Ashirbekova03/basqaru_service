package kz.basqaru.service.domain.lessons.repository;

import kz.basqaru.service.domain.lessons.model.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
