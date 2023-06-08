package nurdanemin.ecommerce.business.dto.response.update.cartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCartItemResponse {
    private Long id;

    private Long cartId;

    private Long productId;
    private Integer quantity;
    private double discount;
    private double productPrice;
}