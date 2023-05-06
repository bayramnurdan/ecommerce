package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressRules {
    private final AddressRepository repository;
    public void checkIfAddressExists(String building, String street, String district, String city, String country){
        if (repository.existsByBuildingAndStreetAndDistrictAndCityAndCountry(building,
                street, district, city, country)){
            throw new RuntimeException("ADDRESS_ALREADY_EXISTS");
        }

    }

    public void checkIfExistsById(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("ADDRES_NOT_EXISTS");
        }
    }


}
