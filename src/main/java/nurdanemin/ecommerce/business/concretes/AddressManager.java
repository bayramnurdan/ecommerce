package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.AddressService;
import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.request.update.address.UpdateAddressRequest;
import nurdanemin.ecommerce.business.dto.response.create.address.CreateAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAllAddressesResponse;
import nurdanemin.ecommerce.business.dto.response.update.address.UpdateAddressResponse;
import nurdanemin.ecommerce.business.rules.AddressRules;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.User;
import nurdanemin.ecommerce.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AddressManager implements AddressService {
    private final AddressRepository repository;
    private final ModelMapper mapper;
    private final AddressRules rules;
    @Override
    public List<GetAllAddressesResponse> getAll() {
        List<Address> addresses = repository.findAll();
        List<GetAllAddressesResponse> response = addresses
                .stream()
                .map(address-> mapper.map(address, GetAllAddressesResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetAddressResponse getById(Long id) {
        rules.checkIfExistsById(id);
        Address address = repository.findById(id).orElseThrow();
        GetAddressResponse response = mapper.map(address, GetAddressResponse.class);
        return response;
    }

    @Override
    public CreateAddressResponse createAddress(CreateAddressRequest request) {
        rules.checkIfAddressExists(request.getBuilding(), request.getStreet(),
                request.getDistrict(), request.getCity(), request.getCountry());

        Address address = mapper.map(request, Address.class);
        address.setId(0L);
        Address createdAddres = repository.save(address);
        return mapper.map(createdAddres, CreateAddressResponse.class);
    }



    @Override
    public UpdateAddressResponse updateAddress(Long id, UpdateAddressRequest request) {
        rules.checkIfExistsById(id);
        Address address = mapper.map(request, Address.class);
        address.setId(id);
        Address createdAddres = repository.save(address);
        return mapper.map(createdAddres, UpdateAddressResponse.class);
    }


    @Override
    public void delete(Long id) {
        rules.checkIfExistsById(id);
        repository.deleteById(id);

    }




}
