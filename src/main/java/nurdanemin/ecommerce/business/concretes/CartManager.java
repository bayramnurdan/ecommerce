package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CartItemService;
import nurdanemin.ecommerce.business.abstracts.CartService;
import nurdanemin.ecommerce.business.abstracts.ProductService;
import nurdanemin.ecommerce.business.abstracts.UserService;
import nurdanemin.ecommerce.business.dto.request.create.cart.CreateCartRequest;
import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.request.update.cart.UpdateCartRequest;
import nurdanemin.ecommerce.business.dto.response.create.address.CreateAddressResponse;
import nurdanemin.ecommerce.business.dto.response.create.cart.CreateCartResponse;
import nurdanemin.ecommerce.business.dto.response.create.cartItem.CreateCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetAllCartsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetCartResponse;
import nurdanemin.ecommerce.business.dto.response.update.cart.UpdateCartResponse;
import nurdanemin.ecommerce.business.rules.ProductRules;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.Product;
import nurdanemin.ecommerce.entities.User;
import nurdanemin.ecommerce.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartManager implements CartService {
    private final CartRepository repository;
    private final CartItemService cartItemService;



    private ModelMapper mapper;
    @Override
    public List<GetAllCartsResponse> getAll() {
        List<Cart> carts = repository.findAll();
        List<GetAllCartsResponse> response = carts
                .stream()
                .map(cart -> mapper.map(cart, GetAllCartsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetCartResponse getById(Long id) {

        return mapper.map(repository.findById(id), GetCartResponse.class);
    }

    @Override
    public Cart  createCartForUser(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        Cart newCard = repository.save(cart);
        return newCard;
    }

    @Override
    public UpdateCartResponse updateCart(Long id, UpdateCartRequest request) {
       Cart cart = mapper.map(request, Cart.class);
       cart.setId(id);
       Cart createdCart = repository.save(cart);
       UpdateCartResponse response = mapper.map(createdCart, UpdateCartResponse.class);
       return response;
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void emptyCart(Long cartId) {
            Cart cart = repository.findById(cartId).orElseThrow();
            cart.setCartItems(new HashSet<>());
            cart.setTotalPrice(0);
            repository.save(cart);
    }

    @Override
    public GetCartResponse addtoCart(CreateCartItemRequest request) {
        Cart cart = repository.findById(request.getCartId()).orElseThrow();



        CartItem cartItemCreated = cartItemService.createCartItem(request);

        cart.getCartItems().add(cartItemCreated.getId());
        cart.setTotalPrice(cart.getTotalPrice()+cartItemCreated.getTotalPrice());

        repository.save(cart);
        return getById(request.getCartId());

    }

    @Override
    public GetCartResponse deleteItemFromCart(Long cartId, Long cartItemId) {
    Cart cart = repository.findById(cartId).orElseThrow();
    cart.getCartItems().remove(cartItemId);
    cart.setTotalPrice(0);
    repository.save(cart);
    return getById(cartId);

    }

    @Override
    public GetCartResponse updateItemQuantity(Long cartItemId) {
        return null;
    }
}
