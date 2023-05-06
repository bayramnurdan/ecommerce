package nurdanemin.ecommerce.business.dto.response.get.user;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.enums.Role;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllUsersResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;


}
