package nurdanemin.ecommerce.business.dto.request.create.orderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Product;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderItemRequest {
    private Long productId;
    private Long orderId;
    private double price;
    private int quantity;
    private double discount;
    private double totalPrice;
}
