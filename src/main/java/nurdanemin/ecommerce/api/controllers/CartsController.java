package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;

import nurdanemin.ecommerce.business.abstracts.CartService;
import nurdanemin.ecommerce.business.dto.request.create.cart.CreateCartRequest;
import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.request.update.cart.UpdateCartRequest;
import nurdanemin.ecommerce.business.dto.response.create.cart.CreateCartResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetAllCartsResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetCartResponse;
import nurdanemin.ecommerce.business.dto.response.update.cart.UpdateCartResponse;
import nurdanemin.ecommerce.entities.CartItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/carts")
public class CartsController {
    private final CartService service;


    @GetMapping
    public List<GetAllCartsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCartResponse getById(@PathVariable Long id){
        return service.getById(id);

    }



    @PutMapping("/{id}")
    public UpdateCartResponse update(@PathVariable  Long id, @RequestBody UpdateCartRequest request){
        return  service.updateCart(id, request);
    }

    @PutMapping("/add-to-cart")
    public GetCartResponse addtoCart(@RequestBody CreateCartItemRequest cartItem) {
        return service.addtoCart(cartItem);
    }
    @PutMapping("/empty-card/{cartId}")
    public void emptyCart(@PathVariable Long cartId) {
        service.emptyCart(cartId);
    }
    @PutMapping("/delete-from/{cartId}")
    public GetCartResponse deleteFromCart(@PathVariable Long cartId,@RequestParam Long cartItemId ) {
        return service.deleteItemFromCart(cartId, cartItemId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    @DeleteMapping("/delete-all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(){
        service.deleteAll();
    }

}
