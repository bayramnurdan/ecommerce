package nurdanemin.ecommerce.business.dto.request.update.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.OrderItem;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrderRequest {
    private Set<OrderItem> orderItems;
    private double orderDiscount;
}
