package nurdanemin.ecommerce.business.dto.response.update.cartItem;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.Product;

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
