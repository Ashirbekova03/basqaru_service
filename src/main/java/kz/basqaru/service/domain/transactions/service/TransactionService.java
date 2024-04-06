package kz.basqaru.service.domain.transactions.service;

import kz.basqaru.service.domain.category.model.Category;
import kz.basqaru.service.domain.category.service.CategoryService;
import kz.basqaru.service.domain.transactions.model.Transaction;
import kz.basqaru.service.domain.transactions.repository.TransactionRepository;
import kz.basqaru.service.domain.user.model.User;
import kz.basqaru.service.domain.user.repository.UserRepository;
import kz.basqaru.service.ui.dto.transaction.mapper.TransactionMapper;
import kz.basqaru.service.ui.dto.transaction.request.ByCategoryRequest;
import kz.basqaru.service.ui.dto.transaction.request.PeriodFilter;
import kz.basqaru.service.ui.dto.transaction.request.TransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    private final TransactionMapper mapper;

    private final UserRepository userRepository;

    private final CategoryService categoryService;

    public ResponseEntity<?> addTransaction(User user, TransactionRequest transactionRequest) {
        Category category = categoryService.get(user, transactionRequest.getCategoryId());
        if (category == null) {
            return ResponseEntity.badRequest().body("Category not found");
        }
        if (transactionRequest.getIsReceive()) {
            user.setBalance(user.getBalance() + transactionRequest.getAmount());
        } else {
            user.setBalance(user.getBalance() - transactionRequest.getAmount());
        }
        userRepository.save(user);
        return ResponseEntity.ok(
            mapper.parse(
               repository.save(
                   new Transaction(
                       user.getId(),
                       category.getId(),
                       transactionRequest.getAmount(),
                       transactionRequest.getIsReceive()
                   )
               )
            )
        );
    }

    public ResponseEntity<?> findAllTransactions(User user) {
        return ResponseEntity.ok(mapper.parse(repository.findAllByUserId(user.getId())));
    }

    public ResponseEntity<?> removeTransaction(User user, Long id) {
        Optional<Transaction> transactionOptional = repository.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            if (Objects.equals(transaction.getUserId(), user.getId())) {
                if (transaction.getIsReceive()) {
                    user.setBalance(user.getBalance() - transaction.getAmount());
                } else {
                    user.setBalance(user.getBalance() + transaction.getAmount());
                }
                userRepository.save(user);
                repository.delete(transaction);
            }
        }
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<?> filterByCategory(User user, ByCategoryRequest byCategoryRequest) {
        Category category = categoryService.get(user, byCategoryRequest.getCategoryId());
        if (category == null) {
            return ResponseEntity.badRequest().body("Category not found");
        }
        if (byCategoryRequest.getPeriod() == null) {
            return ResponseEntity.ok(mapper.parse(category, repository.findAllByCategoryId(category.getId())));
        }
        List<Transaction> transactions = repository.findAllByCategoryId(byCategoryRequest.getCategoryId());
        List<Transaction> response = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getDateTime() <= byCategoryRequest.getPeriod().getTo() && transaction.getDateTime() >= byCategoryRequest.getPeriod().getFrom()) {
                response.add(transaction);
            }
        }
        return ResponseEntity.ok(
            mapper.parse(
                category,
                response
            )
        );
    }

    public ResponseEntity<?> filterByPeriod(User user, PeriodFilter periodFilter) {
        List<Transaction> transactions = repository.findAllByUserId(user.getId());
        List<Transaction> response = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getDateTime() <= periodFilter.getTo() && transaction.getDateTime() >= periodFilter.getFrom()) {
                response.add(transaction);
            }
        }
        return ResponseEntity.ok(
            mapper.parse(
                response
            )
        );
    }
}
