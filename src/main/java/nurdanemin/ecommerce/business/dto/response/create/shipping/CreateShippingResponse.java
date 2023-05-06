package nurdanemin.ecommerce.business.dto.response.create.shipping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.enums.ShippingStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateShippingResponse {

    private Long id;


    private String receiversfirstName;
    private String receiverslastName;


    private Long addressId;

    private ShippingStatus status;

    private Long orderId;
}
