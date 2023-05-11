package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CartService;
import nurdanemin.ecommerce.business.abstracts.ProductService;
import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetAllCartsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetCartResponse;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.Product;
import nurdanemin.ecommerce.repositories.CartItemRepository;
import nurdanemin.ecommerce.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartManager implements CartService {
    private final CartRepository repository;
    private final CartItemRepository cartItemRepository;
    private  final ProductService productService;
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
        Cart cart = repository.findById(id).orElseThrow();
        GetCartResponse response = mapper.map(cart, GetCartResponse.class);
        response.setUserId(cart.getUser().getId());
        response.setCartItemIds(mapCartItemIdsAsList(cart));
        return response;
    }

    @Override
    public Cart getCartById(Long id) {
        Cart cart = repository.findById(id).orElseThrow();
        return cart;
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



        CartItem cartItem = new CartItem();
        Product product = productService.getProductbyId(request.getProductId());
        cartItem.setProduct(product);
        cartItem.setDiscount(request.getDiscount());
        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(product.getPrice());

        cartItem.setCart(cart);

        cartItemRepository.save(cartItem);

        cartItems.add(cartItem);


        Cart cartSaved = repository.save(cart);
        setCartTotalPrice(cartSaved);


        return getById(request.getCartId());

    }

    @Override
    public GetCartResponse deleteItemFromCart(Long cartId, Long cartItemId) {
    Cart cart = repository.findById(cartId).orElseThrow();
    List<CartItem> cartItems = cart.getCartItems();
    CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
    cartItems.remove(cartItem);
    cartItem.setCart(null);
    Cart cartSaved = repository.save(cart);
    setCartTotalPrice(cartSaved);
    return getById(cartId);

    }

    @Override
    public GetCartResponse updateItemQuantity(Long cartId, Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        setCartTotalPrice(repository.findById(cartId).orElseThrow());

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

    public void setCartTotalPrice(Cart cart){
        double totalAmount= 0;
        for(CartItem cartItem : cart.getCartItems() ){
            totalAmount = totalAmount + ((cartItem.getPrice()- cartItem.getPrice()* cartItem.getDiscount())
                    *cartItem.getQuantity());
        }
        cart.setTotalPrice(totalAmount);
        repository.save(cart);
    }


}
