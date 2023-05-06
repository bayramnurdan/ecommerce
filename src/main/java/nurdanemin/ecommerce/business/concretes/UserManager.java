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
    private final AddressService addressService;
    private final CartService cartService;
    private final UserRules rules;

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
        return response;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        rules.checkIfEmailExists(request.getEmail());
        User user = mapper.map(request, User.class);
        user.setRole(Role.USER);

        CreateAddressResponse usersAddress = addressService.createAddress(request.getAddress());

        Set<Long> userAddresses = new HashSet<>();
        userAddresses.add(usersAddress.getId());
        user.setAddressIds(userAddresses);


        User createdUser = repository.save(user);

        Cart cartCreated = cartService.createCartForUser(createdUser.getId());
        setcartIdForUser(createdUser.getId(), cartCreated.getId());





        CreateUserResponse response = mapper.map(createdUser, CreateUserResponse.class);
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
        User user = mapper.map(getById(userId), User.class);
        Set<Long> addressIds = new HashSet<>();
        CreateAddressResponse addressResponse = addressService.createAddress(addressRequest);
        addressIds.add(addressResponse.getId());
        repository.save(user);

        return getById(userId);


    }

    @Override
    public User findByCartId(Long cartId) {
        return repository.findByCartId(cartId);
    }

    public void setcartIdForUser(Long userId, Long cartId){
        User user = repository.findById(userId).orElseThrow();
        user.setCartId(cartId);
        repository.save(user);
    }


}
