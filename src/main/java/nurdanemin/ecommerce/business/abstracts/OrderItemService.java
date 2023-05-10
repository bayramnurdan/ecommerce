package nurdanemin.ecommerce.business.abstracts;



import nurdanemin.ecommerce.business.dto.request.create.orderItem.CreateOrderItemRequest;
import nurdanemin.ecommerce.business.dto.request.update.orderItem.UpdateOrderItemRequest;
import nurdanemin.ecommerce.business.dto.response.create.orderItem.CreateOrderItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.orderItem.GetAllOrderItemsResponse;
import nurdanemin.ecommerce.business.dto.response.get.orderItem.GetOrderItemResponse;
import nurdanemin.ecommerce.business.dto.response.update.orderItem.UpdateOrderItemResponse;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<GetAllOrderItemsResponse> getAll();
    OrderItem getById(Long id);
    OrderItem createOrderItem(CartItem request);
    UpdateOrderItemResponse updateOrderItem(Long id, UpdateOrderItemRequest request);
    void delete(Long id);
    void deleteAll();
}
