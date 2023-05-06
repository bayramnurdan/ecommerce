package nurdanemin.ecommerce.business.dto.response.get.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.Cart;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetUserResponse {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    // TODO : addressIds direkt mapleyemiyor
    private Set<Long> addresIds;
    private Long cartId;

}
