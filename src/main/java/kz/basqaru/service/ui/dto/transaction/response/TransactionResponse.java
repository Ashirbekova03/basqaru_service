package kz.basqaru.service.ui.dto.transaction.response;

import kz.basqaru.service.domain.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponse {

    private Long id;
    private Category category;
    private Double amount;
    private Boolean isReceive;
    private Long dateTime;

}
