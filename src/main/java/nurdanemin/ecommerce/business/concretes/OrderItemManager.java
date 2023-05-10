package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.OrderItemService;
import nurdanemin.ecommerce.business.dto.request.create.orderItem.CreateOrderItemRequest;
import nurdanemin.ecommerce.business.dto.request.update.orderItem.UpdateOrderItemRequest;
import nurdanemin.ecommerce.business.dto.response.create.orderItem.CreateOrderItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.orderItem.GetAllOrderItemsResponse;
import nurdanemin.ecommerce.business.dto.response.get.orderItem.GetOrderItemResponse;
import nurdanemin.ecommerce.business.dto.response.update.orderItem.UpdateOrderItemResponse;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.Order;
import nurdanemin.ecommerce.entities.OrderItem;
import nurdanemin.ecommerce.repositories.OrderItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class OrderItemManager implements OrderItemService {
    private final OrderItemRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllOrderItemsResponse> getAll() {
        return null;
    }

    @Override
    public OrderItem getById(Long id) {
        return repository.findById(id).orElseThrow();

    }

    @Override
    public OrderItem createOrderItem(CartItem request) {
        OrderItem orderItem = new OrderItem();
        //orderItem.setProduct(request.getProductId());
        // TODO : implement a method to set order Id
        //orderItem.setOrderId(request.getOrderId());
        orderItem.setPrice(request.getPrice());
        orderItem.setQuantity(request.getQuantity());
        orderItem.setDiscount(request.getDiscount());

        orderItem.setDiscount(request.getDiscount());
        repository.save(orderItem);
        return orderItem;
    }

    @Override
    public UpdateOrderItemResponse updateOrderItem(Long id, UpdateOrderItemRequest request) {
        return null;
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
