package nurdanemin.ecommerce.business.abstracts;


import nurdanemin.ecommerce.business.dto.request.create.cart.CreateCartRequest;
import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.request.update.cart.UpdateCartRequest;

import nurdanemin.ecommerce.business.dto.response.create.cart.CreateCartResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetAllCartsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetCartResponse;
import nurdanemin.ecommerce.business.dto.response.update.cart.UpdateCartResponse;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.Product;
import nurdanemin.ecommerce.entities.User;

import java.util.List;

public interface CartService {
    List<GetAllCartsResponse> getAll();
    GetCartResponse getById(Long id);

    Cart createCartForUser(Long userId);
    UpdateCartResponse updateCart(Long id, UpdateCartRequest request);
    void delete(Long id);
    void emptyCart(Long id);

    GetCartResponse addtoCart(CreateCartItemRequest request);

    GetCartResponse deleteItemFromCart(Long cartId, Long cartItemId);
    GetCartResponse updateItemQuantity(Long cartItemId);


   // TODO add add delete product and change quantity functions
}
