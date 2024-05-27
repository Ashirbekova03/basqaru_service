package kz.basqaru.service.ui.controller;

import kz.basqaru.service.config.jwtToken.AuthorizationStructure;
import kz.basqaru.service.domain.transactions.service.TransactionService;
import kz.basqaru.service.ui.dto.transaction.request.ByCategoryRequest;
import kz.basqaru.service.ui.dto.transaction.request.PeriodFilter;
import kz.basqaru.service.ui.dto.transaction.request.TransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@AllArgsConstructor
@CrossOrigin("*")
public class TransactionController {

    private final AuthorizationStructure authorization;

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<?> addTransaction(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token,
        @RequestBody TransactionRequest transactionRequest
    ) throws Exception {
        return authorization.checkUser(token, user -> service.addTransaction(user, transactionRequest));
    }

    @GetMapping
    public ResponseEntity<?> findAll(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkUser(token, service::findAllTransactions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTransaction(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token,
        @PathVariable Long id
    ) throws Exception {
        return authorization.checkUser(token, user -> service.removeTransaction(user, id));
    }

    @GetMapping("/category")
    public ResponseEntity<?> filterByCategory(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token,
        @RequestBody ByCategoryRequest byCategoryRequest
    ) {
        return authorization.checkUser(token, user -> service.filterByCategory(user, byCategoryRequest));
    }

    @GetMapping("/period")
    public ResponseEntity<?> filterByPeriod(
        @RequestHeader(AuthorizationStructure.AUTHORIZATION_HEADER) String token,
        @RequestParam String from,
        @RequestParam String to
    ) {
        return authorization.checkUser(token, user -> service.filterByPeriod(user, new PeriodFilter(Long.parseLong(from), Long.parseLong(to))));
    }

}
