package nurdanemin.ecommerce.business.dto.request.update.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAddressRequest {
    private int apartmentNumber;
    private String building;
    private String street;
    private String district;
    private String city;
    private String country;
}
