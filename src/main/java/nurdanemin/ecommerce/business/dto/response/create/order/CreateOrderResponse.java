package nurdanemin.ecommerce.business.dto.response.create.order;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderResponse {
    private Long id;
    private Long userId;


    @ElementCollection
    private List<Long> orderItemIds;

    private double totalAmount;
    private double orderDiscount;


    private Long paymentId;
    private Long shippingId;
    private Long invoiceId;
}