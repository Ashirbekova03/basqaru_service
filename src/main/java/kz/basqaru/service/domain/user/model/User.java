package kz.basqaru.service.domain.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Double balance;
    private String token;

    public User(String fullName, String email, String password, Double balance, String token) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.token = token;
    }
}
