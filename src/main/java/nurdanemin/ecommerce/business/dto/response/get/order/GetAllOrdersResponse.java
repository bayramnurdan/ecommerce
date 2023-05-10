package nurdanemin.ecommerce.business.dto.response.get.order;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllOrdersResponse {
    private Long id;
    private Long userId;
    private double totalAmount;
    private double orderDiscount;
    private Long paymentId;
    private Long shippingId;
    private Long invoiceId;
}
