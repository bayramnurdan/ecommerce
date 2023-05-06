package nurdanemin.ecommerce.business.dto.response.update.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserResponse {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
}
