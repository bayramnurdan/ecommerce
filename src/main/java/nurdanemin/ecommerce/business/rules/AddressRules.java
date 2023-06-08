package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.common.constants.Messages;
import nurdanemin.ecommerce.core.exceptions.BusinessException;
import nurdanemin.ecommerce.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressRules {
    private final AddressRepository repository;
    public boolean checkIfAddressExists(int apartment, String building, String street, String district, String city, String country){
        if (repository.existsByApartmentNumberAndBuildingAndStreetAndDistrictAndCityAndCountry(apartment, building,
                street, district, city, country)){
            return true;
        }
        return false;
    }


    public void checkIfExistsById(Long id){
        if (!repository.existsById(id)){
            throw new BusinessException(Messages.Address.NotExists);
        }
    }


}