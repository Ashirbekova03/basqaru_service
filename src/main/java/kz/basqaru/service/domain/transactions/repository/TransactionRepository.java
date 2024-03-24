package kz.basqaru.service.domain.transactions.repository;

import kz.basqaru.service.domain.transactions.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findAllByUserId(Long userId);

    List<Transaction> findAllByCategoryId(Long id);

    List<Transaction> findAllByCategoryIdAndDateTimeBetween(Long categoryId, Long startDateTime, Long endDateTime);

    List<Transaction> findAllByUserIdAndDateTimeBetween(Long userId, Long startDateTime, Long endDateTime);
}
