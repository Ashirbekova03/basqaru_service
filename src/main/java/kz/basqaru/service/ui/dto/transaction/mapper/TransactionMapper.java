package kz.basqaru.service.ui.dto.transaction.mapper;

import kz.basqaru.service.domain.category.model.Category;
import kz.basqaru.service.domain.category.repository.CategoryRepository;
import kz.basqaru.service.domain.transactions.model.Transaction;
import kz.basqaru.service.ui.dto.transaction.response.TransactionByCategoryResponse;
import kz.basqaru.service.ui.dto.transaction.response.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TransactionMapper {

    private final CategoryRepository categoryRepository;

    public TransactionResponse parse(Transaction transaction) {
        Optional<Category> categoryOptional = categoryRepository.findById(transaction.getCategoryId());
        return categoryOptional.map(category -> new TransactionResponse(
                transaction.getId(),
                category,
                transaction.getAmount(),
                transaction.getIsReceive(),
                transaction.getDateTime()
        )).orElse(new TransactionResponse(
                transaction.getId(),
                null,
                transaction.getAmount(),
                transaction.getIsReceive(),
                transaction.getDateTime()
        ));
    }

    public List<TransactionResponse> parse(List<Transaction> transactions) {
        List<TransactionResponse> responseList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            responseList.add(parse(transaction));
        }
        return responseList;
    }

    public TransactionByCategoryResponse parse(Category category, List<Transaction> transactions) {
        List<TransactionResponse> responseList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            responseList.add(
                new TransactionResponse(
                    transaction.getId(),
                    null,
                    transaction.getAmount(),
                    transaction.getIsReceive(),
                    transaction.getDateTime()
                )
            );
        }
        return new TransactionByCategoryResponse(category, responseList);
    }

}
