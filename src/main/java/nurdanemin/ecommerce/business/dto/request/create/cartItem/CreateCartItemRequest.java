package nurdanemin.ecommerce.business.dto.request.create.cartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Cart;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCartItemRequest {

    private Long productId;
    private Long cartId;

    private int quantity;
    private double discount;


}
