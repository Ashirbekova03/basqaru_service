package kz.basqaru.service.domain.lessons.service;

import kz.basqaru.service.domain.lessons.model.Lesson;
import kz.basqaru.service.domain.lessons.repository.LessonRepository;
import kz.basqaru.service.ui.dto.lesson.request.LessonRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonService {

    private final LessonRepository repository;

    public ResponseEntity<?> create(LessonRequest lesson) {
        return ResponseEntity.ok(
            repository.save(
                new Lesson(
                    lesson.getTitle(),
                    lesson.getData(),
                    lesson.getContentUrl()
                )
            )
        );
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<Lesson> lessonOptional = repository.findById(id);
        lessonOptional.ifPresent(repository::delete);
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<?> search(String word) {
        Iterable<Lesson> lessons = repository.findAll();
        List<Lesson> response = new ArrayList<>();
        String search = word.toLowerCase();
        for (Lesson lesson : lessons) {
            if (lesson.getData().toLowerCase().contains(search) || lesson.getTitle().toLowerCase().contains(search)) {
                response.add(lesson);
            }
        }
        return ResponseEntity.ok(response);
    }
}
