package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.AddressService;
import nurdanemin.ecommerce.business.dto.request.create.address.CreateAddressRequest;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAddressResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAllAddressesResponse;
import nurdanemin.ecommerce.business.rules.AddressRules;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.User;
import nurdanemin.ecommerce.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
    public Address getAddressById(Long id) {
        rules.checkIfExistsById(id);
        Address address = repository.findById(id).orElseThrow();
        return address;
    }

    @Override
    public Address createAddress(CreateAddressRequest request) {
        if (rules.checkIfAddressExists(request.getApartmentNumber(), request.getBuilding(), request.getStreet(),
                request.getDistrict(), request.getCity(), request.getCountry())){
            Address address= repository.findByApartmentNumberAndBuildingAndStreetAndDistrictAndCityAndCountry(
                    request.getApartmentNumber(), request.getBuilding(), request.getStreet(),
                    request.getDistrict(), request.getCity(), request.getCountry());
            return address;
        }

        Address address = mapper.map(request, Address.class);
        address.setUsers(new ArrayList<>());
        Address createdAddres = repository.save(address);
        return createdAddres;

    }

    @Override
    public void addUserForAddress(Address address, User user) {
        List<User> ownersOfAddress = address.getUsers();
        ownersOfAddress.add(user);
        address.setUsers(ownersOfAddress);
        repository.save(address);
    }



    @Override
    public void delete(Long id) {
        rules.checkIfExistsById(id);
        repository.deleteById(id);
    }







}
