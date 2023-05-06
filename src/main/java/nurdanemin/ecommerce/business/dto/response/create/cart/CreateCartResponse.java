package nurdanemin.ecommerce.business.dto.response.create.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCartResponse {
    private Long id;
    private Long userId;
}
