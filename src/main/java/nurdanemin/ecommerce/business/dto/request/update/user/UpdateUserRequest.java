package nurdanemin.ecommerce.business.dto.request.update.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String email;

}
