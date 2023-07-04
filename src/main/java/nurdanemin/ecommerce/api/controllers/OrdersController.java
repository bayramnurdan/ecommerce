package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.OrderService;
import nurdanemin.ecommerce.business.dto.request.create.order.CreateOrderRequest;
import nurdanemin.ecommerce.business.dto.response.create.order.CreateOrderResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetAllOrdersResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrdersController {
    private final OrderService service;


    @GetMapping
    public List<GetAllOrdersResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetOrderResponse getById(@PathVariable Long id){
        return service.getById(id);

    }
    @GetMapping("get-all-orders-of/{userId}")
    public List<GetAllOrdersResponse> getAllOrdersOfUser(@PathVariable Long userId) {
        return service.getAllOrdersOfUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrderForSavedAddress(@RequestBody CreateOrderRequest request){
        return service.createOrderForSavedAddress(request);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Long id){
        service.delete(id);
    }

}