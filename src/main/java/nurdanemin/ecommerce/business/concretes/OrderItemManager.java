package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.OrderItemService;
import nurdanemin.ecommerce.business.dto.response.get.orderItem.GetAllOrderItemsResponse;
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
        return repository.findAll()
                .stream()
                .map(orderItem -> mapper.map(orderItem, GetAllOrderItemsResponse.class))
                .toList();
    }

    @Override
    public OrderItem getById(Long id) {
        return repository.findById(id).orElseThrow();

    }



}