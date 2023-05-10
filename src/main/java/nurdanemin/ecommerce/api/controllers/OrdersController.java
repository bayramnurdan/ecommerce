package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.OrderService;
import nurdanemin.ecommerce.business.dto.request.create.order.CreateOrderRequest;
import nurdanemin.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.order.UpdateOrderRequest;
import nurdanemin.ecommerce.business.dto.response.create.order.CreateOrderResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetAllOrdersResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetOrderResponse;
import nurdanemin.ecommerce.business.dto.response.update.order.UpdateOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<GetAllOrdersResponse> getAllOrderdsOfUser(@PathVariable Long userId) {
        return service.getAllOrdersOfUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrderForSavedAddress(@RequestBody CreateOrderRequest request){
        return service.createOrderForSavedAddress(request);
    }

    @PutMapping("{id}")
    public UpdateOrderResponse update(@PathVariable  Long id, @RequestBody UpdateOrderRequest request){
        return  service.updateOrder(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Long id){
        service.delete(id);
    }

    @DeleteMapping("/delete-all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(){
        service.deleteAll();
    }
}
