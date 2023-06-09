package nurdanemin.ecommerce.business.abstracts;

import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAllAddressesResponse;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.User;

import java.util.List;

public interface AddressService {
    List<GetAllAddressesResponse> getAll();

    GetAddressResponse getById(Long id);
    Address getAddressById(Long id);

    Address createAddress(CreateAddressRequest request);

    void delete(Long id);

    void addUserForAddress(Address address, User user);
    void updateOwnersOfAddress(Long addressId, User user);



}
