package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CartItemService;
import nurdanemin.ecommerce.business.abstracts.ProductService;
import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetAllCartItemsResponse;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.Product;
import nurdanemin.ecommerce.repositories.CartItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CartItemManager implements CartItemService {
    private final ModelMapper mapper;
    private final CartItemRepository repository;
    private final ProductService productService;



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
    public CartItem getCartItemById(Long id) {

        CartItem cartItem = repository.findById(id).orElseThrow();
        return cartItem;
    }



    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public CartItem createCartItem(CreateCartItemRequest request) {
        CartItem cartItem = new CartItem();
        Product product = productService.getProductbyId(request.getProductId());
        cartItem.setProduct(product);
        cartItem.setDiscount(request.getDiscount());
        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(product.getPrice());
        CartItem cartItemCreated = repository.save(cartItem);
        return cartItemCreated;
    }

    @Override
    public void setCartForCartItem(CartItem cartItem, Cart cart) {
        cartItem.setCart(cart);
        repository.save(cartItem);
    }

    @Override
    public void updateQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = repository.findById(cartItemId).orElseThrow();
        cartItem.setQuantity(quantity);
        repository.save(cartItem);
    }


}
