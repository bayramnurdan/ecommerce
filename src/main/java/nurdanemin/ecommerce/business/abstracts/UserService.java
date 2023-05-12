package nurdanemin.ecommerce.business.abstracts;



import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.request.create.user.CreateUserRequest;
import nurdanemin.ecommerce.business.dto.request.update.user.UpdateUserRequest;
import nurdanemin.ecommerce.business.dto.response.create.user.CreateUserResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetAllUsersResponse;
import nurdanemin.ecommerce.business.dto.response.get.user.GetUserResponse;
import nurdanemin.ecommerce.business.dto.response.update.user.UpdateUserResponse;
import nurdanemin.ecommerce.entities.User;

import java.util.List;

public interface UserService {
    List<GetAllUsersResponse> getAll();
    GetUserResponse getById(Long id);
    User getUserById(Long id);

    CreateUserResponse createUser(CreateUserRequest request);
    UpdateUserResponse updateUser(Long id, UpdateUserRequest request);
    void delete(Long id);

    GetUserResponse addAddresstoUser(Long userId, CreateAddressRequest addressRequest);

    void deleteAddressFromUser(Long addressId, Long userId);



}
