package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.ShippingService;
import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.shipping.UpdateShippingRequest;
import nurdanemin.ecommerce.business.dto.response.create.shipping.CreateShippingResponse;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetAllShippingsResponse;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetShippingResponse;
import nurdanemin.ecommerce.business.dto.response.update.shipping.UpdateShippingResponse;
import nurdanemin.ecommerce.entities.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("api/shippings")
public class ShippingsController {
    private final ShippingService service;


    @GetMapping
    public List<GetAllShippingsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetShippingResponse getById(@PathVariable Long id){
        return service.getById(id);
    }


    @PutMapping("{id}")
    public UpdateShippingResponse update(@PathVariable  Long id, @RequestBody UpdateShippingRequest request){
        return  service.updateShipping(id, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id){
        service.delete(id);
    }

}