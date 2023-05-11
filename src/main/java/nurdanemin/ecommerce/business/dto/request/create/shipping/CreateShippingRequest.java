package nurdanemin.ecommerce.business.dto.request.create.shipping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.enums.ShippingStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateShippingRequest {
    private String receiversFirstName;
    private String receiversLastName;
    private Long addressId;

}
