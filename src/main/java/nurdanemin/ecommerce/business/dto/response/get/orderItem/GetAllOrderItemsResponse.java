package nurdanemin.ecommerce.business.dto.response.get.orderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllOrderItemsResponse {
    private Long id;
    private Long productId;

    private Long orderId;
    private double price;
    private int quantity;
    private double discount;
    private double totalPrice;
}
