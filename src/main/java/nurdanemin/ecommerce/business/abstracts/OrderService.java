package nurdanemin.ecommerce.business.abstracts;



import nurdanemin.ecommerce.business.dto.request.create.order.CreateOrderRequest;
import nurdanemin.ecommerce.business.dto.response.create.order.CreateOrderResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetAllOrdersResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetOrderResponse;

import java.util.List;

public interface OrderService {
    List<GetAllOrdersResponse> getAll();
    GetOrderResponse getById(Long id);
    CreateOrderResponse createOrderForSavedAddress(CreateOrderRequest request);
    void delete(Long id);
    List<GetAllOrdersResponse> getAllOrdersOfUser(Long userId);

}
