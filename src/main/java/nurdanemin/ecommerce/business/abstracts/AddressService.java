package nurdanemin.ecommerce.business.abstracts;

import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.request.update.address.UpdateAddressRequest;
import nurdanemin.ecommerce.business.dto.response.create.address.CreateAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAllAddressesResponse;
import nurdanemin.ecommerce.business.dto.response.update.address.UpdateAddressResponse;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.User;

import java.util.List;

public interface AddressService {
    List<GetAllAddressesResponse> getAll();

    GetAddressResponse getById(Long id);

    CreateAddressResponse createAddress(CreateAddressRequest request);

    UpdateAddressResponse updateAddress(Long id, UpdateAddressRequest request);

    void delete(Long id);


}
