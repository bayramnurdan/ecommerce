package nurdanemin.ecommerce.business.abstracts;



import nurdanemin.ecommerce.business.dto.request.create.order.CreateOrderRequest;
import nurdanemin.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.order.UpdateOrderRequest;
import nurdanemin.ecommerce.business.dto.response.create.order.CreateOrderResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetAllOrdersResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetOrderResponse;
import nurdanemin.ecommerce.business.dto.response.update.order.UpdateOrderResponse;

import java.util.List;

public interface OrderService {
    List<GetAllOrdersResponse> getAll();
    GetOrderResponse getById(Long id);
    List<GetOrderResponse> getOrdersByUser(Long userId);
    CreateOrderResponse createOrderForSavedAddress(CreateOrderRequest request);
    // TODO : Create order for unknown address;
    UpdateOrderResponse updateOrder(Long id, UpdateOrderRequest request);
    void delete(Long id);
}
