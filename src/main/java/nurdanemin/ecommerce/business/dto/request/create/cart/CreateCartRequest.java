package nurdanemin.ecommerce.business.dto.request.create.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCartRequest {
    private Long userId;
}
