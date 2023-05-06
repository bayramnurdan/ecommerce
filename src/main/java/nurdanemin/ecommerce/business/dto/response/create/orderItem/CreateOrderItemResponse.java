package nurdanemin.ecommerce.business.dto.response.create.orderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderItemResponse {
    private Long id;
    private Long productId;

    private Long orderId;
    private double price;
    private int quantity;
    private double discount;
    private double totalPrice;
}
