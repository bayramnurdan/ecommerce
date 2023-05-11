package nurdanemin.ecommerce.business.abstracts;


import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetAllCartItemsResponse;
import nurdanemin.ecommerce.entities.CartItem;

import java.util.List;

public interface CartItemService {
    List<GetAllCartItemsResponse> getAll();
    CartItem getById(Long id);
    void save(CartItem cartItem);
    void delete(Long id);

}
