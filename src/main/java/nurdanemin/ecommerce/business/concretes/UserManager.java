package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.AddressService;
import nurdanemin.ecommerce.business.abstracts.CartService;
import nurdanemin.ecommerce.business.abstracts.UserService;
import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.request.create.user.CreateUserRequest;
import nurdanemin.ecommerce.business.dto.request.update.user.UpdateUserRequest;
import nurdanemin.ecommerce.business.dto.response.create.user.CreateUserResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetAllUsersResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetUserResponse;
import nurdanemin.ecommerce.business.dto.response.update.user.UpdateUserResponse;
import nurdanemin.ecommerce.business.rules.UserRules;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.User;
import nurdanemin.ecommerce.entities.enums.Role;
import nurdanemin.ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserManager  implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    private final UserRules rules;

    private final CartService cartService;
    private final AddressService addressService;

    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = repository.findAll();
        return users
                .stream()
                .map(user -> mapper.map(user, GetAllUsersResponse.class))
                .toList();
    }

    @Override
    public GetUserResponse getById(Long id) {
        rules.checkIfExistsById(id);
        User user = repository.findById(id).orElseThrow();

        GetUserResponse response = mapper.map(user, GetUserResponse.class);
        response.setAddressIds(getUsersAddressesAsList(user));
        response.setCartId(user.getCart().getId());

        return response;
    }

    @Override
    public User getUserById(Long id) {
        rules.checkIfExistsById(id);
        return repository.findById(id).orElseThrow();
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        rules.checkIfEmailExists(request.getEmail());

        User user = mapper.map(request, User.class);
        user.setRole(Role.USER);

        Address usersAddress = addressService.createAddress(request.getAddress());
        user.setAddresses(List.of(usersAddress));

        Cart cart = cartService.createCart();
        user.setCart(cart);

        User createdUser = repository.save(user);

        cartService.setUserForCart(cart, createdUser);

        addressService.addUserForAddress(usersAddress, createdUser);


        CreateUserResponse response = mapper.map(createdUser, CreateUserResponse.class);
        response.setAddressIds(getUsersAddressesAsList(createdUser));
        response.setCartId(cart.getId());
        return response;
    }

    @Override
    public UpdateUserResponse updateUser(Long id, UpdateUserRequest request) {
        rules.checkIfExistsById(id);
        User userBeforeUpdate = getUserById(id);

        if (!userBeforeUpdate.getEmail().equals(request.getEmail())){
            rules.checkIfEmailExists(request.getEmail());
        }

        User user = mapper.map(request, User.class);
        User createdUser = repository.save(user);
        return mapper.map(createdUser, UpdateUserResponse.class);
    }

    @Override
    public GetUserResponse addAddresstoUser(Long userId,  CreateAddressRequest addressRequest) {
        rules.checkIfExistsById(userId);
        User user = repository.findById(userId).orElseThrow();

        Address addressCreated = addressService.createAddress(addressRequest);
        List<Address> usersAddresses = user.getAddresses();
        usersAddresses.add(addressCreated);

        User userUpdated = repository.save(user);
        addressService.addUserForAddress(addressCreated,userUpdated );

        return getById(userId);
    }

    @Override
    public void deleteAddressFromUser(Long addressId, Long userId) {
        rules.checkIfExistsById(userId);
        Address address = addressService.getAddressById(addressId);
        User user = repository.findById(userId).orElseThrow();

        List<Address> addressList = user.getAddresses();
        addressList.remove(address);
        repository.save(user);
        addressService.updateOwnersOfAddress(addressId, user);
    }


    @Override
    public void delete(Long id) {
        rules.checkIfExistsById(id);
        repository.deleteById(id);
    }



    public List<Long> getUsersAddressesAsList(User user){
        List<Long> addressIds = new ArrayList<>();
        List<Address> userAddresses = user.getAddresses();
        for (Address address : userAddresses){
            addressIds.add(address.getId());
        }
        return addressIds;

    }




}