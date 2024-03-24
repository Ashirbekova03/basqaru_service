package kz.basqaru.service.ui.dto.lesson.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonRequest {

    private String title;
    private String data;
    private String contentUrl;

}
