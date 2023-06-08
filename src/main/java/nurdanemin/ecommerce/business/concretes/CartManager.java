package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CartItemService;
import nurdanemin.ecommerce.business.abstracts.CartService;

import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetAllCartsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetCartResponse;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.User;
import nurdanemin.ecommerce.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartManager implements CartService {
    private final CartRepository repository;
    private final CartItemService  cartItemService;
    private ModelMapper mapper;

    @Override
    public List<GetAllCartsResponse> getAll() {
        List<Cart> carts = repository.findAll();
        return carts
                .stream()
                .map(cart -> mapper.map(cart, GetAllCartsResponse.class))
                .toList();
    }

    @Override
    public GetCartResponse getById(Long id) {
        Cart cart = repository.findById(id).orElseThrow();
        GetCartResponse response = mapper.map(cart, GetCartResponse.class);
        response.setUserId(cart.getUser().getId());
        response.setCartItemIds(mapCartItemIdsAsList(cart));
        return response;
    }

    @Override
    public Cart getCartById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());
        return repository.save(cart);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void emptyCart(Long cartId) {
        Cart cart = repository.findById(cartId).orElseThrow();
        List<CartItem> cartItems= cart.getCartItems();
        for (int i=0; i<cartItems.size(); i++){
            cartItems.get(i).setCart(null);
            cartItems.remove(cartItems.get(i));
            }
        cart.setCartItems(cartItems);
        cart.setTotalPrice(0);
        repository.save(cart);
    }

    @Override
    public GetCartResponse addtoCart(CreateCartItemRequest request) {
        Cart cart = repository.findById(request.getCartId()).orElseThrow();
        List<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = cartItemService.createCartItem(request);
        cartItems.add(cartItem);
        setCartTotalPrice(cart.getId());
        Cart cartSaved = repository.save(cart);
        cartItemService.setCartForCartItem(cartItem, cartSaved);
        return getById(request.getCartId());

    }

    @Override
    public GetCartResponse deleteItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = repository.findById(cartId).orElseThrow();
        List<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = cartItemService.getCartItemById(cartId);
        cartItems.remove(cartItem);
        cartItem.setCart(null);
        Cart cartSaved = repository.save(cart);
        setCartTotalPrice(cartSaved.getId());
        return getById(cartId);
    }

    @Override
    public GetCartResponse updateItemQuantity(Long cartId, Long cartItemId, int quantity) {
        cartItemService.updateQuantity(cartItemId, quantity);
        setCartTotalPrice(cartId);
        return getById(cartId);

    }


    public Set<Long> mapCartItemIdsAsList(Cart cart){
        Set<Long> cartItemIds = new HashSet<>();
        for (CartItem cartItem:cart.getCartItems()){
            Long id = cartItem.getId();
            cartItemIds.add(id);
        }
        return cartItemIds;
    }

    public void setCartTotalPrice(Long cartId){
        Cart cart = repository.findById(cartId).orElseThrow();
        double totalAmount= 0;
        for(CartItem cartItem : cart.getCartItems() ){
            totalAmount = totalAmount + ((cartItem.getPrice()- cartItem.getPrice()* cartItem.getDiscount())
                    *cartItem.getQuantity());
        }
        cart.setTotalPrice(totalAmount);
        repository.save(cart);
    }

    @Override
    public void setUserForCart(Cart cart, User user) {
        cart.setUser(user);
        repository.save(cart);
    }


}