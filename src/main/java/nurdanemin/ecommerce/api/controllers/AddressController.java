package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.AddressService;

import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.request.update.address.UpdateAddressRequest;
import nurdanemin.ecommerce.business.dto.response.create.address.CreateAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAllAddressesResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetUserResponse;
import nurdanemin.ecommerce.business.dto.response.update.address.UpdateAddressResponse;
import nurdanemin.ecommerce.entities.Address;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/addresses")
public class AddressController {
    private final AddressService service;


    @GetMapping
    public List<GetAllAddressesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetAddressResponse getById(@PathVariable Long id) {
        return service.getById(id);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address add(@RequestBody CreateAddressRequest request) {
        return service.createAddress(request);
    }



    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}


