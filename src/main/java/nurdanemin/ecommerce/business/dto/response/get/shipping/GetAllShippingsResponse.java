package nurdanemin.ecommerce.business.dto.response.get.shipping;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Address;
import nurdanemin.ecommerce.entities.enums.ShippingStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllShippingsResponse {

    private Long id;


    private String receiversfirstName;
    private String receiverslastName;


    private Long addressId;

    private ShippingStatus status;

    private Long orderId;
}
