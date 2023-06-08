package nurdanemin.ecommerce.business.dto.response.update.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAddressResponse {
    private Long id;
    private int apartmentNumber;
    private String building;
    private String street;
    private String district;
    private String city;
    private String country;
}