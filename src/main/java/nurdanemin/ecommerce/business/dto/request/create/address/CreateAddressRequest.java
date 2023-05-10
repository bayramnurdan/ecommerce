package nurdanemin.ecommerce.business.dto.request.create.address;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private int apartmentNumber;
    @NotBlank
    private String building;
    @NotBlank
    private String street;
    @NotBlank
    private String district;
    @NotBlank
    private String city;

    @JsonSetter(nulls = Nulls.SKIP)
    private String country = "Türkiye";

}
