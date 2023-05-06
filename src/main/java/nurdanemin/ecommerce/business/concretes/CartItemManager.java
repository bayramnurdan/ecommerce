package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CartItemService;
import nurdanemin.ecommerce.business.abstracts.CartService;
import nurdanemin.ecommerce.business.abstracts.ProductService;
import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.request.update.cartItem.UpdateCartItemRequest;
import nurdanemin.ecommerce.business.dto.response.create.cartItem.CreateCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetAllCartsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetAllCartItemsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.update.cartItem.UpdateCartItemResponse;
import nurdanemin.ecommerce.business.rules.ProductRules;
import nurdanemin.ecommerce.entities.Cart;
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
    private final ProductService productService;
    private final ProductRules productRules;

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
    public CartItem createCartItem(CreateCartItemRequest request) {
        productRules.checkIfProductExistsById(request.getProductId());
        CartItem cartItem = new CartItem();
        cartItem.setProductId(request.getProductId());
        cartItem.setCartId(request.getCartId());
        cartItem.setDiscount(request.getDiscount());
        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(productService.getById(request.getProductId()).getPrice());
        cartItem.setTotalPrice(productService.getById(request.getProductId()).getPrice()*request.getQuantity());


        CartItem cartItemCreated = repository.save(cartItem);
        return cartItemCreated;


    }

    @Override
    public CartItem updateCartItem(Long id, CartItem request) {

        CartItem cartItem = repository.save(request);
        return cartItem;

    }


    @Override
    public void delete(Long id) {
        // TODO :Business Rules
        repository.deleteById(id);

    }


}
