package nurdanemin.ecommerce.business.dto.response.create.cartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCartItemResponse {
    private Long id;
    private Long productId;
    private Long cartId;
    private double price;
    private int quantity;
    private double discount;
    private double totalPrice;
}
