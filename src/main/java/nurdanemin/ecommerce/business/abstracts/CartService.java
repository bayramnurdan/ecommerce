package nurdanemin.ecommerce.business.abstracts;


import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetAllCartsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetCartResponse;
import nurdanemin.ecommerce.entities.Cart;

import java.util.List;

public interface CartService {
    List<GetAllCartsResponse> getAll();
    GetCartResponse getById(Long id);
    Cart getCartById(Long id);

    void delete(Long id);
    void emptyCart(Long id);

    GetCartResponse addtoCart(CreateCartItemRequest request);

    GetCartResponse deleteItemFromCart(Long cartId, Long cartItemId);
    GetCartResponse updateItemQuantity(Long cartId, Long cartItemId, int quantity);

    void setCartTotalPrice(Cart cart);
}
