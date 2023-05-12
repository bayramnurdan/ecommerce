package nurdanemin.ecommerce.business.abstracts;


import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetAllCartItemsResponse;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.CartItem;

import java.util.List;

public interface CartItemService {
    List<GetAllCartItemsResponse> getAll();
    CartItem getCartItemById(Long id);
    void delete(Long id);
    CartItem createCartItem(CreateCartItemRequest request);

    void setCartForCartItem(CartItem cartItem, Cart cart);
    void updateQuantity(Long cartItemId, int quantity);

}
