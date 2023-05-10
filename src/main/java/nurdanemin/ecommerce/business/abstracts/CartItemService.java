package nurdanemin.ecommerce.business.abstracts;


import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.request.update.cartItem.UpdateCartItemRequest;
import nurdanemin.ecommerce.business.dto.response.create.cartItem.CreateCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetAllCartItemsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.update.cartItem.UpdateCartItemResponse;
import nurdanemin.ecommerce.entities.CartItem;

import java.util.List;

public interface CartItemService {
    List<GetAllCartItemsResponse> getAll();
    CartItem getById(Long id);
    void save(CartItem cartItem);
    CartItem updateCartItem(Long id, CartItem request);
    void delete(Long id);
    void deleteAll();
}
