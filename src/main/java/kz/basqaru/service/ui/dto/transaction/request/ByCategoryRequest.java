package kz.basqaru.service.ui.dto.transaction.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ByCategoryRequest {

    private Long categoryId;
    private PeriodFilter period;

}
