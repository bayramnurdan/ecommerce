package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.UserService;

import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.request.create.user.CreateUserRequest;
import nurdanemin.ecommerce.business.dto.request.update.user.UpdateUserRequest;
import nurdanemin.ecommerce.business.dto.response.create.user.CreateUserResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetAllOrdersResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetAllUsersResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetUserResponse;
import nurdanemin.ecommerce.business.dto.response.update.user.UpdateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UsersController {
    private final UserService service;


    @GetMapping
    public List<GetAllUsersResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetUserResponse getById(@PathVariable Long id){
        return service.getById(id);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse add(@RequestBody CreateUserRequest request){
        return service.createUser(request);
    }

    @PutMapping("{id}")
    public UpdateUserResponse update(@PathVariable  Long id, @RequestBody UpdateUserRequest request){
        return  service.updateUser(id, request);
    }

    @PutMapping("/add/address/{userId}")
    public GetUserResponse addAddresstoUser(@PathVariable  Long userId, @RequestBody CreateAddressRequest addressRequest){
        return service.addAddresstoUser(userId, addressRequest);
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

    @DeleteMapping("/delete-address-from-user/{userId}")
    public  void deleteAdressForUser(@RequestParam Long addressId, @PathVariable Long userId){
        service.deleteAdressForUser(addressId, userId);

    }
}
