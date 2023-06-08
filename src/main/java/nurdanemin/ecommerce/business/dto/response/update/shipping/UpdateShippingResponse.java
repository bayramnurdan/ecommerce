package nurdanemin.ecommerce.business.dto.response.update.shipping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.enums.ShippingStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateShippingResponse {

    private Long id;


    private String receiversFirstName;
    private String receiversLastName;


    private Long addressId;

    private ShippingStatus status;

    private Long orderId;
}