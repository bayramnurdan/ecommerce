package nurdanemin.ecommerce.business.dto.request.create.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.entities.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;

    private CreateAddressRequest address;
}
