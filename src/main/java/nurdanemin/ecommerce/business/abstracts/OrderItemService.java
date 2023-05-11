package nurdanemin.ecommerce.business.abstracts;


import nurdanemin.ecommerce.business.dto.response.get.orderItem.GetAllOrderItemsResponse;
import nurdanemin.ecommerce.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<GetAllOrderItemsResponse> getAll();
    OrderItem getById(Long id);
}
