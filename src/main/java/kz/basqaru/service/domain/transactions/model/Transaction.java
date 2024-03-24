package kz.basqaru.service.domain.transactions.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long categoryId;
    private Double amount;
    private Boolean isReceive;
    private Long dateTime;

    public Transaction(Long userId, Long categoryId, Double amount, Boolean isReceive) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.isReceive = isReceive;
        this.dateTime = System.currentTimeMillis();
    }
}
