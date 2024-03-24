package kz.basqaru.service.ui.dto.transaction.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRequest {

    private Double amount;
    private Long categoryId;
    private Boolean isReceive;

}
