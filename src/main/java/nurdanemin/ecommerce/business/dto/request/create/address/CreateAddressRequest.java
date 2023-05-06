package nurdanemin.ecommerce.business.dto.request.create.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAddressRequest {
    private String building;
    private String street;
    private String district;
    private String city;
    private String country;

}