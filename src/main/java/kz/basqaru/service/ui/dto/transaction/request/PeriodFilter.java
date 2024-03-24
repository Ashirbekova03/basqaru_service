package kz.basqaru.service.ui.dto.transaction.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodFilter {

    private Long from;
    private Long to;

}
