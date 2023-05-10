package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.AddressService;
import nurdanemin.ecommerce.business.abstracts.CartService;
import nurdanemin.ecommerce.business.abstracts.UserService;
import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.request.create.user.CreateUserRequest;
import nurdanemin.ecommerce.business.dto.request.update.cart.UpdateCartRequest;
import nurdanemin.ecommerce.business.dto.request.update.user.UpdateUserRequest;
import nurdanemin.ecommerce.business.dto.response.create.address.CreateAddressResponse;
import nurdanemin.ecommerce.business.dto.response.create.cart.CreateCartResponse;
import nurdanemin.ecommerce.business.dto.response.create.user.CreateUserResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetAllUsersResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetUserResponse;
import nurdanemin.ecommerce.business.dto.response.update.user.UpdateUserResponse;
import nurdanemin.ecommerce.business.rules.UserRules;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.User;
import nurdanemin.ecommerce.entities.enums.Role;
import nurdanemin.ecommerce.repositories.AddressRepository;
import nurdanemin.ecommerce.repositories.CartRepository;
import nurdanemin.ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserManager  implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    private final UserRules rules;

    private final AddressService addressService;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = repository.findAll();
        List<GetAllUsersResponse> response = users
                .stream()
                .map(user -> mapper.map(user, GetAllUsersResponse.class))
                .toList();
        return response;
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
       User user = repository.findById(id).orElseThrow();
       return user;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        rules.checkIfEmailExists(request.getEmail());

        User user = mapper.map(request, User.class);
        user.setRole(Role.USER);

        Address usersAddress = addressService.createAddress(request.getAddress());
        user.setAddresses(List.of(usersAddress));

        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());
        user.setCart(cart);

        User createdUser = repository.save(user);

        cart.setUser(createdUser);
        Cart cartCreated = cartRepository.save(cart);

        addressService.addUserForAddress(usersAddress, createdUser);


        CreateUserResponse response = mapper.map(createdUser, CreateUserResponse.class);
        response.setAddressIds(getUsersAddressesAsList(createdUser));
        response.setCartId(cartCreated.getId());
        return response;


    }

    @Override
    public UpdateUserResponse updateUser(Long id, UpdateUserRequest request) {
        // TODO email update etmesi durumunda check gerekli

        rules.checkIfExistsById(id);
        User user = mapper.map(request, User.class);
        User createdUser = repository.save(user);
        UpdateUserResponse response = mapper.map(createdUser, UpdateUserResponse.class);
        return response;



    }


    @Override
    public void delete(Long id) {
        rules.checkIfExistsById(id);
        repository.deleteById(id);

    }


    @Override
    public GetUserResponse addAddresstoUser(Long userId,  CreateAddressRequest addressRequest) {
        User user = repository.findById(userId).orElseThrow();

        Address addressCreated = addressService.createAddress(addressRequest);
        List<Address> usersAddresses = user.getAddresses();
        usersAddresses.add(addressCreated);

        User userUpdated = repository.save(user);
        addressService.addUserForAddress(addressCreated,userUpdated );

        return getById(userId);
    }

    @Override
    public void deleteAdressForUser(Long addressId, Long userId) {
        Address address = addressService.getAddressById(addressId);
        User user = repository.findById(userId).orElseThrow();
        List<User>  userList = address.getUsers();
        List<Address> addressList = user.getAddresses();
        userList.remove(user);
        addressList.remove(address);
        repository.save(user);
        addressRepository.save(address);


    }



    @Override
    public void deleteAll() {
        repository.deleteAll();
    }




    public List<Long> getUsersAddressesAsList(User user){
        List<Long> addressIds = new ArrayList<>();
        List<Address> userAddresses = user.getAddresses();
        for (Address addres : userAddresses){
           addressIds.add(addres.getId());
        }
        return addressIds;

    }




}
