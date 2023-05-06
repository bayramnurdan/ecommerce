package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Long> {
    boolean existsByBuildingAndStreetAndDistrictAndCityAndCountry(String building,
                                                                  String Street,
                                                                  String District,
                                                                  String City,
                                                                  String Country);

}
