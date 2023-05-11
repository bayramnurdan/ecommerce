package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CartItemService;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetAllCartItemsResponse;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.repositories.CartItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CartItemManager implements CartItemService {
    private final ModelMapper mapper;
    private final CartItemRepository repository;



    @Override
    public List<GetAllCartItemsResponse> getAll() {
        List<CartItem> cartItems = repository.findAll();
        List<GetAllCartItemsResponse> response = cartItems
                .stream()
                .map(cartItem -> mapper.map(cartItem, GetAllCartItemsResponse.class))
                .toList();
        return response;
    }


    @Override
    public CartItem getById(Long id) {
        // TODO : Business Rules
        //TODO : Mapleme fonkisyonları lazım :((
        CartItem cartItem = repository.findById(id).orElseThrow();
        return cartItem;
    }

    @Override
    public void save(CartItem cartItem) {
        repository.save(cartItem);
    }


    @Override
    public void delete(Long id) {
        // TODO :Business Rules
        repository.deleteById(id);

    }



}
