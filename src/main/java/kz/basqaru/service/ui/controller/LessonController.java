package kz.basqaru.service.ui.controller;

import kz.basqaru.service.domain.lessons.service.LessonService;
import kz.basqaru.service.ui.dto.lesson.request.LessonRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
@AllArgsConstructor
@CrossOrigin("*")
public class LessonController {

    private final LessonService service;

    @PutMapping
    public ResponseEntity<?> createLesson(@RequestBody LessonRequest lessonRequest)throws Exception {
        return service.create(lessonRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        return service.delete(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
        @RequestParam("word") String word
    ) throws Exception {
        return service.search(word);
    }
}
