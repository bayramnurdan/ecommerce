package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Long> {
    boolean existsByApartmentNumberAndBuildingAndStreetAndDistrictAndCityAndCountry(int apartment, String building,
                                                                                    String Street,
                                                                                    String District,
                                                                                    String City,
                                                                                    String Country);
    Address findByApartmentNumberAndBuildingAndStreetAndDistrictAndCityAndCountry(int apartment, String building,
                                                                                    String Street,
                                                                                    String District,
                                                                                    String City,
                                                                                    String Country);

}