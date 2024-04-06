package kz.basqaru.service.domain.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    @Column(name = "amount_limit")
    private Double limit;

    public Category(Long userId, String name, String imageUrl, Double limit) {
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.limit = limit;
    }
}
