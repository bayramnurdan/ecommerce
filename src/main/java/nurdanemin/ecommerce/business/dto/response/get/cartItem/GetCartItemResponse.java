package nurdanemin.ecommerce.business.dto.response.get.cartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCartItemResponse {
    private Long id;
    private Long productId;

    private Long cartId;
    private double price;
    private double discount;
    private double totalPrice;
    private int quantity;
}
