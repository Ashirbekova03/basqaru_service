package kz.basqaru.service.ui.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileResponse {

    private Long id;
    private String fullName;
    private String email;
    private Double balance;
    private String password;

}
