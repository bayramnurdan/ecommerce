package nurdanemin.ecommerce.business.dto.response.update.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.CartItem;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCartResponse {
    private Long id;


    private Long userId;


    private List<CartItem> items;

    private double totalPrice;
}