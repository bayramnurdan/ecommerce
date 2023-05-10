package nurdanemin.ecommerce.business.dto.response.create.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.enums.Role;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserResponse {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    @Enumerated
    private Role role;

    @ElementCollection
    private List<Long> addressIds;

    private Long cartId;
}
