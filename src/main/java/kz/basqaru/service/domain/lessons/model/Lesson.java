package kz.basqaru.service.domain.lessons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long time;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String data;
    @Column(columnDefinition = "TEXT")
    private String contentUrl;

    public Lesson(String title, String data, String imageUrl) {
        this.time = System.currentTimeMillis();
        this.title = title;
        this.data = data;
        this.contentUrl = imageUrl;
    }
}
